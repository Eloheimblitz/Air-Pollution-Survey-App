package com.airpollution.survey.service;

import com.airpollution.survey.dto.DashboardSummaryResponse;
import com.airpollution.survey.entity.SurveyRecord;
import com.airpollution.survey.repository.SurveyRecordRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {
    private final SurveyRecordRepository repository;
    private final SurveyService surveyService;

    public DashboardService(SurveyRecordRepository repository, SurveyService surveyService) {
        this.repository = repository;
        this.surveyService = surveyService;
    }

    @Transactional(readOnly = true)
    public DashboardSummaryResponse summary(Authentication authentication) {
        List<SurveyRecord> records = repository.findAll(surveyService.specification(Map.of(), authentication));
        long totalHouseholds = records.size();
        long totalRespondents = records.stream().map(SurveyRecord::getRespondentName).filter(v -> v != null && !v.isBlank()).count();
        long studyAreas = records.stream().map(SurveyRecord::getStudyArea).filter(v -> v != null && !v.isBlank()).distinct().count();
        long highRisk = records.stream().filter(r -> "HIGH".equals(r.getRiskLevel()) || "VERY_HIGH".equals(r.getRiskLevel())).count();
        long wood = records.stream().filter(r -> "FIREWOOD".equals(r.getPrimaryCookingFuel())).count();
        long both = records.stream().filter(r -> "BOTH".equals(r.getCurrentSimpleCookingCategory())).count();
        long smokers = records.stream()
                .filter(r -> Boolean.TRUE.equals(r.getSmokingStatus()) || safeInt(r.getNumberOfSmokersInHousehold()) > 0)
                .count();
        long respiratorySymptoms = records.stream().filter(this::hasRespiratorySymptoms).count();
        long hospitalVisits = records.stream().filter(r -> Boolean.TRUE.equals(r.getVisitedHospital())).count();
        BigDecimal averageMissedDays = averageMissedDays(records);

        return new DashboardSummaryResponse(
                totalHouseholds,
                totalRespondents,
                studyAreas,
                highRisk,
                wood,
                both,
                smokers,
                respiratorySymptoms,
                hospitalVisits,
                averageMissedDays,
                countBy(records, SurveyRecord::getStudyArea),
                countBy(records, SurveyRecord::getRiskLevel),
                countBy(records, SurveyRecord::getPrimaryCookingFuel),
                commonSymptoms(records),
                records.stream().collect(Collectors.groupingBy(r -> Boolean.TRUE.equals(r.getVisitedHospital()) ? "Yes" : "No",
                        LinkedHashMap::new, Collectors.counting()))
        );
    }

    private Map<String, Long> countBy(List<SurveyRecord> records, Function<SurveyRecord, String> getter) {
        return records.stream()
                .collect(Collectors.groupingBy(r -> valueOrUnknown(getter.apply(r)), LinkedHashMap::new, Collectors.counting()));
    }

    private Map<String, Long> commonSymptoms(List<SurveyRecord> records) {
        Map<String, Long> counts = new LinkedHashMap<>();
        counts.put("Dry cough", records.stream().filter(r -> reported(r.getDryCough())).count());
        counts.put("Phlegm cough", records.stream().filter(r -> reported(r.getPhlegmCough())).count());
        counts.put("Wheezing", records.stream().filter(r -> reported(r.getWheezing())).count());
        counts.put("Breathlessness", records.stream().filter(r -> reported(r.getBreathlessness())).count());
        counts.put("Chest discomfort", records.stream().filter(r -> reported(r.getChestDiscomfort())).count());
        counts.put("Eye irritation", records.stream().filter(r -> reported(r.getEyeIrritation())).count());
        return counts;
    }

    private boolean hasRespiratorySymptoms(SurveyRecord r) {
        return reported(r.getDryCough()) || reported(r.getPhlegmCough()) || reported(r.getWheezing())
                || reported(r.getBreathlessness()) || reported(r.getChestDiscomfort());
    }

    private boolean reported(String value) {
        return value != null && !value.equalsIgnoreCase("NEVER");
    }

    private BigDecimal averageMissedDays(List<SurveyRecord> records) {
        return records.stream()
                .map(SurveyRecord::getNumberOfDaysMissedLast30Days)
                .filter(v -> v != null && v >= 0)
                .map(BigDecimal::valueOf)
                .reduce(BigDecimal::add)
                .map(sum -> sum.divide(BigDecimal.valueOf(records.stream()
                        .filter(r -> r.getNumberOfDaysMissedLast30Days() != null).count()), 2, RoundingMode.HALF_UP))
                .orElse(BigDecimal.ZERO);
    }

    private int safeInt(Integer value) {
        return value == null ? 0 : value;
    }

    private String valueOrUnknown(String value) {
        return value == null || value.isBlank() ? "UNKNOWN" : value;
    }
}
