package games.trident.skills.database;

import java.sql.Connection;

public interface Database {
    DatabaseCredentials getCredentials();

    Connection getConnection();
}