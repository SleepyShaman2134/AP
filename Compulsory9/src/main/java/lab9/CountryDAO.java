package lab9;

import java.sql.*;

public class CountryDAO {
    public static void create(String name, Integer code, String continent) throws SQLException {
        Connection connection = Database.createConnection();
        try (PreparedStatement prepareStatement = connection.prepareStatement(
                "INSERT INTO countries (name, code, continent) values ('"+ name +"',"+ code +",'"+ continent +"')")) {
            prepareStatement.executeUpdate();
        }
    }
    public static Integer findByName(String name) throws SQLException {
        Connection connection = Database.createConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                     "select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public static String findById(int id) throws SQLException {
        Connection connection = Database.createConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                     "select name from countries where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
