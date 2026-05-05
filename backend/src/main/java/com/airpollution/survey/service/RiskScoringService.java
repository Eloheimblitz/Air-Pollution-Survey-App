package com.airpollution.survey.service;

import com.airpollution.survey.entity.SurveyRecord;
import org.springframework.stereotype.Service;

@Service
public class RiskScoringService {
    private static final String[] SYMPTOMS = {
            "sinusitis", "rhinitis", "sneezing", "soreThroat", "coldOrFever", "dryCough",
            "phlegmCough", "wheezing", "breathlessness", "chestDiscomfort", "sleepDisturbance",
            "headache", "eyeIrritation", "skinIrritation"
    };

    public void applyScores(SurveyRecord record) {
        String ageGroup = calculateAgeGroup(record.getAge());
        record.setAgeGroup(ageGroup);

        int exposure = 0;
        if (isAny(record.getPrimaryCookingFuel(), "FIREWOOD", "COAL")) exposure += 3;
        if (isAny(record.getSecondaryCookingFuel(), "FIREWOOD", "COAL")) exposure += 2;
        if (is(record.getKitchenVentilation(), "POOR")) exposure += 3;
        if (is(record.getKitchenVentilation(), "MODERATE")) exposure += 1;
        if (Boolean.FALSE.equals(record.getChimneyOrExhaustAvailable())) exposure += 1;
        if (Boolean.TRUE.equals(record.getIndoorSmoking())) exposure += 2;
        if (is(record.getMosquitoCoilOrIncenseUse(), "DAILY")) exposure += 1;
        if (Boolean.TRUE.equals(record.getDampnessOrMould())) exposure += 1;
        if (Boolean.TRUE.equals(record.getHouseNearMainRoad())) exposure += 2;
        if (is(record.getDistanceFromMainRoad(), "LESS_THAN_50M")) exposure += 2;
        if (is(record.getHeavyVehicleMovementNearby(), "HIGH")) exposure += 2;
        if (Boolean.TRUE.equals(record.getNearbyIndustryFactoryQuarry())) exposure += 3;
        if (Boolean.TRUE.equals(record.getNearbyWasteBurning())) exposure += 2;
        if (Boolean.TRUE.equals(record.getNearbyConstructionDust())) exposure += 1;
        if (Boolean.TRUE.equals(record.getVisibleDustOrSmokeAroundHouse())) exposure += 2;

        int vulnerability = 0;
        if (isAny(ageGroup, "UNDER_5", "ELDERLY_60_PLUS")) vulnerability += 2;
        if (Boolean.TRUE.equals(record.getAsthma())) vulnerability += 3;
        if (Boolean.TRUE.equals(record.getCopdOrChronicBronchitis())) vulnerability += 3;
        if (Boolean.TRUE.equals(record.getTuberculosis())) vulnerability += 3;
        if (Boolean.TRUE.equals(record.getLongTermRespiratoryDisease())) vulnerability += 3;

        int symptom = symptomValue(record.getSinusitis()) + symptomValue(record.getRhinitis())
                + symptomValue(record.getSneezing()) + symptomValue(record.getSoreThroat())
                + symptomValue(record.getColdOrFever()) + symptomValue(record.getDryCough())
                + symptomValue(record.getPhlegmCough()) + symptomValue(record.getWheezing())
                + symptomValue(record.getBreathlessness()) + symptomValue(record.getChestDiscomfort())
                + symptomValue(record.getSleepDisturbance()) + symptomValue(record.getHeadache())
                + symptomValue(record.getEyeIrritation()) + symptomValue(record.getSkinIrritation());

        int total = exposure + vulnerability + symptom;
        record.setExposureRiskScore(exposure);
        record.setVulnerabilityScore(vulnerability);
        record.setSymptomScore(symptom);
        record.setTotalRiskScore(total);
        record.setRiskLevel(riskLevel(total));
    }

    public String calculateAgeGroup(Integer age) {
        if (age == null) return null;
        if (age <= 5) return "UNDER_5";
        if (age <= 17) return "CHILD_6_17";
        if (age <= 59) return "ADULT_18_59";
        return "ELDERLY_60_PLUS";
    }

    public String riskLevel(int score) {
        if (score <= 7) return "LOW";
        if (score <= 15) return "MODERATE";
        if (score <= 25) return "HIGH";
        return "VERY_HIGH";
    }

    public static String[] symptomFields() {
        return SYMPTOMS;
    }

    private int symptomValue(String value) {
        if (is(value, "SOMETIMES")) return 1;
        if (is(value, "OFTEN")) return 2;
        if (is(value, "DAILY")) return 3;
        return 0;
    }

    private boolean is(String value, String expected) {
        return value != null && value.equalsIgnoreCase(expected);
    }

    private boolean isAny(String value, String... expected) {
        for (String item : expected) {
            if (is(value, item)) return true;
        }
        return false;
    }
}
