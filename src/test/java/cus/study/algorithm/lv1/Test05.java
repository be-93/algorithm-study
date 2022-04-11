package cus.study.algorithm.lv1;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로그래머스 - 음양 더하기
 */

public class Test05 {

    @Test
    public void case_01() {
        int[] absolutes = {4, 7, 12};
        boolean[] signs = {true, false, true};
        int expected = 9;

        int actual = solution(absolutes, signs);

        assertThat(actual).isEqualTo(expected);
    }

    public int solution(int[] absolutes, boolean[] signs) {
        return IntStream.range(0, absolutes.length)
                .map(index -> !signs[index] ? absolutes[index] * -1 : absolutes[index])
                .sum();
    }
}
