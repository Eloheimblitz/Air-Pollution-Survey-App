package com.airpollution.survey.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class SurveyPayload {
    @NotNull
    private LocalDate surveyDate;
    @NotBlank
    private String surveyorId;
    @AssertTrue(message = "Survey cannot be submitted without consent.")
    private Boolean consentObtained;
    @NotBlank
    private String studyArea;
    private String otherStudyArea;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal gpsAccuracy;
    private String gridId;
    private String headOfHouseholdName;
    private String contactNumber;
    private String district;
    private String block;
    private String village;
    private String locality;
    private String wardOrHouseNumber;
    private String houseType;
    private Integer numberOfRooms;
    private Integer totalHouseholdMembers;
    private String ventilationOfHouse;
    private String respondentName;
    @NotBlank
    private String respondentType;
    @NotNull
    @Min(0)
    private Integer age;
    private String ageGroup;
    @NotBlank
    private String gender;
    private String ethnicity;
    private String otherEthnicity;
    private String education;
    private String occupation;
    private String otherOccupation;
    @NotNull
    private Boolean smokingStatus;
    @NotNull
    @Min(0)
    private Integer numberOfSmokersInHousehold;
    @NotBlank
    private String primaryCookingFuel;
    private String otherCookingFuel;
    private String secondaryCookingFuel;
    private String currentSimpleCookingCategory;
    @NotBlank
    private String cookingLocation;
    private String otherCookingLocation;
    private String kitchenVentilation;
    private Boolean chimneyOrExhaustAvailable;
    private BigDecimal averageCookingHoursPerDay;
    private Boolean electricityAvailable;
    private Boolean indoorSmoking;
    private String mosquitoCoilOrIncenseUse;
    private Boolean dampnessOrMould;
    private String indoorDustLevel;
    private Boolean houseNearMainRoad;
    private String distanceFromMainRoad;
    private String heavyVehicleMovementNearby;
    private Boolean nearbyIndustryFactoryQuarry;
    private Boolean nearbyWasteBurning;
    private Boolean nearbyConstructionDust;
    private Boolean visibleDustOrSmokeAroundHouse;
    private String timeSpentOutdoorsPerDay;
    private String sinusitis;
    private String rhinitis;
    private String sneezing;
    private String soreThroat;
    private String coldOrFever;
    private String dryCough;
    private String wetCough;
    private String phlegmCough;
    private String wheezing;
    private String breathlessness;
    private String chestDiscomfort;
    private String sleepDisturbance;
    private String headache;
    private String eyeIrritation;
    private String skinIrritation;
    private Boolean asthma;
    private Boolean inhalerUse;
    private Boolean heartProblems;
    private Boolean diabetes;
    private Boolean highBp;
    private Boolean hypertension;
    private Boolean tuberculosis;
    private Boolean copdOrChronicBronchitis;
    private Boolean knownAllergy;
    private Boolean longTermRespiratoryDisease;
    private Boolean regularMedicationUse;
    @NotNull
    private Boolean visitedHospital;
    private String facilityType;
    private String facilityName;
    private String hospitalVisitReason;
    private String otherHospitalVisitReason;
    private Boolean hospitalVisitRelatedToBreathing;
    private Boolean selfVaccination;
    private Boolean anyChildUnderFiveInHousehold;
    @Min(0)
    private Integer numberOfChildren;
    private String childVaccinationComplete;
    private String childBirthplace;
    private Boolean enrolledInMHIS;
    @NotNull
    private Boolean missedWorkOrSchoolDueToIllness;
    private Integer numberOfDaysMissedLast30Days;
    private String missedWorkReason;
    private String otherMissedWorkReason;
    private String remarks;

    @AssertFalse(message = "Other study area is required when study area is OTHER")
    public boolean isOtherStudyAreaMissing() {
        return "OTHER".equals(studyArea) && isBlank(otherStudyArea);
    }

    @AssertFalse(message = "Other ethnicity is required when ethnicity is OTHER")
    public boolean isOtherEthnicityMissing() {
        return "OTHER".equals(ethnicity) && isBlank(otherEthnicity);
    }

    @AssertFalse(message = "Other occupation is required when occupation is OTHER")
    public boolean isOtherOccupationMissing() {
        return "OTHER".equals(occupation) && isBlank(otherOccupation);
    }

    @AssertFalse(message = "Other cooking type is required when cooking is OTHER")
    public boolean isOtherCookingFuelMissing() {
        return "OTHER".equals(primaryCookingFuel) && isBlank(otherCookingFuel);
    }

    @AssertFalse(message = "Other cooking location is required when cooking location is OTHER")
    public boolean isOtherCookingLocationMissing() {
        return "OTHER".equals(cookingLocation) && isBlank(otherCookingLocation);
    }

    @AssertFalse(message = "Facility type is required when hospital was visited")
    public boolean isFacilityTypeMissing() {
        return Boolean.TRUE.equals(visitedHospital) && isBlank(facilityType);
    }

    @AssertFalse(message = "Hospital visit reason is required when hospital was visited")
    public boolean isHospitalVisitReasonMissing() {
        return Boolean.TRUE.equals(visitedHospital) && isBlank(hospitalVisitReason);
    }

    @AssertFalse(message = "Other hospital visit reason is required when reason is OTHER")
    public boolean isOtherHospitalVisitReasonMissing() {
        return "OTHER".equals(hospitalVisitReason) && isBlank(otherHospitalVisitReason);
    }

    @AssertFalse(message = "Days missed is required when work or school was missed")
    public boolean isMissedDaysMissing() {
        return Boolean.TRUE.equals(missedWorkOrSchoolDueToIllness) && numberOfDaysMissedLast30Days == null;
    }

    @AssertFalse(message = "Missed work reason is required when work or school was missed")
    public boolean isMissedWorkReasonMissing() {
        return Boolean.TRUE.equals(missedWorkOrSchoolDueToIllness) && isBlank(missedWorkReason);
    }

    @AssertFalse(message = "Other missed work reason is required when reason is OTHER")
    public boolean isOtherMissedWorkReasonMissing() {
        return "OTHER".equals(missedWorkReason) && isBlank(otherMissedWorkReason);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public LocalDate getSurveyDate() { return surveyDate; }
    public void setSurveyDate(LocalDate surveyDate) { this.surveyDate = surveyDate; }
    public String getSurveyorId() { return surveyorId; }
    public void setSurveyorId(String surveyorId) { this.surveyorId = surveyorId; }
    public Boolean getConsentObtained() { return consentObtained; }
    public void setConsentObtained(Boolean consentObtained) { this.consentObtained = consentObtained; }
    public String getStudyArea() { return studyArea; }
    public void setStudyArea(String studyArea) { this.studyArea = studyArea; }
    public String getOtherStudyArea() { return otherStudyArea; }
    public void setOtherStudyArea(String otherStudyArea) { this.otherStudyArea = otherStudyArea; }
    public BigDecimal getLatitude() { return latitude; }
    public void setLatitude(BigDecimal latitude) { this.latitude = latitude; }
    public BigDecimal getLongitude() { return longitude; }
    public void setLongitude(BigDecimal longitude) { this.longitude = longitude; }
    public BigDecimal getGpsAccuracy() { return gpsAccuracy; }
    public void setGpsAccuracy(BigDecimal gpsAccuracy) { this.gpsAccuracy = gpsAccuracy; }
    public String getGridId() { return gridId; }
    public void setGridId(String gridId) { this.gridId = gridId; }
    public String getHeadOfHouseholdName() { return headOfHouseholdName; }
    public void setHeadOfHouseholdName(String headOfHouseholdName) { this.headOfHouseholdName = headOfHouseholdName; }
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }
    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }
    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }
    public String getWardOrHouseNumber() { return wardOrHouseNumber; }
    public void setWardOrHouseNumber(String wardOrHouseNumber) { this.wardOrHouseNumber = wardOrHouseNumber; }
    public String getHouseType() { return houseType; }
    public void setHouseType(String houseType) { this.houseType = houseType; }
    public Integer getNumberOfRooms() { return numberOfRooms; }
    public void setNumberOfRooms(Integer numberOfRooms) { this.numberOfRooms = numberOfRooms; }
    public Integer getTotalHouseholdMembers() { return totalHouseholdMembers; }
    public void setTotalHouseholdMembers(Integer totalHouseholdMembers) { this.totalHouseholdMembers = totalHouseholdMembers; }
    public String getVentilationOfHouse() { return ventilationOfHouse; }
    public void setVentilationOfHouse(String ventilationOfHouse) { this.ventilationOfHouse = ventilationOfHouse; }
    public String getRespondentName() { return respondentName; }
    public void setRespondentName(String respondentName) { this.respondentName = respondentName; }
    public String getRespondentType() { return respondentType; }
    public void setRespondentType(String respondentType) { this.respondentType = respondentType; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEthnicity() { return ethnicity; }
    public void setEthnicity(String ethnicity) { this.ethnicity = ethnicity; }
    public String getOtherEthnicity() { return otherEthnicity; }
    public void setOtherEthnicity(String otherEthnicity) { this.otherEthnicity = otherEthnicity; }
    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }
    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }
    public String getOtherOccupation() { return otherOccupation; }
    public void setOtherOccupation(String otherOccupation) { this.otherOccupation = otherOccupation; }
    public Boolean getSmokingStatus() { return smokingStatus; }
    public void setSmokingStatus(Boolean smokingStatus) { this.smokingStatus = smokingStatus; }
    public Integer getNumberOfSmokersInHousehold() { return numberOfSmokersInHousehold; }
    public void setNumberOfSmokersInHousehold(Integer numberOfSmokersInHousehold) { this.numberOfSmokersInHousehold = numberOfSmokersInHousehold; }
    public String getPrimaryCookingFuel() { return primaryCookingFuel; }
    public void setPrimaryCookingFuel(String primaryCookingFuel) { this.primaryCookingFuel = primaryCookingFuel; }
    public String getOtherCookingFuel() { return otherCookingFuel; }
    public void setOtherCookingFuel(String otherCookingFuel) { this.otherCookingFuel = otherCookingFuel; }
    public String getSecondaryCookingFuel() { return secondaryCookingFuel; }
    public void setSecondaryCookingFuel(String secondaryCookingFuel) { this.secondaryCookingFuel = secondaryCookingFuel; }
    public String getCurrentSimpleCookingCategory() { return currentSimpleCookingCategory; }
    public void setCurrentSimpleCookingCategory(String currentSimpleCookingCategory) { this.currentSimpleCookingCategory = currentSimpleCookingCategory; }
    public String getCookingLocation() { return cookingLocation; }
    public void setCookingLocation(String cookingLocation) { this.cookingLocation = cookingLocation; }
    public String getOtherCookingLocation() { return otherCookingLocation; }
    public void setOtherCookingLocation(String otherCookingLocation) { this.otherCookingLocation = otherCookingLocation; }
    public String getKitchenVentilation() { return kitchenVentilation; }
    public void setKitchenVentilation(String kitchenVentilation) { this.kitchenVentilation = kitchenVentilation; }
    public Boolean getChimneyOrExhaustAvailable() { return chimneyOrExhaustAvailable; }
    public void setChimneyOrExhaustAvailable(Boolean chimneyOrExhaustAvailable) { this.chimneyOrExhaustAvailable = chimneyOrExhaustAvailable; }
    public BigDecimal getAverageCookingHoursPerDay() { return averageCookingHoursPerDay; }
    public void setAverageCookingHoursPerDay(BigDecimal averageCookingHoursPerDay) { this.averageCookingHoursPerDay = averageCookingHoursPerDay; }
    public Boolean getElectricityAvailable() { return electricityAvailable; }
    public void setElectricityAvailable(Boolean electricityAvailable) { this.electricityAvailable = electricityAvailable; }
    public Boolean getIndoorSmoking() { return indoorSmoking; }
    public void setIndoorSmoking(Boolean indoorSmoking) { this.indoorSmoking = indoorSmoking; }
    public String getMosquitoCoilOrIncenseUse() { return mosquitoCoilOrIncenseUse; }
    public void setMosquitoCoilOrIncenseUse(String mosquitoCoilOrIncenseUse) { this.mosquitoCoilOrIncenseUse = mosquitoCoilOrIncenseUse; }
    public Boolean getDampnessOrMould() { return dampnessOrMould; }
    public void setDampnessOrMould(Boolean dampnessOrMould) { this.dampnessOrMould = dampnessOrMould; }
    public String getIndoorDustLevel() { return indoorDustLevel; }
    public void setIndoorDustLevel(String indoorDustLevel) { this.indoorDustLevel = indoorDustLevel; }
    public Boolean getHouseNearMainRoad() { return houseNearMainRoad; }
    public void setHouseNearMainRoad(Boolean houseNearMainRoad) { this.houseNearMainRoad = houseNearMainRoad; }
    public String getDistanceFromMainRoad() { return distanceFromMainRoad; }
    public void setDistanceFromMainRoad(String distanceFromMainRoad) { this.distanceFromMainRoad = distanceFromMainRoad; }
    public String getHeavyVehicleMovementNearby() { return heavyVehicleMovementNearby; }
    public void setHeavyVehicleMovementNearby(String heavyVehicleMovementNearby) { this.heavyVehicleMovementNearby = heavyVehicleMovementNearby; }
    public Boolean getNearbyIndustryFactoryQuarry() { return nearbyIndustryFactoryQuarry; }
    public void setNearbyIndustryFactoryQuarry(Boolean nearbyIndustryFactoryQuarry) { this.nearbyIndustryFactoryQuarry = nearbyIndustryFactoryQuarry; }
    public Boolean getNearbyWasteBurning() { return nearbyWasteBurning; }
    public void setNearbyWasteBurning(Boolean nearbyWasteBurning) { this.nearbyWasteBurning = nearbyWasteBurning; }
    public Boolean getNearbyConstructionDust() { return nearbyConstructionDust; }
    public void setNearbyConstructionDust(Boolean nearbyConstructionDust) { this.nearbyConstructionDust = nearbyConstructionDust; }
    public Boolean getVisibleDustOrSmokeAroundHouse() { return visibleDustOrSmokeAroundHouse; }
    public void setVisibleDustOrSmokeAroundHouse(Boolean visibleDustOrSmokeAroundHouse) { this.visibleDustOrSmokeAroundHouse = visibleDustOrSmokeAroundHouse; }
    public String getTimeSpentOutdoorsPerDay() { return timeSpentOutdoorsPerDay; }
    public void setTimeSpentOutdoorsPerDay(String timeSpentOutdoorsPerDay) { this.timeSpentOutdoorsPerDay = timeSpentOutdoorsPerDay; }
    public String getSinusitis() { return sinusitis; }
    public void setSinusitis(String sinusitis) { this.sinusitis = sinusitis; }
    public String getRhinitis() { return rhinitis; }
    public void setRhinitis(String rhinitis) { this.rhinitis = rhinitis; }
    public String getSneezing() { return sneezing; }
    public void setSneezing(String sneezing) { this.sneezing = sneezing; }
    public String getSoreThroat() { return soreThroat; }
    public void setSoreThroat(String soreThroat) { this.soreThroat = soreThroat; }
    public String getColdOrFever() { return coldOrFever; }
    public void setColdOrFever(String coldOrFever) { this.coldOrFever = coldOrFever; }
    public String getDryCough() { return dryCough; }
    public void setDryCough(String dryCough) { this.dryCough = dryCough; }
    public String getWetCough() { return wetCough; }
    public void setWetCough(String wetCough) { this.wetCough = wetCough; }
    public String getPhlegmCough() { return phlegmCough; }
    public void setPhlegmCough(String phlegmCough) { this.phlegmCough = phlegmCough; }
    public String getWheezing() { return wheezing; }
    public void setWheezing(String wheezing) { this.wheezing = wheezing; }
    public String getBreathlessness() { return breathlessness; }
    public void setBreathlessness(String breathlessness) { this.breathlessness = breathlessness; }
    public String getChestDiscomfort() { return chestDiscomfort; }
    public void setChestDiscomfort(String chestDiscomfort) { this.chestDiscomfort = chestDiscomfort; }
    public String getSleepDisturbance() { return sleepDisturbance; }
    public void setSleepDisturbance(String sleepDisturbance) { this.sleepDisturbance = sleepDisturbance; }
    public String getHeadache() { return headache; }
    public void setHeadache(String headache) { this.headache = headache; }
    public String getEyeIrritation() { return eyeIrritation; }
    public void setEyeIrritation(String eyeIrritation) { this.eyeIrritation = eyeIrritation; }
    public String getSkinIrritation() { return skinIrritation; }
    public void setSkinIrritation(String skinIrritation) { this.skinIrritation = skinIrritation; }
    public Boolean getAsthma() { return asthma; }
    public void setAsthma(Boolean asthma) { this.asthma = asthma; }
    public Boolean getInhalerUse() { return inhalerUse; }
    public void setInhalerUse(Boolean inhalerUse) { this.inhalerUse = inhalerUse; }
    public Boolean getHeartProblems() { return heartProblems; }
    public void setHeartProblems(Boolean heartProblems) { this.heartProblems = heartProblems; }
    public Boolean getDiabetes() { return diabetes; }
    public void setDiabetes(Boolean diabetes) { this.diabetes = diabetes; }
    public Boolean getHighBp() { return highBp; }
    public void setHighBp(Boolean highBp) { this.highBp = highBp; }
    public Boolean getHypertension() { return hypertension; }
    public void setHypertension(Boolean hypertension) { this.hypertension = hypertension; }
    public Boolean getTuberculosis() { return tuberculosis; }
    public void setTuberculosis(Boolean tuberculosis) { this.tuberculosis = tuberculosis; }
    public Boolean getCopdOrChronicBronchitis() { return copdOrChronicBronchitis; }
    public void setCopdOrChronicBronchitis(Boolean copdOrChronicBronchitis) { this.copdOrChronicBronchitis = copdOrChronicBronchitis; }
    public Boolean getKnownAllergy() { return knownAllergy; }
    public void setKnownAllergy(Boolean knownAllergy) { this.knownAllergy = knownAllergy; }
    public Boolean getLongTermRespiratoryDisease() { return longTermRespiratoryDisease; }
    public void setLongTermRespiratoryDisease(Boolean longTermRespiratoryDisease) { this.longTermRespiratoryDisease = longTermRespiratoryDisease; }
    public Boolean getRegularMedicationUse() { return regularMedicationUse; }
    public void setRegularMedicationUse(Boolean regularMedicationUse) { this.regularMedicationUse = regularMedicationUse; }
    public Boolean getVisitedHospital() { return visitedHospital; }
    public void setVisitedHospital(Boolean visitedHospital) { this.visitedHospital = visitedHospital; }
    public String getFacilityType() { return facilityType; }
    public void setFacilityType(String facilityType) { this.facilityType = facilityType; }
    public String getFacilityName() { return facilityName; }
    public void setFacilityName(String facilityName) { this.facilityName = facilityName; }
    public String getHospitalVisitReason() { return hospitalVisitReason; }
    public void setHospitalVisitReason(String hospitalVisitReason) { this.hospitalVisitReason = hospitalVisitReason; }
    public String getOtherHospitalVisitReason() { return otherHospitalVisitReason; }
    public void setOtherHospitalVisitReason(String otherHospitalVisitReason) { this.otherHospitalVisitReason = otherHospitalVisitReason; }
    public Boolean getHospitalVisitRelatedToBreathing() { return hospitalVisitRelatedToBreathing; }
    public void setHospitalVisitRelatedToBreathing(Boolean hospitalVisitRelatedToBreathing) { this.hospitalVisitRelatedToBreathing = hospitalVisitRelatedToBreathing; }
    public Boolean getSelfVaccination() { return selfVaccination; }
    public void setSelfVaccination(Boolean selfVaccination) { this.selfVaccination = selfVaccination; }
    public Boolean getAnyChildUnderFiveInHousehold() { return anyChildUnderFiveInHousehold; }
    public void setAnyChildUnderFiveInHousehold(Boolean anyChildUnderFiveInHousehold) { this.anyChildUnderFiveInHousehold = anyChildUnderFiveInHousehold; }
    public Integer getNumberOfChildren() { return numberOfChildren; }
    public void setNumberOfChildren(Integer numberOfChildren) { this.numberOfChildren = numberOfChildren; }
    public String getChildVaccinationComplete() { return childVaccinationComplete; }
    public void setChildVaccinationComplete(String childVaccinationComplete) { this.childVaccinationComplete = childVaccinationComplete; }
    public String getChildBirthplace() { return childBirthplace; }
    public void setChildBirthplace(String childBirthplace) { this.childBirthplace = childBirthplace; }
    public Boolean getEnrolledInMHIS() { return enrolledInMHIS; }
    public void setEnrolledInMHIS(Boolean enrolledInMHIS) { this.enrolledInMHIS = enrolledInMHIS; }
    public Boolean getMissedWorkOrSchoolDueToIllness() { return missedWorkOrSchoolDueToIllness; }
    public void setMissedWorkOrSchoolDueToIllness(Boolean missedWorkOrSchoolDueToIllness) { this.missedWorkOrSchoolDueToIllness = missedWorkOrSchoolDueToIllness; }
    public Integer getNumberOfDaysMissedLast30Days() { return numberOfDaysMissedLast30Days; }
    public void setNumberOfDaysMissedLast30Days(Integer numberOfDaysMissedLast30Days) { this.numberOfDaysMissedLast30Days = numberOfDaysMissedLast30Days; }
    public String getMissedWorkReason() { return missedWorkReason; }
    public void setMissedWorkReason(String missedWorkReason) { this.missedWorkReason = missedWorkReason; }
    public String getOtherMissedWorkReason() { return otherMissedWorkReason; }
    public void setOtherMissedWorkReason(String otherMissedWorkReason) { this.otherMissedWorkReason = otherMissedWorkReason; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
