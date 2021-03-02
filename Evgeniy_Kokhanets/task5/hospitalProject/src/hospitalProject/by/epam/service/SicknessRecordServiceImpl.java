package hospitalProject.by.epam.service;

import java.util.ArrayList;
import java.util.List;

import hospitalProject.by.epam.dao.DAOException;
import hospitalProject.by.epam.dao.SicknessRecordDAO;
import hospitalProject.by.epam.domain.SicknessRecord;

public class SicknessRecordServiceImpl implements SicknessRecordService{
    private SicknessRecordDAO sicknessRecordDao;
    
    public void setSicknessRecordDao(SicknessRecordDAO sicknessRecordDao) {
        this.sicknessRecordDao = sicknessRecordDao;
    }

    @Override
    public List<SicknessRecord> findAll() throws ServiceException {
        try {
            return sicknessRecordDao.readAll();
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<SicknessRecord> findByPhysicianId(Long id) throws ServiceException {
        try { 
            List<SicknessRecord> sicknessRecords = new ArrayList<>();
            for(SicknessRecord sicknessRecord : sicknessRecordDao.readAll()) {
                if(sicknessRecord.getPhysicianId() == id) {
                    sicknessRecords.add(sicknessRecord);
                }
            }
            return sicknessRecords;
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<SicknessRecord> findByPatientId(Long id) throws ServiceException {
        try { 
            List<SicknessRecord> sicknessRecords = new ArrayList<>();
            for(SicknessRecord sicknessRecord : sicknessRecordDao.readAll()) {
                if(sicknessRecord.getPatientId() == id) {
                    sicknessRecords.add(sicknessRecord);
                }
            }
            return sicknessRecords;
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public SicknessRecord findById(Long id) throws ServiceException {
        try {
            return sicknessRecordDao.read(id);
            
        }
        catch(DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(SicknessRecord sicknessRecord) throws ServiceException {
        try {
            if(sicknessRecord.getId() != null) {
                sicknessRecordDao.update(sicknessRecord);
            }
            else {
                sicknessRecordDao.create(sicknessRecord);
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
                sicknessRecordDao.delete(id);
            }
        }
        catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}
