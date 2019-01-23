package Model;

import java.sql.*;
import java.util.ArrayList;

public class Solution {
    private int id;
    private Timestamp created;
    private Timestamp updated;
    private String description;
    private int exercise_id;
    private int user_id;

    public Solution(Timestamp created, Timestamp updated, String description, int exercise_id, int user_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public Solution() {
    }

    public int getId() {
        return id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public Timestamp getupdated() {
        return updated;
    }

    public String getDescription() {
        return description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public int getuser_id() {
        return user_id;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public void setupdated(Timestamp updated) {
        this.updated = updated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void saveToDB(Connection conn) throws SQLException {
        if (this.id == 0) {
            String sql = "INSERT INTO solutions(created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
            String[] generatedColumns = {"ID"};
            PreparedStatement preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setTimestamp(1, this.created);
            preparedStatement.setTimestamp(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise_id);
            preparedStatement.setInt(5, this.user_id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } else {
            String sql = "update solutions SET created=?, updated=?, description=? exercise_id=?, user_id=? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setTimestamp(1, this.created);
            preparedStatement.setTimestamp(2, this.updated);
            preparedStatement.setString(3, this.description);
            preparedStatement.setInt(4, this.exercise_id);
            preparedStatement.setInt(5, this.user_id);
            preparedStatement.setInt(6, this.id);
            preparedStatement.executeUpdate();
        }
    }

    static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM solutions where id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getTimestamp("created");
            loadedSolution.updated = resultSet.getTimestamp("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.user_id = resultSet.getInt("user_id");
            return loadedSolution;
        }
        return null;
    }

    static public Solution[] loadAllsolution(Connection conn) throws SQLException {
        ArrayList<Solution> solution = new ArrayList<Solution>();
        String sql = "SELECT * FROM solutions";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getTimestamp("created");
            loadedSolution.updated = resultSet.getTimestamp("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.user_id = resultSet.getInt("user_id");
            solution.add(loadedSolution);
        }
        Solution[] uArray = new Solution[solution.size()];
        uArray = solution.toArray(uArray);
        return uArray;
    }

    public void delete(Connection conn) throws SQLException {
        if (this.id != 0) {
            String sql = "DELETE FROM solution WHERE id=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, this.id);
            preparedStatement.executeUpdate();
            this.id = 0;
        }
    }

    static public Solution[] loadAllByUserId(Connection conn, int id) throws SQLException {
        ArrayList<Solution> solution = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE user_id=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getTimestamp("created");
            loadedSolution.updated = resultSet.getTimestamp("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.user_id = resultSet.getInt("user_id");
            solution.add(loadedSolution);
        }
        Solution[] uArray = new Solution[solution.size()];
        uArray = solution.toArray(uArray);
        return uArray;
    }

    static public Solution[] loadAllByExerciseId(Connection conn, int id) throws SQLException {
        ArrayList<Solution> solution = new ArrayList<Solution>();
        String sql = "SELECT * FROM solution WHERE exercise_id=? order by created";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.id = resultSet.getInt("id");
            loadedSolution.created = resultSet.getTimestamp("created");
            loadedSolution.updated = resultSet.getTimestamp("updated");
            loadedSolution.description = resultSet.getString("description");
            loadedSolution.exercise_id = resultSet.getInt("exercise_id");
            loadedSolution.user_id = resultSet.getInt("users_id");
            solution.add(loadedSolution);
        }
        Solution[] uArray = new Solution[solution.size()];
        uArray = solution.toArray(uArray);
        return uArray;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created=" + created +
                ", updated=" + updated +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", user_id=" + user_id +
                '}';
    }
}