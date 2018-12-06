import Model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        User user = new User();
        int id = 10;
        try {
            user= User.loadUserById(DatabaseConnection.getEfficientConnection(),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user instanceof User){
            System.out.println(user.toString());
        }else{
            System.out.println("User of id = "+id+" not exist.");
        }


        try {
            user.delete(DatabaseConnection.getEfficientConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User[] users=new User[0];
        try {
            users=User.loadAllUsers(DatabaseConnection.getEfficientConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (User user1:users){

            System.out.println(user1.toString());
        }

    }
}
