package lab9;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Database {

    private static BasicDataSource dataSource = null;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/lab8");
        dataSource.setUsername("postgres");
        dataSource.setPassword("112233445511");

        dataSource.setMinIdle(200);
        dataSource.setMaxIdle(500);
        dataSource.setMaxTotal(1000);
    }
    private Database()
    {}
    public static void initializeDatabase() throws SQLException {
        Connection connection=dataSource.getConnection();
        try(PreparedStatement preparedStatement= connection.prepareStatement(
                "DROP TABLE IF EXISTS PUBLIC.citytwins;\n" +
                        "\n" +
                        "CREATE TABLE IF NOT EXISTS public.citytwins\n" +
                        "(\n" +
                        "id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1 MINVALUE  1 MAXVALUE  1000 CACHE 1),\n" +
                        "name VARCHAR(50) NOT NULL,\n" +
                        "country VARCHAR(50) NOT NULL,\n" +
                        "capital boolean NOT NULL,\n" +
                        "latitude NUMERIC(30, 25) NOT NULL,\n" +
                        "longitude NUMERIC(30, 25) NOT NULL,\n"+
                        "twin integer NOT NULL\n" +
                        ");"

        )){
            preparedStatement.executeUpdate();
        }
        try (PreparedStatement prepareStatement = connection.prepareStatement(
                "DROP TABLE IF EXISTS public.countries;\n" +
                        "\n" +
                        "CREATE TABLE IF NOT EXISTS public.countries\n" +
                        "(\n" +
                        "    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 1000 CACHE 1 ),\n" +
                        "    name character varying(30) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                        "    code integer NOT NULL,\n" +
                        "    continent character varying(30) COLLATE pg_catalog.\"default\" NOT NULL,\n" +
                        "    CONSTRAINT countries_pkey PRIMARY KEY (id)\n" +
                        ")\n" +
                        "\n" +
                        "TABLESPACE pg_default;\n" +
                        "\n" +
                        "ALTER TABLE IF EXISTS public.countries\n" +
                        "    OWNER to postgres;")) {
            prepareStatement.executeUpdate();
        }
    }
    public static Connection createConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
