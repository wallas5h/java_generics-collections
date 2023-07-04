package W1GenericTypes.Project_W1_Java;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class CustomQueue<T> {

  private final Deque<T> queue;
  private final Deque<T> queueVip;
  private Map<String, Integer> counterMap = new HashMap<>();

  public CustomQueue(Deque<T> queue, Deque<T> queueVip) {
    this.queue = queue;
    this.queueVip = queueVip;
  }

  Integer getAndIncrementPersonCounter(String key) {
    Integer tempCounter = counterMap.getOrDefault(key, 0);
    counterMap.put(key, ++tempCounter);
    return tempCounter;
  }

  public void addNewPerson(T person) {
    boolean isAdded = queue.offer(person);
    System.out.printf("%s came to the queue:  %s%n", person, isAdded);
    printTotalQueue();
  }
  public void addVipPerson(T person) {
    boolean isAdded = queueVip.offer(person);
    System.out.printf("%s came to the queue:  %s%n", person, isAdded);
    printTotalQueue();
//    System.out.println(queue);
  }

  public void enter() {
    if(!queueVip.isEmpty()) {
      T itemEntered = queueVip.poll();
      handleEnterQueue(itemEntered);
    }
    if(queueVip.isEmpty()) {
      T itemEntered = queue.poll();
      handleEnterQueue(itemEntered);
    }
    if (queue.isEmpty()) {
      System.out.println("no items in the queue.");
    }
  }

  private void handleEnterQueue(T itemEntered) {
    System.out.printf("Processing queue: %s arrived at the store.%n", itemEntered);
    printTotalQueue();
    System.out.println();
  }

  public void leave(T item) {
    if (queue.contains(item)) {
      System.out.printf("Leaving queue: %s", item);
      queue.remove(item);
      printTotalQueue();
    } else {
      System.out.println(item + " is not in the queue\n");
    }

  }

  private void printTotalQueue() {
    ArrayList<T> totalList= new ArrayList<>(queueVip);
    totalList.addAll(queue);
    System.out.println("Queue: " +totalList);
    System.out.println();
  }

}

// TODO 28 min

