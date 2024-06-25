package cus.study.algorithm.lv1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 프로그래머스 - 2022 KAKAO BLIND RECRUITMENT 신고 결과 받기
 * <p>
 * 정답 : [2,1,1,0] String[] id_list = {"muzi", "frodo", "apeach", "neo"}; String[] report = {"muzi
 * frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}; int k = 2;
 * <p>
 * 정답 : [0, 0] String[] id_list = {"con", "ryan"}; String[] report = {"ryan con", "ryan con", "ryan
 * con", "ryan con"}; int k = 3;
 */
public class Test01 {

  @Test
  public void case_01() {

    String[] id_list = {"muzi", "frodo", "apeach", "neo"};
    String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    int k = 2;

    int[] expected = {2, 1, 1, 0};

    int[] actual = solution(id_list, report, k);

    assertThat(actual).containsExactly(expected);
  }

  @Test
  public void case_02() {

    String[] id_list = {"con", "ryan"};
    String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
    int k = 3;

    int[] expected = {0, 0};

    int[] actual = solution(id_list, report, k);

    assertThat(actual).containsExactly(expected);
  }

  public int[] solution(String[] id_list, String[] report, int k) {
    int[] answer = new int[id_list.length];

    Reports reports = new Reports(Arrays.asList(report));

    for (int i = 0; i < answer.length; i++) {
      answer[i] = reports.getReportMailCount(id_list[i], k);
    }

    return answer;
  }

  class Reports {

    private List<Report> reports = new ArrayList<>();

    public Reports(List<String> reporters) {
      for (String reporter : reporters) {
        String reporterName = reporter.split(" ")[0];
        String targetName = reporter.split(" ")[1];
        Report report = getReport(targetName);

        report.addReporters(reporterName);
      }
    }

    private Report getReport(String targetName) {
      Report findReport = reports.stream()
          .filter(it -> it.getTarget().equals(targetName))
          .findFirst()
          .orElseGet(() -> {
            Report report = new Report(targetName);
            this.reports.add(report);
            return report;
          });

      return findReport;
    }

    public int getReportMailCount(String name, int count) {
      return (int) reports.stream()
          .filter(it -> it.isExistsReporters(name) && it.getReportListSize() >= count)
          .count();
    }
  }

  class Report {

    private String target;
    private List<String> reporters = new ArrayList<>();

    public Report(String target) {
      this.target = target;
    }

    public void addReporters(String name) {
      boolean isExists = isExistsReporters(name);

      if (!isExists) {
        reporters.add(name);
      }
    }

    public boolean isExistsReporters(String name) {
      return reporters.stream()
          .anyMatch(name::equals);
    }

    public String getTarget() {
      return target;
    }

    public int getReportListSize() {
      return reporters.size();
    }
  }

}
