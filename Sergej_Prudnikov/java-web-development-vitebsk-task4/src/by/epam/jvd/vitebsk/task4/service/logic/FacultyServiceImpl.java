package by.epam.jvd.vitebsk.task4.service.logic;

import java.util.List;

import by.epam.jvd.vitebsk.task4.dao.DaoException;
import by.epam.jvd.vitebsk.task4.dao.FacultyDao;
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyNameNotUniqueException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyNotExistsException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;

public class FacultyServiceImpl extends BaseService implements FacultyService {
    private FacultyDao facultyDao;

    public void setFacultyDao(FacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    @Override
    public Faculty findById(Long id) throws ServiceException {
        try {
            return facultyDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void save(Faculty faculty) throws ServiceException {
        try {
            getTransaction().start();
            if (faculty.getId() != null) {
                Faculty storedFaculty = facultyDao.read(faculty.getId());
                if (storedFaculty != null) {
                    // проверка, что бы названия факультетов были одинаковые, или новое название
                    // было свободно
                    if (storedFaculty.getName().equals(faculty.getName())
                            || facultyDao.readByName(faculty.getName()) == null) {
                        facultyDao.update(faculty);
                    } else {
                        throw new FacultyNameNotUniqueException(faculty.getName());
                    }
                } else {
                    throw new FacultyNotExistsException(faculty.getId());
                }
            } else {
                if (facultyDao.readByName(faculty.getName()) == null) {
                    Long id = facultyDao.create(faculty);
                    faculty.setId(id);
                } else {
                    throw new FacultyNameNotUniqueException(faculty.getName());
                }
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            facultyDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Faculty> findAll() throws ServiceException {
        try {
            return facultyDao.readAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
