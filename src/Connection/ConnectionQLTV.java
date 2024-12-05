
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionQLTV {
    public static String DB_URL = "jdbc:mysql://localhost/libaryutt";
    public static String USER_NAME = "root";
    public static String PASSWORD = "";
    
    
    public static ConnectionQLTV instance;
    public Connection connection;
    
    public ConnectionQLTV() {
        
        try {
            connection = DriverManager.getConnection(DB_URL ,USER_NAME , PASSWORD );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public synchronized static ConnectionQLTV getInstance() {
        if(instance == null)
            instance = new ConnectionQLTV();
        return instance;
    }

    public java.sql.Connection getConnection() {
        return connection;
    }
  
}

    
    

