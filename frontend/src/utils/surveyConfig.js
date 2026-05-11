import { symptomFields } from './risk';

export const optionSets = {
  yesNo: [
    { value: true, label: 'Yes' },
    { value: false, label: 'No' }
  ],
  studyArea: ['BYRNIHAT', 'NONGPOH', 'BHOIRYMBONG', 'OTHER'],
  ventilation: ['GOOD', 'MODERATE', 'POOR'],
  respondentType: ['HEAD_OF_HOUSEHOLD', 'SPOUSE', 'ADULT_MEMBER', 'CHILD', 'OTHER'],
  gender: ['MALE', 'FEMALE', 'OTHER'],
  ethnicity: ['KHASI', 'GARO', 'ASSAMESE', 'OTHER'],
  education: ['BELOW_10', 'CLASS_10', 'CLASS_12', 'UG', 'PG', 'OTHER'],
  occupation: ['FARMER', 'LABOR', 'STUDENT', 'BUSINESS', 'HOME', 'GOVERNMENT', 'PRIVATE', 'OTHER'],
  cookingFuel: ['LPG', 'FIREWOOD', 'COAL', 'KEROSENE', 'ELECTRICITY', 'OTHER'],
  cookingLocation: ['INSIDE', 'OUTSIDE', 'OTHER'],
  mosquito: ['DAILY', 'SOMETIMES', 'NEVER'],
  dust: ['LOW', 'MODERATE', 'HIGH'],
  roadDistance: ['LESS_THAN_50M', 'BETWEEN_50_100M', 'MORE_THAN_100M', 'NOT_APPLICABLE'],
  traffic: ['LOW', 'MODERATE', 'HIGH'],
  outdoors: ['LESS_THAN_1_HOUR', 'ONE_TO_THREE_HOURS', 'MORE_THAN_THREE_HOURS'],
  symptoms: ['NEVER', 'SOMETIMES', 'OFTEN', 'DAILY'],
  facilityType: ['SHC', 'PHC', 'CHC', 'CIVIL_HOSPITAL', 'PRIVATE_HOSPITAL', 'OTHER', 'NA'],
  hospitalReason: ['HEADACHE', 'RESPIRATORY_PROBLEM', 'FEVER', 'CHEST_PAIN', 'EYE_IRRITATION', 'SKIN_PROBLEM', 'OTHER'],
  childVaccination: ['YES', 'NO', 'NOT_APPLICABLE'],
  childBirthplace: ['HOME', 'HOSPITAL', 'BOTH', 'NOT_APPLICABLE'],
  missedReason: ['HEADACHE', 'RESPIRATORY_ILLNESS', 'FEVER', 'GENERAL_WEAKNESS', 'OTHER'],
  riskLevel: ['LOW', 'MODERATE', 'HIGH', 'VERY_HIGH']
};

export const studyAreaBlockMap = {
  BYRNIHAT: 'Umling',
  NONGPOH: 'Umling',
  BHOIRYMBONG: 'Bhoirymbong'
};

export function locationDefaultsForStudyArea(studyArea) {
  return {
    district: 'Ri Bhoi',
    block: studyAreaBlockMap[studyArea] || ''
  };
}

export const defaultSurvey = {
  surveyDate: new Date().toISOString().slice(0, 10),
  consentObtained: true,
  studyArea: 'BYRNIHAT',
  district: 'Ri Bhoi',
  block: 'Umling',
  gender: 'MALE',
  age: '',
  visitedHospital: false,
  childVaccinationComplete: 'NOT_APPLICABLE',
  childBirthplace: 'NOT_APPLICABLE',
  ...Object.fromEntries(symptomFields.map((field) => [field, 'NEVER']))
};

