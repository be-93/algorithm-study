package cus.study.algorithm.lv1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로그래머스 - 2018 KAKAO BLIND RECRUITMENT 1차 다트게임 Single(S) 1제곱, Double(D) 2제곱, Triple(T) 3제곱 스타상(*)
 * 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배 아차상(#) 당첨 시 해당 점수는 마이너스
 */

public class Test03 {

  @Test
  public void case_01() {
    String dartResult = "1S2D*3T";
    int expected = 37;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void case_02() {
    String dartResult = "1D2S#10S";
    int expected = 9;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void case_03() {
    String dartResult = "1D2S0T";
    int expected = 3;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void case_04() {
    String dartResult = "1S*2T*3S";
    int expected = 23;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void case_05() {
    String dartResult = "1D#2S*3S";
    int expected = 5;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void case_06() {
    String dartResult = "1T2D3D#";
    int expected = -4;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void case_07() {
    String dartResult = "1D2S3T*";
    int expected = 59;

    int actual = solution(dartResult);

    assertThat(actual).isEqualTo(expected);
  }

  public int solution(String dartResult) {
    Numbers numbers = getNumbers(dartResult);

    return numbers.getSumNumbers();
  }

  private Numbers getNumbers(String dartResult) {
    Numbers numbers = new Numbers();
    String temp = "";
    for (int i = 0; i < dartResult.length(); i++) {
      if (!Character.isDigit(dartResult.charAt(i))) {
        if (temp.length() > 0) {
          numbers.addNumber(temp);
          temp = "";
        }

        Number lastNumber = numbers.getLastNumber();
        lastNumber.changeCalcOption(dartResult.charAt(i), numbers);
        continue;
      }
      temp += dartResult.charAt(i);

    }
    return numbers;
  }

  class Numbers {

    private List<Number> numbers = new ArrayList<>();

    public void addNumber(String number) {
      numbers.add(new Number(Integer.parseInt(number)));
    }

    public Number getLastNumber() {
      return numbers.get(numbers.size() - 1);
    }

    public Number getPreviousNumber(Number number) {
      return numbers.get(numbers.indexOf(number) - 1);
    }

    private boolean isFirstElement(Number number) {
      return numbers.indexOf(number) == 0;
    }

    public int getSumNumbers() {
      return numbers.stream().mapToInt(Number::getNumber).sum();
    }
  }

  class Number {

    private int number;

    public Number(int number) {
      this.number = number;
    }

    public int getNumber() {
      return number;
    }

    public void multiply(int multiply) {
      number = number * multiply;
    }

    public void changeCalcOption(char charAt, Numbers numbers) {
      String option = String.valueOf(charAt);

      switch (option) {
        case "S":
          number = (int) Math.pow(number, 1);
          break;
        case "D":
          number = (int) Math.pow(number, 2);
          break;
        case "T":
          number = (int) Math.pow(number, 3);
          break;
        case "*":
          number = number * 2;
          if (!numbers.isFirstElement(this)) {
            Number previousNumber = numbers.getPreviousNumber(this);
            previousNumber.multiply(2);
          }
          break;
        case "#":
          number = number * -1;
          break;
      }

    }
  }

}
