import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private final String HOST;
    private final String USERNAME;
    private final String PASSWORD;

    public DatabaseConnectionManager() {
        String url = "";
        String username = "";
        String password = "";

        try {
            Properties prop = new Properties();
            String propFileName = "aplication.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            prop.load(inputStream);
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (Exception ignored){
        }

        this.HOST = url;
        this.USERNAME = username;
        this.PASSWORD = password;
    }

    public Connection getConnection()throws SQLException {
        return DriverManager.getConnection(HOST,USERNAME,PASSWORD);
    }
}
