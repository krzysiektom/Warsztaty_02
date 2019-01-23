package Model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private int userGroup_id;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserGroup_id() {
        return userGroup_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserGroup_id(int userGroup_id) {
        this.userGroup_id = userGroup_id;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userGroup_id=" + userGroup_id +
                '}';
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO users(username, email, password, usergroup_id) VALUES (?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.userGroup_id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getLong(1);
            }
        } else {
            String sql = "UPDATE users SET username=?, email=?, password=?, usergroup_id=? where id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, this.username);
            preparedStatement.setString(2, this.email);
            preparedStatement.setString(3, this.password);
            preparedStatement.setInt(4, this.userGroup_id);
            preparedStatement.setLong(5, this.id);
            preparedStatement.executeUpdate();
        }
    }

    static public User loadUserById(Connection conn, long id) throws SQLException {
        String sql = "SELECT * FROM users where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getLong("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.userGroup_id = resultSet.getInt("usergroup_id");
            return loadedUser;
        }
        return null;
    }

    static public User[] loadAllUsers(Connection conn) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getLong("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.userGroup_id = resultSet.getInt("usergroup_id");
            users.add(loadedUser);
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM users WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    static public User[] loadAllByGroupId(Connection conn,int userGroup_id) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users WHERE userGroup_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setLong(1, userGroup_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.id = resultSet.getLong("id");
            loadedUser.username = resultSet.getString("username");
            loadedUser.password = resultSet.getString("password");
            loadedUser.email = resultSet.getString("email");
            loadedUser.userGroup_id = resultSet.getInt("usergroup_id");
            users.add(loadedUser);
        }
        User[] uArray = new User[users.size()];
        uArray = users.toArray(uArray);
        return uArray;
    }

}