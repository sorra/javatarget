package rex.model.model;

import java.sql.*;

public class SimpleEntity {
  // AUTO_INCREMENT
  private Long id;
  private String name;
  private Timestamp createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public void insert(Connection connection) throws SQLException {
    String sql = "INSERT INTO simple_entity(name, created_at) VALUES(?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, getName());
      stmt.setTimestamp(2, getCreatedAt());
      stmt.executeUpdate();
    }
  }

  public static SimpleEntity find(Connection connection, Long id) throws SQLException {
    String sql = "SELECT id, name, created_at FROM simple_entity WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, id);
      ResultSet resultSet = stmt.executeQuery();
      boolean exists = resultSet.next();
      if (!exists) {
        return null;
      }
      SimpleEntity entity = new SimpleEntity();
      entity.setId(resultSet.getLong(1));
      entity.setName(resultSet.getString(2));
      entity.setCreatedAt(resultSet.getTimestamp(3));
      return entity;
    }
  }
}
