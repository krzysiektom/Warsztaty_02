import Model.User;
import Model.UserGroup;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("test");
        UserGroup userGroup = new UserGroup("group3");
        userGroup.saveToDB(DatabaseConnection.getEfficientConnection());
        System.out.println(userGroup.toString());
        UserGroup[] userGroups = new UserGroup[0];
        userGroups= UserGroup.loadAllUserGroups(DatabaseConnection.getEfficientConnection());
        for (UserGroup userGroup1: userGroups) {
            System.out.println(userGroup1);
        }
        userGroup.delete(DatabaseConnection.getEfficientConnection());
        userGroups= UserGroup.loadAllUserGroups(DatabaseConnection.getEfficientConnection());
        for (UserGroup userGroup1: userGroups) {
            System.out.println(userGroup1);
        }
        userGroup = UserGroup.loadUserGroupById(DatabaseConnection.getEfficientConnection(),3);
        userGroup.delete(DatabaseConnection.getEfficientConnection());
        userGroups= UserGroup.loadAllUserGroups(DatabaseConnection.getEfficientConnection());
        for (UserGroup userGroup1: userGroups) {
            System.out.println(userGroup1);
        }

    }
}
