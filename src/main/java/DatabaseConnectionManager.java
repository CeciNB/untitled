import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager() {
        Properties poop = new Properties();
        String propFileName = "aplication.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            poop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host = poop.getProperty("url");
        String username = poop.getProperty("username");
        String password = poop.getProperty("password");

        try {
            this.connection = DriverManager.getConnection(host, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static DatabaseConnectionManager getInstance(){
        if (instance == null){
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
