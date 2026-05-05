import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import api from '../api/client';
import { labelize, sections } from '../utils/surveyConfig';

export default function SurveyDetail() {
  const { id } = useParams();
  const [record, setRecord] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    api.get(`/surveys/${id}`)
      .then(({ data }) => setRecord(data))
      .catch(() => setError('Unable to load survey detail.'));
  }, [id]);

  if (error) return <div className="page"><div className="alert error">{error}</div></div>;
  if (!record) return <div className="page"><p>Loading survey detail...</p></div>;

  return (
    <div className="page">
      <header className="page-header">
        <div>
          <p className="eyebrow">{record.householdId}</p>
          <h1>{record.surveyId}</h1>
        </div>
        <div className="header-actions">
          <Link className="secondary-link" to="/surveys">Records</Link>
          <Link className="button-link" to={`/surveys/${record.id}/edit`}>Edit</Link>
        </div>
      </header>

      <section className="risk-preview detail-risk">
        <div><span>Exposure</span><strong>{record.exposureRiskScore}</strong></div>
        <div><span>Symptoms</span><strong>{record.symptomScore}</strong></div>
        <div><span>Vulnerability</span><strong>{record.vulnerabilityScore}</strong></div>
        <div className={`risk-pill ${record.riskLevel?.toLowerCase()}`}><span>Total</span><strong>{record.totalRiskScore} - {labelize(record.riskLevel || '')}</strong></div>
      </section>

      {sections.map((section) => (
        <section className="detail-card" key={section.title}>
          <h2>{section.title}</h2>
          <div className="detail-grid">
            {section.fields.map((field) => (
              <div key={field.name}>
                <span>{field.label}</span>
                <strong>{format(record[field.name])}</strong>
              </div>
            ))}
          </div>
        </section>
      ))}
    </div>
  );
}

function format(value) {
  if (value === true) return 'Yes';
  if (value === false) return 'No';
  if (value === null || value === undefined || value === '') return '-';
  return typeof value === 'string' ? labelize(value) : value;
}
