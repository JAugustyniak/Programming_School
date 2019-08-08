package DAO;


import classes.Exercise;
import classes.Solution;

import java.sql.*;
import java.util.ArrayList;

import static java.time.LocalDate.now;

public class SolutionDAO {

    private final String URL = "jdbc:mysql://localhost:3306/workshop2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private ArrayList<Solution> solutions = new ArrayList<>();
    private ArrayList<Solution> solutionsByUserId = new ArrayList<>();
    private ArrayList<Solution> solutionsByExerciseId = new ArrayList<>();
    private ArrayList<Exercise> exerciseWithoutSolution = new ArrayList<>();
    private ArrayList<Integer> listOfExIdWithoutSol = new ArrayList<>();

    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution (id, created, updated, description, exercise_id, USERS_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution WHERE id = ?";

    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, USERS_id = ? WHERE id = ?";        //czy na pewno update wszystkich pól

    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";

    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solution";

    private static final String FIND_ALL_BY_USER_ID =
            "SELECT * FROM solution WHERE USERS_id = ?";

    private static final String FIND_ALL_BY_EXERCISE_ID =
            "SELECT * FROM solution WHERE exerciseId = ? ORDER BY updated DESC";

    private static final String FIND_ALL_EXERCISE_WITHOUT_SOLUTION =
            "SELECT id, title, description FROM exercise WHERE id NOT IN (SELECT exercise_id FROM solution WHERE USERs_id = ?)";

    public Solution create(Solution solution) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SOLUTION_QUERY);
            preparedStatement.setInt(1, solution.getId());
            preparedStatement.setDate(2, Date.valueOf(now()));
            preparedStatement.setDate(3, Date.valueOf(now()));
            preparedStatement.setString(4, solution.getDescription());
            preparedStatement.setInt(5, solution.getExerciseId());
            preparedStatement.setInt(6, solution.getUsersId());
            preparedStatement.executeUpdate();
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Solution read(int solutionId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_SOLUTION_QUERY);
            preparedStatement.setInt(1, solutionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("USERS_id"));
                System.out.println(solution.getId() + " " + solution.getCreated() + " " + solution.getUpdated() + " " +
                        solution.getDescription() + " " + solution.getDescription() + " " + solution.getExerciseId() + " " + solution.getUsersId());
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Solution solution) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SOLUTION_QUERY);
            preparedStatement.setDate(1, Date.valueOf(now()));
            preparedStatement.setString(2, solution.getDescription());
            preparedStatement.setInt(3, solution.getExerciseId());
            preparedStatement.setInt(4, solution.getUsersId());
            preparedStatement.setInt(5, solution.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int solutionId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SOLUTION_QUERY);
            preparedStatement.setInt(1, solutionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Solution> findAll() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("USERS_id"));
                solutions.add(solution);
            }
            for (Solution s : solutions) {
                System.out.println(s.getId() + " " + s.getCreated() + " " + s.getUpdated() + " " + s.getDescription() + " " + s.getExerciseId() + " " + s.getUsersId());
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Solution> findAllByUserId(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_USER_ID);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("USERS_id"));
                solutionsByUserId.add(solution);

            }
            for (Solution s : solutionsByUserId) {
                System.out.println(s.getId() + " " + s.getCreated() + " " + s.getUpdated() + " " + s.getDescription() + " " + s.getExerciseId() + " " + s.getUsersId());
            }
            return solutionsByUserId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Solution> findAllByExerciseId(int exerciseId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_EXERCISE_ID);
            preparedStatement.setInt(1, exerciseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getInt("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getInt("exercise_id"));
                solution.setUsersId(resultSet.getInt("USERS_id"));
                solutionsByExerciseId.add(solution);
            }
            for (Solution s : solutionsByExerciseId) {
                System.out.println(s.getId() + " " + s.getCreated() + " " + s.getUpdated() + " " + s.getDescription() + " " + s.getExerciseId() + " " + s.getUsersId());
            }
            return solutionsByExerciseId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Exercise> findAllExerciseWithoutSolution(int userId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EXERCISE_WITHOUT_SOLUTION);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exerciseWithoutSolution.add(exercise);
                listOfExIdWithoutSol.add(exercise.getId());
            }
            for (Exercise ex : exerciseWithoutSolution) {
                System.out.println(ex.getId() + " " + ex.getTitle() + " " + ex.getDescription());
            }
            return exerciseWithoutSolution;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Integer> getListOfExIdWithoutSol() {
        return listOfExIdWithoutSol;
    }
}
