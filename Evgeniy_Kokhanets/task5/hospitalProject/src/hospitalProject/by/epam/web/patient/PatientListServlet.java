package hospitalProject.by.epam.web.patient;

import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;
import hospitalProject.by.epam.domain.Prescription;
import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.SicknessRecord;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.PrescriptionService;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.SicknessRecordService;
import hospitalProject.by.epam.service.UserService;

public class PatientListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("session_user");
            if(user != null) {
                req.setAttribute("login", user.getLogin());
                req.setAttribute("userRole", user.getRole());
                req.setAttribute("roles", Role.values());
            }
        }
        String patientId = req.getParameter("patientId");
        if(patientId != null) {
            try(ServiceCreator creator = new ServiceCreator()){
                UserService service = creator.getUserService();
                User user = service.findById(Long.parseLong(patientId));
                req.setAttribute("patient", user);
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
            try(ServiceCreator creator = new ServiceCreator()){
                PrescriptionService prescriptionService = creator.getPrescriptionService();
                UserService userService = creator.getUserService();
                List<Prescription> prescriptions = prescriptionService.findByPatientId(Long.parseLong(patientId));
                List<User> users = userService.findAll();
                Map<Prescription, User> prescriptionMap = new HashMap<>();
                for(Prescription prescription : prescriptions) {
                    for(User physician : users) {
                        if(prescription.getPhysicianId() == physician.getId()) {
                            prescriptionMap.put(prescription, physician);
                        }
                    }
                }
                req.setAttribute("prescriptions", prescriptionMap);
            }
            catch(ServiceCreatorException | ServiceException e1) {
                throw new ServletException(e1);
            }
            try(ServiceCreator creator = new ServiceCreator()) {
                SicknessRecordService recordService = creator.getSicknessRecordService();
                UserService userService = creator.getUserService();
                List<SicknessRecord> records = recordService.findByPatientId(Long.parseLong(patientId));
                List<User> users = userService.findAll();
                Map<SicknessRecord, User> recordMap = new HashMap<>();
                for(SicknessRecord record : records) {
                    for(User physician : users) {
                        if(record.getPhysicianId() == physician.getId()) {
                            recordMap.put(record, physician);
                        }
                    }
                }
                req.setAttribute("records", recordMap);
            }
            catch(ServiceCreatorException | ServiceException e1) {
                throw new ServletException(e1);
            }
            catch(IllegalFormatException e1) {
                resp.sendError(404);
                return;
            }
        }
        req.getRequestDispatcher("/WEB-INF/jsp/patient/list.jsp").forward(req, resp);
    }
}
