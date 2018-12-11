import Model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) throws SQLException {
        boolean exit=false;
        Scanner scan = new Scanner(System.in);
        String text;
        menu();

        while (!exit){
            text=scan.nextLine();
            switch (text){
                case "add":{
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
                    menu();
                    break;
                }
                case "edit":{
                    User user = new User();
                    System.out.println("Wprowadz id edytowanego użytkownika");
                    user=User.loadUserById(DatabaseConnection.getEfficientConnection(),Long.valueOf(scan.nextLine()));
                    System.out.println("Wprowadz imię i nazwisko edytowanego użytkownika");
                    user.setUsername(scan.nextLine());
                    System.out.println("Wprowadz email edytowanego użytkownika");
                    user.setEmail(scan.nextLine());
                    System.out.println("Wprowadz hasło edytowanego użytkownika");
                    user.setPassword(scan.nextLine());
                    System.out.println("Wprowadz grupę edytowanego użytkownika");
                    user.setUserGroup_id(Integer.valueOf(scan.nextLine()));
                    user.saveToDB(DatabaseConnection.getEfficientConnection());
                    menu();
                    break;
                }
                case "delete":{
                    User user = new User();
                    System.out.println("Wprowadz id usuwanego użytkownika");
                    user=User.loadUserById(DatabaseConnection.getEfficientConnection(),Long.valueOf(scan.nextLine()));
                    user.delete(DatabaseConnection.getEfficientConnection());
                    menu();
                    break;
                }
                case "quit":{
                    exit=true;
                    break;
                }
                default:{
                    System.out.println("Wybierz prawidłową opcję");
                }

            }


        }
    }

    public static void menu(){
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – dodanie użytkownika,\n" +
                "edit – edycja użytkownika,\n" +
                "delete – usunięcie użytkownika,\n" +
                "quit – zakończenie programu.");
    }
}
