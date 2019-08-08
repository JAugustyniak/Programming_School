package DAO;


import classes.Group;

import java.sql.*;
import java.util.ArrayList;

public class GroupDAO {

    private final String URL = "jdbc:mysql://localhost:3306/workshop2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "coderslab";

    private ArrayList<Group> groups = new ArrayList<>();

    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO user_group (id, name) VALUES (?, ?)";

    private static final String READ_GROUP_QUERY =
            "SELECT * FROM user_group WHERE id = ?";

    private static final String UPDATE_GROUP_QUERY =
            "UPDATE user_group SET name = ? WHERE id = ?";

    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";

    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT * FROM user_group";

    public Group create(Group group) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_GROUP_QUERY);
            preparedStatement.setInt(1, group.getId());
            preparedStatement.setString(2, group.getName());
            preparedStatement.executeUpdate();
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Group read(int groupId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_GROUP_QUERY);
            preparedStatement.setInt(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                System.out.println(group.getId() + " " + group.getName());
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Group group) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUP_QUERY);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP_QUERY);
            preparedStatement.setInt(1, groupId);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Group> findAll() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups.add(group);
            }

            for (Group g : groups) {
                System.out.println(g.getId() + " " + g.getName());
            }

            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
