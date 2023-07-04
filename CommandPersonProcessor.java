package W1GenericTypes.Project_W1_Java;

public class CommandPersonProcessor {

  private final CustomQueue<Person> customQueue;


  public CommandPersonProcessor(CustomQueue<Person> customQueue) {
    this.customQueue = customQueue;
  }

  public void processCommand(String command) {

    if (command.contains("ADD PERSON")) {
      handleAddPerson(command, command.contains("VIP"));
    } else if (command.contains("LEAVE PERSON")) {
      handleLeavePerson(command);
    } else if (command.contains("PROCESS")) {
      handleProcess(command);
    } else {
      throw new RuntimeException("Unknown process command: " + command);
    }
  }

  private static Person createLeavingPerson(String command) {
    String personId = command
        .replace("LEAVE PERSON(", "")
        .replace(")", "");

    String[] splitId = personId.split("_");

    if (splitId.length == 2) {
      return new Person(splitId[0], splitId[1], 1);

    } else if (splitId.length == 3) {
      return new Person(splitId[0], splitId[1], Integer.valueOf(splitId[2]));
    } else {
      throw new IllegalArgumentException("Illegal argument:" + command);
    }
  }

  private void handleAddPerson(String command, boolean vip) {
    System.out.println(command);
    Person incomingPerson = createIncomingPerson(command, vip);
    if (vip) {
      customQueue.addVipPerson(incomingPerson);
    } else {
      customQueue.addNewPerson(incomingPerson);
    }
  }

  private Person createIncomingPerson(String command, Boolean Vip) {
    boolean isVip = Vip;

    String personKey = command
        .replace("ADD PERSON(", "")
        .replace(isVip ? ",VIP)" : ")", "");

//    int firstIndex = command.indexOf("(") + 1;
//    int secondIndex = command.indexOf(")");
//    String personKey = command.substring(firstIndex, secondIndex);

    String[] splitResult = personKey.split("_");

    if (splitResult.length == 2) {
      Integer counter = customQueue.getAndIncrementPersonCounter(personKey);
      return new Person(splitResult[0], splitResult[1], counter, isVip);
    } else {
      throw new IllegalArgumentException("Illegal argument:" + command);
    }

  }

  private void handleProcess(String command) {
    System.out.println(command);
    customQueue.enter();
  }

  private void handleLeavePerson(String command) {
    System.out.println(command);
    Person person = createLeavingPerson(command);
    customQueue.leave(person);
  }
}
