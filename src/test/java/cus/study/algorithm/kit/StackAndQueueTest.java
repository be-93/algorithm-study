package cus.study.algorithm.kit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class StackAndQueueTest {

  @Test
  public void 같은숫자는싫어() throws Exception {
    int[] solution1 = this.같은숫자는싫어_solution(new int[]{1, 1, 3, 3, 0, 1, 1});
    int[] solution2 = this.같은숫자는싫어_solution(new int[]{4, 4, 4, 3, 3});

    assertThat(solution1).containsExactly(1, 3, 0, 1);
    assertThat(solution2).containsExactly(4, 3);
  }

  public int[] 같은숫자는싫어_solution(int[] arr) {

    Deque<Integer> deque = new LinkedList<>();

    for (int number : arr) {
      if (deque.isEmpty() || deque.peekLast() != number) {
        deque.add(number);
      }
    }

    return deque.stream()
        .mapToInt(Integer::intValue)
        .toArray();
  }

  @Test
  public void 올바른괄호() throws Exception {
    boolean solution1 = this.올바른괄호_solution("()()");
    boolean solution2 = this.올바른괄호_solution("(())()");
    boolean solution3 = this.올바른괄호_solution(")()(");
    boolean solution4 = this.올바른괄호_solution("(()(");

    assertThat(solution1).isTrue();
    assertThat(solution2).isTrue();
    assertThat(solution3).isFalse();
    assertThat(solution4).isFalse();
  }

  public boolean 올바른괄호_solution(String s) {
    int count = 0;

    for (int i = 0; i < s.length(); i++) {
      String str = String.valueOf(s.charAt(i));

      count += "(".equals(str) ? 1 : -1;

      if (count < 0) {
        return false;
      }
    }

    if (count != 0) {
      return false;
    }

    return true;
  }

  @Test
  public void 프로세스() throws Exception {
    int solution1 = this.프로세스_solution(new int[]{2, 1, 3, 2}, 2);
    int solution2 = this.프로세스_solution(new int[]{1, 1, 9, 1, 1, 1}, 0);

    assertThat(solution1).isEqualTo(1);
    assertThat(solution2).isEqualTo(5);
  }

  public int 프로세스_solution(int[] priorities, int location) {
    int answer = 0;

    Queue<Integer> deque = Arrays.stream(priorities)
        .boxed()
        .collect(Collectors.toCollection(LinkedList::new));

    return answer;
  }
}

