package hospitalProject.by.epam.web;

import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import hospitalProject.by.epam.pool.ConnectionPool;
import hospitalProject.by.epam.pool.ConnectionPoolException;

public class InitializerListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().init("jdbc:mysql://localhost:3306/hospital_db?useUnicode=true&serverTimezone=" 
                    + TimeZone.getDefault().getID(), 
                    "root", 
                    "root", 
                    1, 
                    10, 
                    0);
        }
        catch(ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}
