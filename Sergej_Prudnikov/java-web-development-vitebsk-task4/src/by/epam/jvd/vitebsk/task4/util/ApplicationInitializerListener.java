package by.epam.jvd.vitebsk.task4.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epam.jvd.vitebsk.task4.pool.ConnectionPool;
import by.epam.jvd.vitebsk.task4.pool.ConnectionPoolException;

public class ApplicationInitializerListener implements ServletContextListener { // работает автоматически

    @Override
    public void contextInitialized(ServletContextEvent sce) { // создает ConnectionPool при старте приложения
        try {
            ConnectionPool.getInstance().init("com.mysql.cj.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/reception_commission_db?useUnicode=true&serverTimezone=", "root",
                    "root", 5, 100, 0);
        } catch (ConnectionPoolException e) {
            e.printStackTrace(); // TODO сюда нужно поставить логгер, для того, что бы фиксировать ошибки
                                 // связанные с инициализацией ConnectionPool
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) { // уничтожает ConnectionPool при остановке или перезапуске
                                                            // приложения
        ConnectionPool.getInstance().destroy();
    }

}
