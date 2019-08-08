package DAO;


import classes.Exercise;

import java.sql.*;
import java.util.ArrayList;

public class ExerciseDAO {

    private final String URL = "jdbc:mysql://localhost:3306/workshop2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private ArrayList<Exercise> listOfExercise = new ArrayList<>();

    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise (id, title, description) VALUES (?, ?, ?)";

    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercise WHERE id = ?";

    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ? WHERE id = ?";

    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";

    private static final String FIND_ALL_EXERCISE_QUERY =
            "SELECT * FROM exercise";

    public Exercise create(Exercise exercise){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EXERCISE_QUERY);
            preparedStatement.setInt(1, exercise.getId());
            preparedStatement.setString(2, exercise.getTitle());
            preparedStatement.setString(3, exercise.getDescription());
            preparedStatement.executeUpdate();

            /*ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                exercise.setId(resultSet.getInt(1));  // pobieramy wstawiony do bazy identyfikator,
                                                                // a następnie ustawiamy id obiektu user
            }*/

            return exercise;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Exercise read(int exerciseId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_EXERCISE_QUERY);
            preparedStatement.setInt(1, exerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(exerciseId);
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                System.out.println(exercise.getId() + " " + exercise.getTitle() + " " + exercise.getDescription());
                return exercise;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Nie wczytano exercise o id " + exerciseId);
        return null;
    }

    public void update(Exercise exercise) {

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXERCISE_QUERY);
            preparedStatement.setString(1, exercise.getTitle());
            preparedStatement.setString(2, exercise.getDescription());
            preparedStatement.setInt(3, exercise.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXERCISE_QUERY);
            preparedStatement.setInt(1, exerciseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Exercise> findAll() {

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EXERCISE_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                listOfExercise.add(exercise);
            }
            for (Exercise ex : listOfExercise) {
                System.out.println(ex.getId() + " " + ex.getTitle() + " " + ex.getDescription());
            }
            return listOfExercise;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Exercise> getListOfExercise() {
        return listOfExercise;
    }
}
