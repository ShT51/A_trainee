package connection;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DataSourceTest {

    @Test
    void getConnection() throws SQLException {
        DataSource.getConnection();
    }
}