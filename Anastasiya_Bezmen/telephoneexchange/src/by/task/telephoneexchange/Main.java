package by.task.telephoneexchange;

import by.task.telephoneexchange.di.ServiceCreationException;
import by.task.telephoneexchange.di.ServiceCreator;
import by.task.telephoneexchange.domain.User;
import by.task.telephoneexchange.pool.ConnectionPool;
import by.task.telephoneexchange.pool.ConnectionPoolException;
import by.task.telephoneexchange.service.IUserService;
import by.task.telephoneexchange.service.ServiceException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ConnectionPoolException {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.init("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/telephoneexchange?useUnicode=true&serverTimezone=" +
                        TimeZone.getDefault().getID(), "root", "root", 5, 100, 0);
//        Connection c = pool.getConnection();
//        UserRepository userRepository = new UserRepository();
//        userRepository.setConnection(c);
//        List<User> users = userRepository.readAll();
//        for (User user : users) {
//            System.out.println(user);
//        }
//        System.out.println("ok reedAll()");
//        User user = userRepository.read(1L);
//        System.out.println(user);
//        System.out.println("ok reed()");
//        user.setRole(Role.ADMINISTRATOR);
//        userRepository.update(user);
//        System.out.println("ok update()");
//        //  userRepository.delete(3L);
//        //  System.out.println("ok delete()");
//        user = new User();
//        user.setLogin("пользователь" + Math.random());
//        user.setPassword("Винтов");
//        user.setRole(Role.ADMINISTRATOR);
//        user.setPersonal_info_id(2L);
//        Long id = userRepository.create(user);
//        System.out.println("ok create(), id = " + id);
//        c.close();

        try (ServiceCreator sc = new ServiceCreator()) {
            IUserService iUserService = sc.getUserService();
            List<User> users = iUserService.findAll();
            for (User user : users) {
                System.out.println(user);
            }
            System.out.println("ok findAll()");
        } catch (ServiceCreationException | ServiceException e) {
            System.out.println("ERROR");
        }

    }
}
