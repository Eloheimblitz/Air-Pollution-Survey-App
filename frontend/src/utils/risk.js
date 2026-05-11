const symptomFields = [
  'sinusitis', 'rhinitis', 'sneezing', 'soreThroat', 'coldOrFever', 'dryCough',
  'wetCough', 'wheezing', 'breathlessness', 'chestDiscomfort', 'sleepDisturbance',
  'headache', 'eyeIrritation', 'skinIrritation'
];

export function ageGroup(age) {
  const value = Number(age);
  if (Number.isNaN(value)) return '';
  if (value <= 5) return 'UNDER_5';
  if (value <= 17) return 'CHILD_6_17';
  if (value <= 59) return 'ADULT_18_59';
  return 'ELDERLY_60_PLUS';
}

export function calculateRisk(values) {
  let exposureRiskScore = 0;
  if (['FIREWOOD', 'COAL'].includes(values.primaryCookingFuel)) exposureRiskScore += 3;
  if (values.kitchenVentilation === 'POOR') exposureRiskScore += 3;
  if (values.kitchenVentilation === 'MODERATE') exposureRiskScore += 1;
  if (values.chimneyOrExhaustAvailable === false) exposureRiskScore += 1;
  if (values.indoorSmoking === true) exposureRiskScore += 2;
  if (values.mosquitoCoilOrIncenseUse === 'DAILY') exposureRiskScore += 1;
  if (values.dampnessOrMould === true) exposureRiskScore += 1;
  if (values.houseNearMainRoad === true) exposureRiskScore += 2;
  if (values.distanceFromMainRoad === 'LESS_THAN_50M') exposureRiskScore += 2;
  if (values.heavyVehicleMovementNearby === 'HIGH') exposureRiskScore += 2;
  if (values.nearbyIndustryFactoryQuarry === true) exposureRiskScore += 3;
  if (values.nearbyWasteBurning === true) exposureRiskScore += 2;
  if (values.nearbyConstructionDust === true) exposureRiskScore += 1;
  if (values.visibleDustOrSmokeAroundHouse === true) exposureRiskScore += 2;

  const group = ageGroup(values.age);
  let vulnerabilityScore = ['UNDER_5', 'ELDERLY_60_PLUS'].includes(group) ? 2 : 0;
  ['asthma', 'tuberculosis'].forEach((field) => {
    if (values[field] === true) vulnerabilityScore += 3;
  });

  const symptomScore = symptomFields.reduce((sum, field) => {
    const value = values[field];
    if (value === 'SOMETIMES') return sum + 1;
    if (value === 'OFTEN') return sum + 2;
    if (value === 'DAILY') return sum + 3;
    return sum;
  }, 0);

  const totalRiskScore = exposureRiskScore + vulnerabilityScore + symptomScore;
  const riskLevel = totalRiskScore <= 7 ? 'LOW' : totalRiskScore <= 15 ? 'MODERATE' : totalRiskScore <= 25 ? 'HIGH' : 'VERY_HIGH';

  return { exposureRiskScore, vulnerabilityScore, symptomScore, totalRiskScore, riskLevel, ageGroup: group };
}

export { symptomFields };
