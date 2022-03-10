package cus.study.algorithm.lv1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 프로그래머스 - K번째 수
 */

public class Test02 {

    @Test
    public void case_01() {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        int[] expected = {5, 6, 3};

        int[] actual = solution(array, commands);

        assertThat(actual).containsExactly(expected);
    }

    public int[] solution(int[] array, int[][] commands) {

        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int startIndex = command[0] - 1;
            int endIndex = command[1];
            int findIndex = command[2] - 1;

            int[] newArray = Arrays.copyOfRange(array, startIndex, endIndex);
            Arrays.sort(newArray);

            answer[i] = newArray[findIndex];
        }

        return answer;
    }

}
