package hospitalProject.by.epam.di;

import java.sql.Connection;

import hospitalProject.by.epam.dao.PrescriptionDAO;
import hospitalProject.by.epam.dao.SicknessRecordDAO;
import hospitalProject.by.epam.dao.UserDAO;
import hospitalProject.by.epam.dao.mysql.PrescriptionDAOImpl;
import hospitalProject.by.epam.dao.mysql.SicknessRecordDAOImpl;
import hospitalProject.by.epam.dao.mysql.UserDAOImpl;
import hospitalProject.by.epam.pool.ConnectionPool;
import hospitalProject.by.epam.pool.ConnectionPoolException;
import hospitalProject.by.epam.service.PrescriptionService;
import hospitalProject.by.epam.service.PrescriptionServiceImpl;
import hospitalProject.by.epam.service.SicknessRecordService;
import hospitalProject.by.epam.service.SicknessRecordServiceImpl;
import hospitalProject.by.epam.service.UserService;
import hospitalProject.by.epam.service.UserServiceImpl;

public class ServiceCreator implements AutoCloseable{
    private UserService userService = null;
    public UserService getUserService() throws ServiceCreatorException {
        if(userService == null){
            UserServiceImpl userServiceImpl = new UserServiceImpl();
            userServiceImpl.setUserDao(getUserDao());
            userService = userServiceImpl;
        }
        return userService;
    }
    
    private UserDAO userDao = null;
    private UserDAO getUserDao() throws ServiceCreatorException {
        if(userDao == null){
            UserDAOImpl userDaoImpl = new UserDAOImpl();
            userDaoImpl.setConnection(getConnection());
            userDao = userDaoImpl;
        }
        return userDao;
    }
    
    private PrescriptionService prescriptionService = null;
    public PrescriptionService getPrescriptionService() throws ServiceCreatorException {
        if(prescriptionService == null){
            PrescriptionServiceImpl prescriptionServiceImpl = new PrescriptionServiceImpl();
            prescriptionServiceImpl.setPrescriptionDao(getPrescriptionDao());
            prescriptionService = prescriptionServiceImpl;
        }
        return prescriptionService;
    }
    
    private PrescriptionDAO prescriptionDao = null;
    private PrescriptionDAO getPrescriptionDao() throws ServiceCreatorException {
        if(prescriptionDao == null){
            PrescriptionDAOImpl prescriptionDaoImpl = new PrescriptionDAOImpl();
            prescriptionDaoImpl.setConnection(getConnection());
            prescriptionDao = prescriptionDaoImpl;
        }
        return prescriptionDao;
    }
    
    private SicknessRecordService sicknessRecordService = null;
    public SicknessRecordService getSicknessRecordService() throws ServiceCreatorException {
        if(sicknessRecordService == null){
            SicknessRecordServiceImpl sicknessRecordServiceImpl = new SicknessRecordServiceImpl();
            sicknessRecordServiceImpl.setSicknessRecordDao(getSicknessRecordDao());
            sicknessRecordService = sicknessRecordServiceImpl;
        }
        return sicknessRecordService;
    }
    
    private SicknessRecordDAO sicknessRecordDao = null;
    private SicknessRecordDAO getSicknessRecordDao() throws ServiceCreatorException {
        if(sicknessRecordDao == null){
            SicknessRecordDAOImpl sicknessRecordDaoImpl = new SicknessRecordDAOImpl();
            sicknessRecordDaoImpl.setConnection(getConnection());
            sicknessRecordDao = sicknessRecordDaoImpl;
        }
        return sicknessRecordDao;
    }
    
    private Connection connection = null;
    private Connection getConnection() throws ServiceCreatorException {
        if(connection == null) {
            try {
                connection = ConnectionPool.getInstance().getConnection();
            }
            catch(ConnectionPoolException e) {
                throw new ServiceCreatorException(e);
            }
        }
        return connection;
    }
    @Override
    public void close(){
       try { connection.close(); } catch(Exception e) {}
    }
}
