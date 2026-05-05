import { symptomFields } from './risk';

export const optionSets = {
  yesNo: [
    { value: true, label: 'Yes' },
    { value: false, label: 'No' }
  ],
  studyArea: ['BYRNIHAT', 'NONGPOH', 'BHOIRYMBONG', 'OTHER'],
  houseType: ['KUTCHA', 'SEMI_PUCCA', 'PUCCA', 'OTHER'],
  ventilation: ['GOOD', 'MODERATE', 'POOR'],
  respondentType: ['HEAD_OF_HOUSEHOLD', 'SPOUSE', 'ADULT_MEMBER', 'CHILD', 'OTHER'],
  gender: ['MALE', 'FEMALE', 'OTHER'],
  ethnicity: ['KHASI', 'GARO', 'ASSAMESE', 'OTHER'],
  education: ['BELOW_10', 'CLASS_10', 'CLASS_12', 'UG', 'PG', 'OTHER'],
  occupation: ['FARMER', 'LABOR', 'STUDENT', 'BUSINESS', 'HOME', 'GOVERNMENT', 'PRIVATE', 'OTHER'],
  cookingFuel: ['LPG', 'FIREWOOD', 'COAL', 'KEROSENE', 'ELECTRICITY', 'OTHER'],
  secondaryCookingFuel: ['NONE', 'LPG', 'FIREWOOD', 'COAL', 'KEROSENE', 'ELECTRICITY', 'OTHER'],
  cookingCategory: ['GAS', 'WOOD', 'BOTH', 'OTHER'],
  cookingLocation: ['SEPARATE_KITCHEN', 'INSIDE_LIVING_ROOM', 'OUTDOOR', 'SHARED_KITCHEN', 'OTHER'],
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

export const defaultSurvey = {
  surveyDate: new Date().toISOString().slice(0, 10),
  consentObtained: true,
  studyArea: 'BYRNIHAT',
  gender: 'MALE',
  age: '',
  secondaryCookingFuel: 'NONE',
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
      { name: 'latitude', label: 'Latitude', type: 'number', step: 'any' },
      { name: 'longitude', label: 'Longitude', type: 'number', step: 'any' },
      { name: 'gpsAccuracy', label: 'GPS accuracy in meters', type: 'number', step: 'any' }
    ]
  },
  {
    title: 'B. Household Information',
    fields: [
      { name: 'headOfHouseholdName', label: 'Head of household name' },
      { name: 'contactNumber', label: 'Contact number' },
      { name: 'district', label: 'District', required: true },
      { name: 'block', label: 'Block', required: true },
      { name: 'village', label: 'Village', required: true },
      { name: 'locality', label: 'Locality' },
      { name: 'wardOrHouseNumber', label: 'Ward or house number' },
      { name: 'houseType', label: 'House type', type: 'select', options: optionSets.houseType, required: true },
      { name: 'numberOfRooms', label: 'Number of rooms', type: 'number', required: true },
      { name: 'totalHouseholdMembers', label: 'Total household members', type: 'number', required: true },
      { name: 'ventilationOfHouse', label: 'Ventilation of house', type: 'select', options: optionSets.ventilation, required: true }
    ]
  },
  {
    title: 'C. Respondent Information',
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
    title: 'D. Indoor Air Pollution Exposure',
    fields: [
      { name: 'primaryCookingFuel', label: 'Primary cooking fuel', type: 'select', options: optionSets.cookingFuel, required: true },
      { name: 'secondaryCookingFuel', label: 'Secondary cooking fuel', type: 'select', options: optionSets.secondaryCookingFuel, required: true },
      { name: 'currentSimpleCookingCategory', label: 'Cooking category', type: 'select', options: optionSets.cookingCategory, required: true },
      { name: 'cookingLocation', label: 'Cooking location', type: 'select', options: optionSets.cookingLocation, required: true },
      { name: 'kitchenVentilation', label: 'Kitchen ventilation', type: 'select', options: optionSets.ventilation, required: true },
      { name: 'chimneyOrExhaustAvailable', label: 'Chimney or exhaust available', type: 'boolean', required: true },
      { name: 'averageCookingHoursPerDay', label: 'Average cooking hours per day', type: 'number', step: '0.1' },
      { name: 'electricityAvailable', label: 'Electricity available', type: 'boolean', required: true },
      { name: 'indoorSmoking', label: 'Indoor smoking', type: 'boolean', required: true },
      { name: 'mosquitoCoilOrIncenseUse', label: 'Mosquito coil or incense use', type: 'select', options: optionSets.mosquito, required: true },
      { name: 'dampnessOrMould', label: 'Dampness or mould', type: 'boolean', required: true },
      { name: 'indoorDustLevel', label: 'Indoor dust level', type: 'select', options: optionSets.dust, required: true }
    ]
  },
  {
    title: 'E. Outdoor Air Pollution Exposure',
    fields: [
      { name: 'houseNearMainRoad', label: 'House near main road', type: 'boolean', required: true },
      { name: 'distanceFromMainRoad', label: 'Distance from main road', type: 'select', options: optionSets.roadDistance, required: true },
      { name: 'heavyVehicleMovementNearby', label: 'Heavy vehicle movement nearby', type: 'select', options: optionSets.traffic, required: true },
      { name: 'nearbyIndustryFactoryQuarry', label: 'Nearby industry, factory, or quarry', type: 'boolean', required: true },
      { name: 'nearbyWasteBurning', label: 'Nearby waste burning', type: 'boolean', required: true },
      { name: 'nearbyConstructionDust', label: 'Nearby construction dust', type: 'boolean', required: true },
      { name: 'visibleDustOrSmokeAroundHouse', label: 'Visible dust or smoke around house', type: 'boolean', required: true },
      { name: 'timeSpentOutdoorsPerDay', label: 'Time spent outdoors per day', type: 'select', options: optionSets.outdoors, required: true }
    ]
  },
  {
    title: 'F. Health Symptoms',
    help: 'Symptoms experienced during the last 30 days.',
    fields: symptomFields.map((name) => ({ name, label: labelize(name), type: 'select', options: optionSets.symptoms }))
  },
  {
    title: 'G. Existing Health Conditions',
    fields: [
      'asthma', 'inhalerUse', 'heartProblems', 'diabetes', 'hypertension', 'tuberculosis',
      'copdOrChronicBronchitis', 'knownAllergy', 'longTermRespiratoryDisease', 'regularMedicationUse'
    ].map((name) => ({ name, label: labelize(name), type: 'boolean', required: name === 'asthma' }))
  },
  {
    title: 'H. Healthcare Access and Vaccination',
    fields: [
      { name: 'visitedHospital', label: 'Visited hospital', type: 'boolean', required: true },
      { name: 'facilityType', label: 'Facility type', type: 'select', options: optionSets.facilityType, required: true, showWhen: { visitedHospital: true } },
      { name: 'facilityName', label: 'Facility name', showWhen: { visitedHospital: true } },
      { name: 'hospitalVisitReason', label: 'Hospital visit reason', type: 'select', options: optionSets.hospitalReason, required: true, showWhen: { visitedHospital: true } },
      { name: 'otherHospitalVisitReason', label: 'Other hospital visit reason', required: true, showWhen: { hospitalVisitReason: 'OTHER' } },
      { name: 'hospitalVisitRelatedToBreathing', label: 'Hospital visit related to breathing', type: 'boolean', showWhen: { visitedHospital: true } },
      { name: 'selfVaccination', label: 'Self vaccination', type: 'boolean' },
      { name: 'anyChildUnderFiveInHousehold', label: 'Any child under five in household', type: 'boolean' },
      { name: 'childVaccinationComplete', label: 'Child vaccination complete', type: 'select', options: optionSets.childVaccination },
      { name: 'childBirthplace', label: 'Child birthplace', type: 'select', options: optionSets.childBirthplace },
      { name: 'enrolledInMHIS', label: 'Enrolled in MHIS', type: 'boolean' }
    ]
  },
  {
    title: 'I. Illness Impact and Remarks',
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
    .replace(/\b\w/g, (letter) => letter.toUpperCase());
}
