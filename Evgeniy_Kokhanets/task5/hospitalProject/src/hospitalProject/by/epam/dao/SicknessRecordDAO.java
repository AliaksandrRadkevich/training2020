package hospitalProject.by.epam.dao;

import java.util.List;

import hospitalProject.by.epam.domain.SicknessRecord;

public interface SicknessRecordDAO extends DAO<SicknessRecord>{
    List<SicknessRecord> readAll() throws DAOException;

}
