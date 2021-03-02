package hospitalProject.by.epam.service;

import java.util.ArrayList;
import java.util.List;

import hospitalProject.by.epam.dao.DAOException;
import hospitalProject.by.epam.dao.PrescriptionDAO;
import hospitalProject.by.epam.domain.Prescription;

public class PrescriptionServiceImpl implements PrescriptionService{
    private PrescriptionDAO prescriptionDao;
    
    public void setPrescriptionDao(PrescriptionDAO prescriptionDao) {
        this.prescriptionDao = prescriptionDao;
    }

    @Override
    public Prescription findById(Long id) throws ServiceException {
        try {
            return prescriptionDao.read(id);
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> findAll() throws ServiceException {
        try{
            return prescriptionDao.readAll();
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Prescription> findByPatientId(Long id) throws ServiceException {
        try { 
            List<Prescription> prescriptions = new ArrayList<>();
            for(Prescription prescription : prescriptionDao.readAll()) {
                if(prescription.getPatientId() == id) {
                    prescriptions.add(prescription);
                }
            }
            return prescriptions;
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }
    
    

    @Override
    public List<Prescription> findByPhysicianId(Long id) throws ServiceException {
        try { 
            List<Prescription> prescriptions = new ArrayList<>();
            for(Prescription prescription : prescriptionDao.readAll()) {
                if(prescription.getPhysicianId() == id) {
                    prescriptions.add(prescription);
                }
            }
            return prescriptions;
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Prescription prescription) throws ServiceException {
        try {
            if(prescription.getId() != null) {
                prescriptionDao.update(prescription);
            }
            else {
                prescriptionDao.create(prescription);
            }
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(List<Long> ids) throws ServiceException {
        try {
            for(Long id : ids) {
                prescriptionDao.delete(id);
            }
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}
