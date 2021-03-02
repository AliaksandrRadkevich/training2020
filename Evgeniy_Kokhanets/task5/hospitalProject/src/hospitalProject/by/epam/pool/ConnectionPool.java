package hospitalProject.by.epam.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

import com.mysql.cj.jdbc.Driver;

public final class ConnectionPool {
    private String jdbcUrl;
    private String user;
    private String password;
    private int maxSize;
    private int validationConnectionTimedout;
    
    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>(
            (c1, c2) -> Integer.compare(c1.hashCode(), c2.hashCode())
    );
    
    private ConnectionPool() {}
    
    public void init(String jdbcUrl, String user, String password, int minSize, int maxSize, 
            int validationConnectionTimedout) throws ConnectionPoolException {
        try {
            destroy();
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            this.jdbcUrl = jdbcUrl;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.validationConnectionTimedout = validationConnectionTimedout;
            for (int i = 0; i < minSize; i++) {
                freeConnections.add(newConnection());
            }
        }
        catch(SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }
    
    public Connection getConnection() throws ConnectionPoolException{
        Connection connection = null;
        while (connection == null) {
            try {
                connection = freeConnections.poll();
                if(connection != null) {
                    if(!connection.isValid(validationConnectionTimedout)) {
                        try {connection.close();} catch(SQLException e) {}
                    }
                    connection = null;
                }
                else if(usedConnections.size() < maxSize) {
                   connection = newConnection();
                }
                else if(this.jdbcUrl == null | this.user == null | this.password == null) {
                    throw new ConnectionPoolException("The connection pool has not been initialized");
                }
                else {
                    throw new ConnectionPoolException("The database connections limit is exceeded");
                }
            }
            catch(SQLException e) {
                throw new ConnectionPoolException(e);
            }
        }
        usedConnections.add(connection);
        return new ConnectionWrapper(connection);
    }
    
    void freeConnection(Connection connection) {
        try {
            connection.clearWarnings();
            usedConnections.remove(connection);
            freeConnections.add(connection);
        }
        catch(SQLException e) {
            try {connection.close();} catch(SQLException e1) {}
        }
    }
    
    public void destroy() {
        synchronized(freeConnections) {
            synchronized(usedConnections) {
                usedConnections.addAll(freeConnections);
                freeConnections.clear();
                for(Connection connection : usedConnections) {
                    try {connection.close();} catch(SQLException e) {}
                }
                usedConnections.clear();
            }
        }
    }
    
    private Connection newConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, user, password);
    }
    
    private static ConnectionPool instance = new ConnectionPool();
    
    public static ConnectionPool getInstance() {
        return instance;
    } 
}
