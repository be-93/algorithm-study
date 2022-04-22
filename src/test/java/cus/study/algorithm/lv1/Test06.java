package cus.study.algorithm.lv1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로그래머스 - 없는 숫자 더하기
 */

public class Test06 {

    @Test
    public void case_01() {
        int[] numbers = {1,2,3,4,6,7,8,0};
        int expected = 14;

        int actual = solution(numbers);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void case_02() {
        int[] numbers = {5,8,4,0,6,7,9};
        int expected = 6;

        int actual = solution(numbers);

        assertThat(actual).isEqualTo(expected);
    }

    public int solution(int[] numbers) {
        return 45 - Arrays.stream(numbers).sum();
    }
}
