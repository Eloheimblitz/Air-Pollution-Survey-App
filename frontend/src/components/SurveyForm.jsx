import { useState } from 'react';
import FormField from './FormField';
import { ageGroup } from '../utils/risk';
import { defaultSurvey, locationDefaultsForStudyArea, sections } from '../utils/surveyConfig';

export default function SurveyForm({ initialValues = {}, lockedFields = [], onSubmit, submitLabel = 'Save survey', loading = false }) {
  const [values, setValues] = useState({ ...defaultSurvey, ...initialValues });
  const [activeSection, setActiveSection] = useState(0);
  const [gpsStatus, setGpsStatus] = useState('');
  const [validationErrors, setValidationErrors] = useState([]);

  function update(name, value) {
    setValues((current) => {
      if (name === 'studyArea') {
        return { ...current, [name]: value, ...locationDefaultsForStudyArea(value) };
      }
      return { ...current, [name]: value };
    });
    setValidationErrors([]);
  }

  function visible(field) {
    if (!field.showWhen) return true;
    return Object.entries(field.showWhen).every(([key, expected]) => values[key] === expected);
  }

  function captureGps() {
    if (!navigator.geolocation) {
      setGpsStatus('GPS is not available in this browser.');
      return;
    }
    setGpsStatus('Capturing location...');
    navigator.geolocation.getCurrentPosition(
      (position) => {
        setValues((current) => ({
          ...current,
          latitude: Number(position.coords.latitude.toFixed(7)),
          longitude: Number(position.coords.longitude.toFixed(7)),
          gpsAccuracy: Number(position.coords.accuracy.toFixed(1))
        }));
        setGpsStatus('Location captured.');
      },
      () => setGpsStatus('Location permission denied or unavailable.'),
      { enableHighAccuracy: true, timeout: 12000 }
    );
  }

  function submit(event) {
    event.preventDefault();
    if (values.consentObtained !== true) return;
    const missing = requiredMissing(values);
    if (missing.length > 0) {
      setValidationErrors(missing);
      setActiveSection(missing[0].sectionIndex);
      return;
    }
    onSubmit({ ...values, ageGroup: ageGroup(values.age) });
  }

  const section = sections[activeSection];
  const progress = Math.round(((activeSection + 1) / sections.length) * 100);

  return (
    <form className="survey-form" onSubmit={submit}>
      <div className="progress-card">
        <div>
          <strong>{section.title}</strong>
          <span>{progress}% complete</span>
        </div>
        <div className="progress-track"><span style={{ width: `${progress}%` }} /></div>
      </div>

      <div className="section-tabs">
        {sections.map((item, index) => (
          <button type="button" key={item.title} className={index === activeSection ? 'active' : ''} onClick={() => setActiveSection(index)}>
            {index + 1}
          </button>
        ))}
      </div>

      <section className="form-card">
        <h2>{section.title}</h2>
        {section.help && <p className="muted">{section.help}</p>}
        {activeSection === 0 && (
          <div className="gps-row">
            <button type="button" className="secondary-button" onClick={captureGps}>Capture GPS</button>
            <span>{gpsStatus}</span>
          </div>
        )}
        {values.consentObtained === false && (
          <div className="alert error">Survey cannot be submitted without consent.</div>
        )}
        {validationErrors.length > 0 && (
          <div className="alert error">
            <strong>Complete required fields before submitting.</strong>
            <ul>
              {validationErrors.slice(0, 6).map((item) => (
                <li key={`${item.sectionIndex}-${item.name}`}>{item.label}</li>
              ))}
              {validationErrors.length > 6 && <li>{validationErrors.length - 6} more required fields</li>}
            </ul>
          </div>
        )}
        <div className="form-grid">
          {section.fields.filter(visible).map((field) => (
            <FormField key={field.name} field={field} value={values[field.name]} onChange={(value) => update(field.name, value)} disabled={isLocked(field, lockedFields)} />
          ))}
        </div>
      </section>

      <div className="form-actions sticky-actions">
        <button type="button" className="secondary-button" disabled={activeSection === 0} onClick={() => setActiveSection((value) => value - 1)}>Previous</button>
        {activeSection < sections.length - 1 ? (
          <button type="button" onClick={() => setActiveSection((value) => value + 1)}>Next</button>
        ) : (
          <button disabled={loading || values.consentObtained !== true}>{loading ? 'Saving...' : submitLabel}</button>
        )}
      </div>
    </form>
  );
}

function isLocked(field, lockedFields) {
  return field.readOnly || lockedFields.includes(field.name);
}

function requiredMissing(values) {
  const missing = [];
  sections.forEach((section, sectionIndex) => {
    section.fields.forEach((field) => {
      if (!field.required || !isVisible(field, values)) return;
      const value = values[field.name];
      if (value === null || value === undefined || value === '') {
        missing.push({ sectionIndex, name: field.name, label: `${section.title}: ${field.label}` });
      }
    });
  });
  return missing;
}

function isVisible(field, values) {
  if (!field.showWhen) return true;
  return Object.entries(field.showWhen).every(([key, expected]) => values[key] === expected);
}
