package lab9;

import javax.persistence.Entity;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class CitiesDAO implements Runnable{
    private volatile boolean check=true;
    private int id1=0;
    private Object twin=0;
    private String cityName=null;
    private Connection connection=null;

    public CitiesDAO(int id, String name, int twin, Connection connection)
    {
        id1=id;
        cityName=name;
        this.twin =twin;
        this.connection =connection;
    }

    public static Double findLatitudeById(int id) throws SQLException {
        Connection connection = Database.createConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                     "select latitude from cities where id=" + id)) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    public static Double findLongitudeById(int id) throws SQLException {
        Connection connection = Database.createConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                     "select longitude from cities where id=" + id)) {
            return rs.next() ? rs.getDouble(1) : null;
        }
    }

    public static String findById(int id) throws SQLException {
        Connection connection = Database.createConnection();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(
                     "select name from cities where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

    public static Double distance(int firstCity, int secondCity) throws SQLException {
        Double lat1 = findLatitudeById(firstCity);
        Double lat2 = findLatitudeById(secondCity);
        Double long1 = findLongitudeById(firstCity);
        Double long2 = findLongitudeById(secondCity);
        System.out.println(lat1 +" " +  lat2 +" " + long1 + " " + long2);
        long1 = Math.toRadians(long1);
        long2 = Math.toRadians(long2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        System.out.println(findById(firstCity) + " " + findById(secondCity));
        double dlon = long2 - long1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public Object getTwin() {
        return twin;
    }

    public void setTwin(Object twin) {
        this.twin = twin;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void run() {

        System.out.println(id1 + " and the thread " + currentThread().getName() + " and the city name " + cityName + " and twin relation " + twin);
        try (PreparedStatement prepareStatement = connection.prepareStatement(
                "INSERT INTO cityTwins ( name, country, capital, latitude, longitude, twin) values ( " + "'"+ cityName +"','" +"test"+ "'," + true +"," + "0" + "," + "0"
                        + "," + twin + ")")) {
            /* pstmt.setString(1, name); */
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