export const sections = [
  {
    title: 'A. Survey Information',
    fields: [
      { name: 'surveyDate', label: 'Survey date', type: 'date', required: true },
      { name: 'surveyorId', label: 'Surveyor ID', required: true },
      { name: 'consentObtained', label: 'Consent obtained', type: 'boolean', required: true },
      { name: 'studyArea', label: 'Study area', type: 'select', options: optionSets.studyArea, required: true },
      { name: 'otherStudyArea', label: 'Other study area', required: true, showWhen: { studyArea: 'OTHER' } },
      { name: 'district', label: 'District', required: true, readOnly: true },
      { name: 'block', label: 'Block', required: true },
      { name: 'gridId', label: 'Grid ID' },
      { name: 'latitude', label: 'Latitude', type: 'number', step: 'any' },
      { name: 'longitude', label: 'Longitude', type: 'number', step: 'any' },
      { name: 'gpsAccuracy', label: 'GPS accuracy in meters', type: 'number', step: 'any' }
    ]
  },
  {
    title: 'B. Respondent Information',
    fields: [
      { name: 'respondentName', label: 'Respondent name' },
      { name: 'respondentType', label: 'Respondent type', type: 'select', options: optionSets.respondentType, required: true },
      { name: 'age', label: 'Age', type: 'number', required: true },
      { name: 'gender', label: 'Gender', type: 'select', options: optionSets.gender, required: true },
      { name: 'ethnicity', label: 'Ethnicity', type: 'select', options: optionSets.ethnicity },
      { name: 'otherEthnicity', label: 'Other ethnicity', required: true, showWhen: { ethnicity: 'OTHER' } },
      { name: 'education', label: 'Education', type: 'select', options: optionSets.education },
      { name: 'occupation', label: 'Occupation', type: 'select', options: optionSets.occupation },
      { name: 'otherOccupation', label: 'Other occupation', required: true, showWhen: { occupation: 'OTHER' } },
      { name: 'smokingStatus', label: 'Smoking status', type: 'boolean', required: true },
      { name: 'numberOfSmokersInHousehold', label: 'Number of smokers in household', type: 'number', required: true }
    ]
  },
  {
    title: 'C. Indoor Air Pollution Exposure',
    fields: [
      { name: 'primaryCookingFuel', label: 'Cooking', type: 'select', options: optionSets.cookingFuel, required: true },
      { name: 'otherCookingFuel', label: 'Other cooking type', required: true, showWhen: { primaryCookingFuel: 'OTHER' } },
      { name: 'cookingLocation', label: 'Cooking location', type: 'select', options: optionSets.cookingLocation, required: true },
      { name: 'otherCookingLocation', label: 'Other cooking location', required: true, showWhen: { cookingLocation: 'OTHER' } }
    ]
  },
  {
    title: 'D. Health Symptoms',
    help: 'Symptoms experienced during the last 30 days.',
    fields: symptomFields.map((name) => ({ name, label: labelize(name), type: 'select', options: optionSets.symptoms }))
  },
  {
    title: 'E. Existing Health Conditions',
    fields: [
      'asthma', 'inhalerUse', 'heartProblems', 'diabetes', 'highBp', 'tuberculosis'
    ].map((name) => ({ name, label: labelize(name), type: 'boolean' }))
  },
  {
    title: 'F. Healthcare Access and Vaccination',
    fields: [
      { name: 'visitedHospital', label: 'Visited Hospital (Last 12 months)', type: 'boolean', required: true },
      { name: 'facilityType', label: 'Facility type', type: 'select', options: optionSets.facilityType, required: true, showWhen: { visitedHospital: true } },
      { name: 'facilityName', label: 'Facility name', showWhen: { visitedHospital: true } },
      { name: 'hospitalVisitReason', label: 'Hospital visit reason', type: 'select', options: optionSets.hospitalReason, required: true, showWhen: { visitedHospital: true } },
      { name: 'otherHospitalVisitReason', label: 'Other hospital visit reason', required: true, showWhen: { hospitalVisitReason: 'OTHER' } },
      { name: 'hospitalVisitRelatedToBreathing', label: 'Hospital visit related to breathing', type: 'boolean', showWhen: { visitedHospital: true } },
      { name: 'selfVaccination', label: 'Any vaccination taken?', type: 'boolean' },
      { name: 'anyChildUnderFiveInHousehold', label: 'Do you have any children?', type: 'boolean' },
      { name: 'numberOfChildren', label: 'Number of children', type: 'number', showWhen: { anyChildUnderFiveInHousehold: true } },
      { name: 'childVaccinationComplete', label: 'Child vaccination complete', type: 'select', options: optionSets.childVaccination },
      { name: 'childBirthplace', label: 'Child birthplace', type: 'select', options: optionSets.childBirthplace },
      { name: 'enrolledInMHIS', label: 'Enrolled in MHIS', type: 'boolean' }
    ]
  },
  {
    title: 'G. Illness Impact and Remarks',
    fields: [
      { name: 'missedWorkOrSchoolDueToIllness', label: 'Missed work or school due to illness', type: 'boolean', required: true },
      { name: 'numberOfDaysMissedLast30Days', label: 'Days missed in last 30 days', type: 'number', required: true, showWhen: { missedWorkOrSchoolDueToIllness: true } },
      { name: 'missedWorkReason', label: 'Missed work reason', type: 'select', options: optionSets.missedReason, required: true, showWhen: { missedWorkOrSchoolDueToIllness: true } },
      { name: 'otherMissedWorkReason', label: 'Other missed work reason', required: true, showWhen: { missedWorkReason: 'OTHER' } },
      { name: 'remarks', label: 'Remarks', type: 'textarea' }
    ]
  }
];

export function labelize(value) {
  return String(value)
    .replace(/([a-z])([A-Z])/g, '$1 $2')
    .replace(/_/g, ' ')
    .replace(/\b\w/g, (letter) => letter.toUpperCase())
    .replace(/\bBp\b/g, 'BP');
}
