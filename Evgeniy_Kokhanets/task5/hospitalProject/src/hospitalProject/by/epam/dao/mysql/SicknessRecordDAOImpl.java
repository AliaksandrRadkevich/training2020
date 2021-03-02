package hospitalProject.by.epam.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import hospitalProject.by.epam.dao.DAOException;
import hospitalProject.by.epam.dao.SicknessRecordDAO;
import hospitalProject.by.epam.domain.SicknessRecord;

public class SicknessRecordDAOImpl implements SicknessRecordDAO{
    private Connection connection;
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public Long create(SicknessRecord sicknessRecord) throws DAOException {
        String sql = "INSERT INTO `sickness_record` (`medical_assessment`, `patient_id`, `physician_id`,"
                + " `hospitalization_date`, `discharge_date`) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sicknessRecord.getMedicalAssessment());
            statement.setLong(2, sicknessRecord.getPatientId());
            statement.setLong(3, sicknessRecord.getPhysicianId());
            statement.setTimestamp(4, new Timestamp(sicknessRecord.getHospitalizationDate().getTime()));
            statement.setTimestamp(5, new Timestamp(sicknessRecord.getDischargeDate().getTime()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getLong(1);
        }
        catch(SQLException e) {
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
        }
    }

    @Override
    public SicknessRecord read(Long id) throws DAOException {
        String sql = "SELECT `medical_assessment`, `patient_id`, `physician_id`, `hospitalization_date`,"
                + " `discharge_date` "
                + "FROM `sickness_record`"
                + " WHERE `id` = " + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            SicknessRecord sicknessRecord = null;
            if(resultSet.next()){
                sicknessRecord = new SicknessRecord();
                sicknessRecord.setId(id);
                sicknessRecord.setMedicalAssessment(resultSet.getString("medical_assessment"));
                sicknessRecord.setPatientId(resultSet.getLong("patient_id"));
                sicknessRecord.setPhysicianId(resultSet.getLong("physician_id"));
                sicknessRecord.setHospitalizationDate(
                        new java.util.Date(resultSet.getTimestamp("hospitalization_date").getTime())
                );
                sicknessRecord.setDischargeDate(new java.util.Date(resultSet.getTimestamp("discharge_date").getTime()));
            }
            return sicknessRecord;
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
            try {resultSet.close();} catch(Exception e) {}
        }
    }

    @Override
    public void update(SicknessRecord sicknessRecord) throws DAOException {
        String sql = "UPDATE `sickness_record` SET `medical_assessment` = ?, `patient_id` = ?, `physician_id` = ?,"
                + " `hospitalization_date` = ?, `discharge_date` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sicknessRecord.getMedicalAssessment());
            statement.setLong(2, sicknessRecord.getPatientId());
            statement.setLong(3, sicknessRecord.getPhysicianId());
            statement.setTimestamp(4, new Timestamp(sicknessRecord.getHospitalizationDate().getTime()));
            statement.setTimestamp(5, new Timestamp(sicknessRecord.getDischargeDate().getTime()));
            statement.setLong(6, sicknessRecord.getId());
            statement.executeUpdate();
        }
        catch(SQLException e) {
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
        }
    }

    @Override
    public void delete(Long id) throws DAOException {
        String sql = "DELETE FROM `sickness_record` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        }
        catch(SQLException e) {
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
        }
    }

    @Override
    public List<SicknessRecord> readAll() throws DAOException {
        String sql = "SELECT `id`, `medical_assessment`, `patient_id`, `physician_id`, `hospitalization_date`,"
                + " `discharge_date` "
                + "FROM `sickness_record`";
        Statement statement = null;
        ResultSet resultSet = null;
        List<SicknessRecord> sicknessRecords = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                SicknessRecord sicknessRecord = new SicknessRecord();
                sicknessRecord.setId(resultSet.getLong("id"));
                sicknessRecord.setMedicalAssessment(resultSet.getString("medical_assessment"));
                sicknessRecord.setPatientId(resultSet.getLong("patient_id"));
                sicknessRecord.setPhysicianId(resultSet.getLong("physician_id"));
                sicknessRecord.setHospitalizationDate(
                        new java.util.Date(resultSet.getTimestamp("hospitalization_date").getTime())
                );
                sicknessRecord.setDischargeDate(new java.util.Date(resultSet.getTimestamp("discharge_date").getTime()));
                sicknessRecords.add(sicknessRecord);
            }
            return sicknessRecords;
        }
        catch(SQLException e){
            throw new DAOException(e);
        }
        finally {
            try {statement.close();} catch(Exception e) {}
            try {resultSet.close();} catch(Exception e) {}
        }
    }
}
