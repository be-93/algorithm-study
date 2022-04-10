package cus.study.algorithm.lv1;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로그래머스 - 내적
 */

public class Test04 {

    @Test
    public void case_01() {
        int[] a = {1, 2, 3, 4};
        int[] b = {-3, -1, 0, 2};
        int expected = 3;

        int actual = solution(a, b);

        assertThat(actual).isEqualTo(expected);
    }

    public int solution(int[] a, int[] b) {
        int answer = 0;

        for (int i = 0; i < a.length; i++) {
            answer += multiply(a[i], b[i]);
        }
        return answer;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

}
