package hospitalProject.by.epam.dao;

import java.util.List;

import hospitalProject.by.epam.domain.Prescription;
import hospitalProject.by.epam.domain.User;

public interface PrescriptionDAO extends DAO<Prescription>{
    List<Prescription> readAll() throws DAOException;
}
