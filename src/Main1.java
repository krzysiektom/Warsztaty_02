import Model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String text;
        viewAllUsers();
        viewMenu();

        while (!exit) {
            text = scan.nextLine();
            switch (text) {
                case "add": {
                    User user = new User();
                    System.out.println("Wprowadz imię i nazwisko nowego użytkownika");
                    user.setUsername(scan.nextLine());
                    System.out.println("Wprowadz email nowego użytkownika");
                    user.setEmail(scan.nextLine());
                    System.out.println("Wprowadz hasło nowego użytkownika");
                    user.setPassword(scan.nextLine());
                    System.out.println("Wprowadz grupę nowego użytkownika");
                    user.setUserGroup_id(Integer.valueOf(scan.nextLine()));
                    user.saveToDB(DatabaseConnection.getEfficientConnection());
                    viewAllUsers();
                    viewMenu();
                    break;
                }
                case "edit": {
                    System.out.println("Wprowadz id edytowanego użytkownika");
                    User user = User.loadUserById(DatabaseConnection.getEfficientConnection(), Long.valueOf(scan.nextLine()));
                    System.out.println("Wprowadz imię i nazwisko edytowanego użytkownika");
                    user.setUsername(scan.nextLine());
                    System.out.println("Wprowadz email edytowanego użytkownika");
                    user.setEmail(scan.nextLine());
                    System.out.println("Wprowadz hasło edytowanego użytkownika");
                    user.setPassword(scan.nextLine());
                    System.out.println("Wprowadz grupę edytowanego użytkownika");
                    user.setUserGroup_id(Integer.valueOf(scan.nextLine()));
                    user.saveToDB(DatabaseConnection.getEfficientConnection());
                    viewAllUsers();
                    viewMenu();
                    break;
                }
                case "delete": {
                    System.out.println("Wprowadz id usuwanego użytkownika");
                    User user = User.loadUserById(DatabaseConnection.getEfficientConnection(), Long.valueOf(scan.nextLine()));
                    user.delete(DatabaseConnection.getEfficientConnection());
                    viewAllUsers();
                    viewMenu();
                    break;
                }
                case "quit": {
                    exit = true;
                    break;
                }
                default: {
                    System.out.println("Wybierz prawidłową opcję");
                }
            }
        }
    }

    private static void viewAllUsers() throws SQLException {
        User[] users = User.loadAllUsers(DatabaseConnection.getEfficientConnection());
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void viewMenu() {
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – dodanie użytkownika,\n" +
                "edit – edycja użytkownika,\n" +
                "delete – usunięcie użytkownika,\n" +
                "quit – zakończenie programu.");
    }
}
