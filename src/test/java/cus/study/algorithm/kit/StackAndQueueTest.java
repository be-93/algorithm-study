package cus.study.algorithm.kit;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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

    List<Integer> list = Arrays.stream(priorities)
        .boxed()
        .collect(Collectors.toCollection(ArrayList::new));

    int index = location;
    int count = 0;

    while (!list.isEmpty()) {
      Integer number = list.remove(0);
      index--;
      boolean isMoveLast = false;

      for (int i = 0; i < list.size(); i++) {
        if (number < list.get(i)) {
          isMoveLast = true;
          break;
        }
      }

      if (isMoveLast) {
        list.add(number);
        index = index < 0 ? list.size() - 1 : index;
      }

      if (!isMoveLast) {
        count++;
      }

      if (!isMoveLast && index < 0) {
        return count;
      }
    }

    return answer;
  }

  @Test
  void 다리를지나는트럭() {
    int solution1 = this.다리를지나는트럭_solution(2, 10, new int[]{7, 4, 5, 6});
    int solution2 = this.다리를지나는트럭_solution(100, 100, new int[]{10});
    int solution3 = this.다리를지나는트럭_solution(100, 100,
        new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10});

    assertThat(solution1).isEqualTo(8);
    assertThat(solution2).isEqualTo(101);
    assertThat(solution3).isEqualTo(110);
  }

  public int 다리를지나는트럭_solution(int bridge_length, int weight, int[] truck_weights) {
    int answer = 0;

    Queue<Integer> trucks = Arrays.stream(truck_weights)
        .boxed()
        .collect(Collectors.toCollection(LinkedList::new));

    Queue<Integer> bridge = Stream.generate(() -> 0)
        .limit(bridge_length)
        .collect(Collectors.toCollection(LinkedList::new));

    while (!bridge.isEmpty()) {
      answer++;
      bridge.poll();

      int sum = bridge.stream()
          .mapToInt(Integer::intValue)
          .sum();

      int latestTruck = trucks.isEmpty() ? 0 : trucks.peek();

      if (sum + latestTruck > weight) {
        bridge.add(0);
      } else if (!trucks.isEmpty()) {
        bridge.add(trucks.poll());
      }

    }

    return answer;
  }

  @Test
  void 주식가격() {
    int[] solution1 = this.주식가격_solution(new int[]{1, 2, 3, 2, 3});

    assertThat(solution1).containsExactly(4, 3, 1, 1, 0);
  }

  public int[] 주식가격_solution(int[] prices) {
    int[] answer = new int[prices.length];

    for (int i = 0; i < prices.length - 1; i++) {
      int time = 0;
      int price = prices[i];
      for (int j = i + 1; j < prices.length; j++) {
        time++;
        if (price > prices[j]) {
          break;
        }
      }
      answer[i] = time;
    }
    return answer;
  }

}

