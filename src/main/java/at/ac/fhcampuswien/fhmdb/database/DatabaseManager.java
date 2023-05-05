package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;

import java.sql.SQLException;

public class DatabaseManager {  // Verbindg: ORMLite m. DB
    public static final String DB_URL = "jdbc:hw:file: ./db/watchlistdb"; //Verbindungsaufbau mit Attributen:
    public static final String user = "user";
    public static final String password = "pass";

    public static JdbcConnectionSource connectionSource;
    Dao<WatchlistEntity, Long> dao; // Zugriffe machbar

    private static DatabaseManager instance; // Singleton Pattern

    private DatabaseManager() {  // constructor
        try {
            createConnectionSource();
            dao = DaoManager.createDao(connectionSource, WatchlistEntity.class);
            // Tabelle einmalig kreieren
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        } else { return instance; }
    }

    private static void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(DB_URL, user, password);
    }
}
