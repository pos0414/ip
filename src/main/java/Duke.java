import java.util.Scanner;

/**
 * This class is the main class of the program Duke.
 */
public class Duke {

    /**
     * Execute users' requests from user inputs.
     * Continues until user enters "bye".
     */
    public static void executeRequest() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!Parser.parseCommand(line).equals("bye")) {
            System.out.println(Ui.LINE);
            try {
                switch (Parser.parseCommand(line)) {
                case "list":
                    Ui.printTaskList();
                    break;
                case "done":
                    TaskHandler.markTaskAsDone(line);
                    Storage.saveData();
                    break;
                case "deadline":
                    TaskHandler.addDeadline(line);
                    Storage.saveData();
                    break;
                case "event":
                    TaskHandler.addEvent(line);
                    Storage.saveData();
                    break;
                case "todo":
                    TaskHandler.addTodo(line);
                    Storage.saveData();
                    break;
                case "help":
                    Ui.showHelp();
                    break;
                case "delete":
                    TaskHandler.deleteTask(line);
                    Storage.saveData();
                    break;
                case "find":
                    TaskHandler.findTask(line);
                    break;
                default:
                    TaskHandler.handleWrongCommand();
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Ui.LINE);
            if (in.hasNextLine()) {
                line = in.nextLine();
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Storage.loadData();
        Ui.printGreetMessage();
        executeRequest();
        Ui.printFarewellMessage();
    }
}
