package hospitalProject.by.epam.pool;

public class ConnectionPoolException extends Exception{
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }
}
