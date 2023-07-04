package W1GenericTypes.Project_W1_Java;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    CustomQueue<Person> queue= new CustomQueue<>(new ArrayDeque<>(), new ArrayDeque<>());
    CommandPersonProcessor commandPersonProcessor = new CommandPersonProcessor(queue);

    Scanner scanner = new Scanner(System.in);

    System.out.println("To stand to store queue enter \"ADD PERSON(name)\"" + "or ADD PERSON(name,VIP) if you are Vip" +
        "\"LEAVE PERSON(name)\"");

    while (scanner.hasNext()) {
      String command= scanner.nextLine();

      if("Exit".toLowerCase().equals(command.toLowerCase())){
        break;
      }
      commandPersonProcessor.processCommand(command);
    }

//    List<String> commandsList = List.of(
//        "ADD PERSON(Tomasz_Romański)",
//        "ADD PERSON(Rafał_Adamczuk)",
//        "ADD PERSON(Tomasz_Romański)",
//        "ADD PERSON(Mariusz_Wilczek,VIP)",
//        "ADD PERSON(Mariusz_Wilczek,VIP)",
//        "LEAVE PERSON(Mariusz_Wilczek)",
////        "ADD PERSON(Zbigniew_Ratownik)",
//        "PROCESS",
//        "PROCESS"
////        "PROCESS",
////        "LEAVE PERSON(Tomasz_Romański_2)"
////        "PROCESS",
////        "PROCESS",
////        "PROCESS"
//    );
//
//    for (String command : commandsList) {
//      commandPersonProcessor.processCommand(command);
//    }

  }
}
