package by.training.hotel.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hotel.model.pool.ConnectionPoolException;
import by.training.hotel.model.pool.ConnectionPool;

public class ApplicationInitializerListener implements ServletContextListener{
    private static Logger log = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().init("com.mysql.cj.jdbc.Driver", 
                    "jdbc:mysql://localhost:3306/hotel_db?useUnicode=true&serverTimezone=UTC", 
                    "root", 
                    "", 
                    5, 
                    100, 
                    0
            );
            log.info("Application started.");
        } catch (ConnectionPoolException e) {
            log.error("Connection Pool Exception={}", e);
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
        log.info("Application stoped.");
    }
}
