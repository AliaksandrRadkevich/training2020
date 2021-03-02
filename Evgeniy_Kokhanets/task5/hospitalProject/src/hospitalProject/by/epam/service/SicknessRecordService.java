package hospitalProject.by.epam.service;

import java.util.List;

import hospitalProject.by.epam.domain.SicknessRecord;

public interface SicknessRecordService {
    List<SicknessRecord> findAll() throws ServiceException;
    
    List<SicknessRecord> findByPhysicianId(Long id) throws ServiceException;
    
    List<SicknessRecord> findByPatientId(Long id) throws ServiceException;
    
    SicknessRecord findById(Long id) throws ServiceException;
    
    void save(SicknessRecord sicknessRecord) throws ServiceException;
    
    void delete(List<Long> ids) throws ServiceException;
}
