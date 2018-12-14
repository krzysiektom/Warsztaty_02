import Model.Exercise;
import Model.Solution;
import Model.User;
import Model.UserGroup;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException {
        boolean exit=false;
        Scanner scan = new Scanner(System.in);
        String text;
        menu();

        while (!exit){
            text=scan.nextLine();
            switch (text){
                case "add":{
                    Solution solution= new Solution();
                    solution.setUser_id(Integer.valueOf(args[0]));
                    Exercise[] exercises = Exercise.loadExerciseNotSolvedByUserId(DatabaseConnection.getEfficientConnection(),Integer.valueOf(args[0]));
                    int[] idExerciseNotSolved =new int[exercises.length];
                    int iterator=0;
                    for (Exercise exercise:exercises) {
                        System.out.println(exercise.toString());
                        idExerciseNotSolved[iterator]=exercise.getId();
                        iterator++;
                    }
                    boolean correctId =false;
                    int inputID=0;
                    System.out.println("Wprowadz id zadania, dla którego chcesz dodać rozwiązanie.");
                    while (!correctId){
                        inputID=Integer.valueOf(scan.nextLine());
                        if (contain(idExerciseNotSolved,inputID)){
                            correctId=true;
                        }else {
                            System.out.println("Rozwiązanie zadania o podanym id już istnieje.\n" +
                                    "Wprowadz prawidłowe id zadania.");
                        }
                    }
                    solution.setExercise_id(inputID);
                    System.out.println("Wprowadz rozwiązanie dla zadania.");
                    solution.setDescription(scan.nextLine());
                    solution.setCreated(Timestamp.valueOf(LocalDateTime.now()));
                    solution.saveToDB(DatabaseConnection.getEfficientConnection());
                    menu();
                    break;
                }
                case "view":{
                    Solution[] solutions=Solution.loadAllByUserId(DatabaseConnection.getEfficientConnection(),Integer.valueOf(args[0]));
                    for (Solution solution: solutions) {
                        System.out.println(solution.toString());
                    }
                    menu();
                    break;
                }
                case "quit":{
                    exit=true;
                    break;
                }
                default:{
                    System.out.println("Wybierz prawidłową opcję.");
                }
            }
        }

    }

    public static void menu(){
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – dodawanie rozwiązania,\n" +
                "view – przeglądanie swoich rozwiązań,\n" +
                "quit – zakończenie programu.");
    }

    public static boolean contain(int[] array, int value){
        for (int i: array) {
            if (i==value){
                return true;
            }
        }
        return false;
    }
}
