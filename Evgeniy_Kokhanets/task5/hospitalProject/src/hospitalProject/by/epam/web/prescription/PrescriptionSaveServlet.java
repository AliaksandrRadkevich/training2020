package hospitalProject.by.epam.web.prescription;

import java.io.IOException;
import java.util.Date;
import java.util.IllegalFormatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;
import hospitalProject.by.epam.domain.Prescription;
import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.PrescriptionService;
import hospitalProject.by.epam.service.ServiceException;

public class PrescriptionSaveServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = null;
        if(session != null) {
            user = (User)session.getAttribute("session_user");
        }
        Long physicianId = null;
        if(user != null && user.getRole() == Role.PHYSICIAN) {
            physicianId = user.getId();
        }
        else {
            try {
                physicianId = Long.parseLong(req.getParameter("physician"));
            }
            catch(IllegalFormatException e) {
                throw new ServletException(e);
            }
        }
        Long patientId = null; 
        try {
            patientId = Long.parseLong(req.getParameter("patient"));
        }
        catch(IllegalFormatException e) {
            throw new ServletException(e);
        }
        String drugs = req.getParameter("drugs");
        String procedure = req.getParameter("procedure");
        String surgery = req.getParameter("surgery");
        if(patientId != null && physicianId != null) {
            Long id = null;
            try {
                id = Long.parseLong(req.getParameter("id"));
            }
            catch(NumberFormatException e) {}
            Prescription prescription = new Prescription();
            prescription.setId(id);
            prescription.setPhysicianId(physicianId);
            prescription.setPatientId(patientId);
            prescription.setDrugs(drugs);
            prescription.setProcedure(procedure);
            prescription.setSurgery(surgery);
            prescription.setDate(new Date());
            try (ServiceCreator creator = new ServiceCreator();){
                PrescriptionService service = creator.getPrescriptionService();
                service.save(prescription);
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/prescription/list.html");
    }
}
