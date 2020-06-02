package connection;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class DataSourceTest {

    @Test
    void getConnection() throws SQLException {
        DataSource.getConnection();
    }
}