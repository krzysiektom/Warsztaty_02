import Model.User;
import Model.UserGroup;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        User[] user = new User[0];
        user=User.loadAllUsers(DatabaseConnection.getEfficientConnection());
        for (User user1: user) {
            System.out.println(user1);
        }


    }
}
