import Model.Exercise;
import Model.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws SQLException {
        boolean exit=false;
        Scanner scan = new Scanner(System.in);
        String text;
        viewAllExercises();
        viewMenu();

        while (!exit){
            text=scan.nextLine();
            switch (text){
                case "add":{
                    Exercise exercise = new Exercise();
                    System.out.println("Wprowadz tytuł nowego zadania");
                    exercise.setTitle(scan.nextLine());
                    System.out.println("Wprowadz opis nowego zadania");
                    exercise.setDescription(scan.nextLine());
                    exercise.saveToDB(DatabaseConnection.getEfficientConnection());
                    viewAllExercises();
                    viewMenu();
                    break;
                }
                case "edit":{
                    Exercise exercise = new Exercise();
                    System.out.println("Wprowadz id edytowanego zadania");
                    exercise=Exercise.loadExerciseById(DatabaseConnection.getEfficientConnection(),Integer.valueOf(scan.nextLine()));
                    System.out.println("Wprowadz tytuł edytowanego zadania");
                    exercise.setTitle(scan.nextLine());
                    System.out.println("Wprowadz opis edytowanego zadania");
                    exercise.setDescription(scan.nextLine());
                    exercise.saveToDB(DatabaseConnection.getEfficientConnection());
                    viewAllExercises();
                    viewMenu();
                    break;
                }
                case "delete":{
                    Exercise exercise = new Exercise();
                    System.out.println("Wprowadz id usuwanego zadania");
                    exercise=Exercise.loadExerciseById(DatabaseConnection.getEfficientConnection(),Integer.valueOf(scan.nextLine()));
                    exercise.delete(DatabaseConnection.getEfficientConnection());
                    viewAllExercises();
                    viewMenu();
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

    public static void viewAllExercises() throws SQLException {
        Exercise[] exercises=Exercise.loadAllExercise(DatabaseConnection.getEfficientConnection());
        for (Exercise exercise:exercises) {
            System.out.println(exercise);
        }
    }

    public static void viewMenu(){
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – dodanie zadania,\n" +
                "edit – edycja zadania,\n" +
                "delete – usunięcie zadania,\n" +
                "quit – zakończenie programu.");
    }
}
