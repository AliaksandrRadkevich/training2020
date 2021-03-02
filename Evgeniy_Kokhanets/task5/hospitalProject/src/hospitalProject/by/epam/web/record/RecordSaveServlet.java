package hospitalProject.by.epam.web.record;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.IllegalFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;

import hospitalProject.by.epam.domain.SicknessRecord;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.SicknessRecordService;

public class RecordSaveServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User physician = null;
        if(session != null) {
            physician = (User)session.getAttribute("session_user");
        }
        Long physicianId = physician.getId();
        Long patientId = null; 
        try {
            patientId = Long.parseLong(req.getParameter("patient"));
        }
        catch(IllegalFormatException e) {
            throw new ServletException(e);
        }
        String medicalAssessment = req.getParameter("medicalAssessment");
        String hospitalizationDateStr = req.getParameter("hospitalizationDate");
        String dischargeDateStr = req.getParameter("dischargeDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date hospitalizationDate = null;
        Date dischargeDate = null;
        if(hospitalizationDateStr != null && !hospitalizationDateStr.isEmpty() && dischargeDateStr != null 
                && !dischargeDateStr.isEmpty()) {
            try {
                hospitalizationDate = format.parse(hospitalizationDateStr);
                dischargeDate = format.parse(dischargeDateStr);
            }
            catch(ParseException e) {
                throw new ServletException(e);
            }
        }
        if(patientId != null && physicianId != null && medicalAssessment != null && !medicalAssessment.isEmpty() && 
                hospitalizationDate != null && dischargeDate != null && !hospitalizationDate.after(dischargeDate)) {
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            }
            catch(NumberFormatException e) {}
            SicknessRecord record = new SicknessRecord();
            record.setId(id);
            record.setMedicalAssessment(medicalAssessment);
            record.setPhysicianId(physicianId);
            record.setPatientId(patientId);
            record.setHospitalizationDate(hospitalizationDate);
            record.setDischargeDate(dischargeDate);
            try (ServiceCreator creator = new ServiceCreator();){
                SicknessRecordService service = creator.getSicknessRecordService();
                service.save(record);
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/record/list.html");
    }
}
