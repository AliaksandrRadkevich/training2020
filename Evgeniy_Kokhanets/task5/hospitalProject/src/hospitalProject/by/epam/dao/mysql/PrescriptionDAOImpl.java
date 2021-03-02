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
import hospitalProject.by.epam.dao.PrescriptionDAO;
import hospitalProject.by.epam.domain.Prescription;

public class PrescriptionDAOImpl implements PrescriptionDAO{
    private Connection connection;
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long create(Prescription prescription) throws DAOException {
        String sql = "INSERT INTO `prescription` (`patient_id`, `physician_id`, `drugs`, `procedure`, `surgery`, `date`) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, prescription.getPatientId());
            statement.setLong(2, prescription.getPhysicianId());
            statement.setString(3, prescription.getDrugs());
            statement.setString(4, prescription.getProcedure());
            statement.setString(5, prescription.getSurgery());
            statement.setTimestamp(6, new Timestamp(prescription.getDate().getTime()));
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
    public Prescription read(Long id) throws DAOException {
        String sql = "SELECT `patient_id`, `physician_id`, `drugs`, `procedure`, `surgery`, `date` FROM `prescription`"
                + " WHERE `id` = " + id;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Prescription prescription = null;
            if(resultSet.next()){
                prescription = new Prescription();
                prescription.setId(id);
                prescription.setPatientId(resultSet.getLong("patient_id"));
                prescription.setPhysicianId(resultSet.getLong("physician_id"));
                prescription.setDrugs(resultSet.getString("drugs"));
                prescription.setProcedure(resultSet.getString("procedure"));
                prescription.setSurgery(resultSet.getString("surgery"));
                prescription.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
            }
            return prescription;
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
    public void update(Prescription prescription) throws DAOException {
        String sql = "UPDATE `prescription` SET `patient_id` = ?, `physician_id` = ?, `drugs` = ?,"
                + " `procedure` = ?, `surgery` = ?, `date` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setLong(1, prescription.getPatientId());
            statement.setLong(2, prescription.getPhysicianId());
            statement.setString(3, prescription.getDrugs());
            statement.setString(4, prescription.getProcedure());
            statement.setString(5, prescription.getSurgery());
            statement.setTimestamp(6, new Timestamp(prescription.getDate().getTime()));
            statement.setLong(7, prescription.getId());
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
        String sql = "DELETE FROM `prescription` WHERE `id` = ?";
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
    public List<Prescription> readAll() throws DAOException {
        String sql = "SELECT `id`, `patient_id`, `physician_id`, `drugs`, `procedure`, `surgery`, `date` "
                + "FROM `prescription`";
        Statement statement = null;
        ResultSet resultSet = null;
        List<Prescription> prescriptions = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Prescription prescription = new Prescription();
                prescription.setId(resultSet.getLong("id"));
                prescription.setPatientId(resultSet.getLong("patient_id"));
                prescription.setPhysicianId(resultSet.getLong("physician_id"));
                prescription.setDrugs(resultSet.getString("drugs"));
                prescription.setProcedure(resultSet.getString("procedure"));
                prescription.setSurgery(resultSet.getString("surgery"));
                prescription.setDate(new java.util.Date(resultSet.getTimestamp("date").getTime()));
                prescriptions.add(prescription);
            }
            return prescriptions;
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
