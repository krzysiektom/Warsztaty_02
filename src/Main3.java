import Model.Exercise;
import Model.UserGroup;

import java.sql.SQLException;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) throws SQLException {
        boolean exit=false;
        Scanner scan = new Scanner(System.in);
        String text;
        menu();

        while (!exit){
            text=scan.nextLine();
            switch (text){
                case "add":{
                    UserGroup userGroup = new UserGroup();
                    System.out.println("Wprowadz nazwę nowej grupy");
                    userGroup.setName(scan.nextLine());
                    userGroup.saveToDB(DatabaseConnection.getEfficientConnection());
                    menu();
                    break;
                }
                case "edit":{
                    UserGroup userGroup = new UserGroup();
                    System.out.println("Wprowadz id edytowanej grupy");
                    userGroup= UserGroup.loadUserGroupById(DatabaseConnection.getEfficientConnection(),Integer.valueOf(scan.nextLine()));
                    System.out.println("Wprowadz nazwę edytowanej grupy");
                    userGroup.setName(scan.nextLine());
                    userGroup.saveToDB(DatabaseConnection.getEfficientConnection());
                    menu();
                    break;
                }
                case "delete":{
                    UserGroup userGroup = new UserGroup();
                    System.out.println("Wprowadz id usuwanej grupy");
                    userGroup= UserGroup.loadUserGroupById(DatabaseConnection.getEfficientConnection(),Integer.valueOf(scan.nextLine()));
                    userGroup.delete(DatabaseConnection.getEfficientConnection());
                    menu();
                    break;
                }
                case "quit":{
                    System.out.println("quit");
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
                "add – dodanie grupy,\n" +
                "edit – edycja grupy,\n" +
                "delete – usunięcie grupy,\n" +
                "quit – zakończenie programu.");
    }

}
