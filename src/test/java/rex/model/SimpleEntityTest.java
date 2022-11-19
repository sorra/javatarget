package rex.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import rex.model.jdbc.ConnectionManager;
import rex.model.model.SimpleEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleEntityTest {

  private ConnectionManager connectionManager;

  {
    try {
      connectionManager = new ConnectionManager();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void testInsert() throws Exception {
    Timestamp createdAt = Timestamp.from(Instant.now());
    try (Connection connection = connectionManager.getConnection()) {
      SimpleEntity entity = new SimpleEntity();
      entity.setName("Tom");
      entity.setCreatedAt(createdAt);
      entity.insert(connection);

      SimpleEntity retrieved = SimpleEntity.find(connection, 1L);
      assertEquals(1L, retrieved.getId());
      assertEquals("Tom", retrieved.getName());
      assertEquals(createdAt, retrieved.getCreatedAt());
    }
  }
}
