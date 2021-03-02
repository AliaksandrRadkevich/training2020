package hospitalProject.by.epam.service;

import java.util.List;

import hospitalProject.by.epam.domain.Prescription;

public interface PrescriptionService {
    List<Prescription> findAll() throws ServiceException;
    
    List<Prescription> findByPatientId(Long id) throws ServiceException;
    
    List<Prescription> findByPhysicianId(Long id) throws ServiceException;
    
    Prescription findById(Long id) throws ServiceException;
    
    void save(Prescription prescription) throws ServiceException;
    
    void delete(List<Long> ids) throws ServiceException;
}
