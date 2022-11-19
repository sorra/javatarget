package rex.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {

  Connection connection;

  public ConnectionManager() throws SQLException {
    connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", "");
    String dml = "CREATE TABLE simple_entity(id number AUTO_INCREMENT, name varchar, created_at timestamp, PRIMARY KEY (id))";
    try (PreparedStatement stmt = connection.prepareStatement(dml)) {
      stmt.executeUpdate();
    }
  }

  public Connection getConnection() throws SQLException {
    return connection;
  }
}
