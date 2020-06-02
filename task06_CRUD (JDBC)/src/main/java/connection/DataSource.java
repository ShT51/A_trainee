package connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static HikariConfig config;
    private static HikariDataSource dataSource;
    private static final String configFile = "src/main/resources/demo.properties";

    static {
        config = new HikariConfig(configFile);
        dataSource = new HikariDataSource(config);

    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
