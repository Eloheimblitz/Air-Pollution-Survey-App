package com.airpollution.survey.service;

import com.airpollution.survey.dto.SurveyPayload;
import com.airpollution.survey.dto.SurveyResponse;
import com.airpollution.survey.entity.SurveyRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SurveyMapper {
    public void copyPayload(SurveyPayload payload, SurveyRecord record) {
        BeanUtils.copyProperties(payload, record, "id", "surveyId", "householdId", "submittedBy",
                "createdAt", "updatedAt", "exposureRiskScore", "symptomScore", "vulnerabilityScore",
                "totalRiskScore", "riskLevel");
    }

    public SurveyResponse toResponse(SurveyRecord r) {
        return new SurveyResponse(
                r.getId(), r.getSurveyId(), r.getHouseholdId(), r.getSubmittedBy(), r.getCreatedAt(), r.getUpdatedAt(),
                r.getSurveyDate(), r.getSurveyorId(), r.getConsentObtained(), r.getStudyArea(), r.getOtherStudyArea(),
                r.getLatitude(), r.getLongitude(), r.getGpsAccuracy(), r.getGridId(), r.getHeadOfHouseholdName(), r.getContactNumber(),
                r.getDistrict(), r.getBlock(), r.getVillage(), r.getLocality(), r.getWardOrHouseNumber(), r.getHouseType(),
                r.getNumberOfRooms(), r.getTotalHouseholdMembers(), r.getVentilationOfHouse(), r.getRespondentName(),
                r.getRespondentType(), r.getAge(), r.getAgeGroup(), r.getGender(), r.getEthnicity(), r.getOtherEthnicity(),
                r.getEducation(), r.getOccupation(), r.getOtherOccupation(), r.getSmokingStatus(), r.getNumberOfSmokersInHousehold(),
                r.getPrimaryCookingFuel(), r.getOtherCookingFuel(), r.getSecondaryCookingFuel(), r.getCurrentSimpleCookingCategory(), r.getCookingLocation(),
                r.getOtherCookingLocation(),
                r.getKitchenVentilation(), r.getChimneyOrExhaustAvailable(), r.getAverageCookingHoursPerDay(),
                r.getElectricityAvailable(), r.getIndoorSmoking(), r.getMosquitoCoilOrIncenseUse(), r.getDampnessOrMould(),
                r.getIndoorDustLevel(), r.getHouseNearMainRoad(), r.getDistanceFromMainRoad(), r.getHeavyVehicleMovementNearby(),
                r.getNearbyIndustryFactoryQuarry(), r.getNearbyWasteBurning(), r.getNearbyConstructionDust(),
                r.getVisibleDustOrSmokeAroundHouse(), r.getTimeSpentOutdoorsPerDay(), r.getSinusitis(), r.getRhinitis(),
                r.getSneezing(), r.getSoreThroat(), r.getColdOrFever(), r.getDryCough(), r.getWetCough(), r.getPhlegmCough(), r.getWheezing(),
                r.getBreathlessness(), r.getChestDiscomfort(), r.getSleepDisturbance(), r.getHeadache(), r.getEyeIrritation(),
                r.getSkinIrritation(), r.getAsthma(), r.getInhalerUse(), r.getHeartProblems(), r.getDiabetes(), r.getHighBp(), r.getHypertension(),
                r.getTuberculosis(), r.getCopdOrChronicBronchitis(), r.getKnownAllergy(), r.getLongTermRespiratoryDisease(),
                r.getRegularMedicationUse(), r.getVisitedHospital(), r.getFacilityType(), r.getFacilityName(),
                r.getHospitalVisitReason(), r.getOtherHospitalVisitReason(), r.getHospitalVisitRelatedToBreathing(),
                r.getSelfVaccination(), r.getAnyChildUnderFiveInHousehold(), r.getNumberOfChildren(), r.getChildVaccinationComplete(),
                r.getChildBirthplace(), r.getEnrolledInMHIS(), r.getMissedWorkOrSchoolDueToIllness(),
                r.getNumberOfDaysMissedLast30Days(), r.getMissedWorkReason(), r.getOtherMissedWorkReason(), r.getRemarks(),
                r.getExposureRiskScore(), r.getSymptomScore(), r.getVulnerabilityScore(), r.getTotalRiskScore(), r.getRiskLevel(),
                mainSymptomsSummary(r)
        );
    }

    private String mainSymptomsSummary(SurveyRecord r) {
        StringBuilder builder = new StringBuilder();
        appendSymptom(builder, "Dry cough", r.getDryCough());
        appendSymptom(builder, "Wet cough", r.getWetCough());
        appendSymptom(builder, "Wheezing", r.getWheezing());
        appendSymptom(builder, "Breathlessness", r.getBreathlessness());
        appendSymptom(builder, "Chest discomfort", r.getChestDiscomfort());
        appendSymptom(builder, "Eye irritation", r.getEyeIrritation());
        return builder.isEmpty() ? "None reported" : builder.toString();
    }

    private void appendSymptom(StringBuilder builder, String label, String value) {
        if (value != null && !value.equalsIgnoreCase("NEVER")) {
            if (!builder.isEmpty()) builder.append(", ");
            builder.append(label).append(" (").append(value).append(")");
        }
    }
}
