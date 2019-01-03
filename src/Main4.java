import Model.Exercise;
import Model.Solution;
import Model.User;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        String text;
        viewAllSolutions();
        viewMenu();

        while (!exit) {
            text = scan.nextLine();
            switch (text) {
                case "add": {
                    Solution solution = new Solution();
                    User[] users = User.loadAllUsers(DatabaseConnection.getEfficientConnection());
                    for (User user : users) {
                        System.out.println(user.toString());
                    }
                    System.out.println("Wprowadz id użytkownika, dla którego chcesz dodać rozwiązanie");
                    solution.setUser_id(Integer.valueOf(scan.nextLine()));
                    Exercise[] exercises = Exercise.loadAllExercise(DatabaseConnection.getEfficientConnection());
                    for (Exercise exercise : exercises) {
                        System.out.println(exercise.toString());
                    }
                    System.out.println("Wprowadz id zadania, dla którego chcesz dodać rozwiązanie");
                    solution.setExercise_id(Integer.valueOf(scan.nextLine()));
                    solution.setCreated(Timestamp.valueOf(LocalDateTime.now()));
                    solution.saveToDB(DatabaseConnection.getEfficientConnection());
                    viewAllSolutions();
                    viewMenu();
                    break;
                }
                case "view": {
                    Solution[] solutions = new Solution[0];
                    System.out.println("Wprowadz id użytkownika, którego chcesz zobaczyć rozwiązania");
                    solutions = Solution.loadAllByUserId(DatabaseConnection.getEfficientConnection(), Integer.valueOf(scan.nextLine()));
                    for (Solution solution : solutions) {
                        System.out.println(solution.toString());
                    }
                    viewAllSolutions();
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

    public static void viewAllSolutions() throws SQLException {
        Solution[] solutions = Solution.loadAllsolution(DatabaseConnection.getEfficientConnection());
        for (Solution solution : solutions) {
            System.out.println(solution.toString());
        }
    }

    public static void viewMenu() {
        System.out.println("Wybierz jedną z opcji:\n" +
                "add – przypisywanie zadań do użytkowników,\n" +
                "view – przeglądanie rozwiązań danego użytkownika,\n" +
                "quit – zakończenie programu.");
    }
}
