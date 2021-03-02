package hospitalProject.by.epam.web.prescription;

import java.io.IOException;
import java.util.HashMap;
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
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.PrescriptionService;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.UserService;

public class PrescriptionListServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("session_user");
            if(user != null) {
                req.setAttribute("login", user.getLogin());
                req.setAttribute("userRole", user.getRole());
                req.setAttribute("roles", Role.values());
                switch(user.getRole()) {
                case PHYSICIAN:
                    try(ServiceCreator creator = new ServiceCreator()){
                        PrescriptionService prescriptionService = creator.getPrescriptionService();
                        UserService userService = creator.getUserService();
                        List<Prescription> prescriptions = prescriptionService.findByPhysicianId(user.getId());
                        List<User> users = userService.findAll();
                        Map<Prescription, User> map = new HashMap<>();
                        for(Prescription prescription : prescriptions) {
                            for(User patient : users) {
                                if(prescription.getPatientId() == patient.getId()) {
                                    map.put(prescription, patient);
                                }
                            }
                        }
                        req.setAttribute("map", map);
                        req.getRequestDispatcher("/WEB-INF/jsp/prescription/list.jsp").forward(req, resp);
                        return;
                    }
                    catch(ServiceCreatorException | ServiceException e) {
                        throw new ServletException(e);
                    }
                case PATIENT:
                    try(ServiceCreator creator = new ServiceCreator()){
                        PrescriptionService prescriptionService = creator.getPrescriptionService();
                        UserService userService = creator.getUserService();
                        List<Prescription> prescriptions = prescriptionService.findByPatientId(user.getId());
                        List<User> users = userService.findAll();
                        Map<Prescription, User> map = new HashMap<>();
                        for(Prescription prescription : prescriptions) {
                            for(User physician : users) {
                                if(prescription.getPhysicianId() == physician.getId()) {
                                    map.put(prescription, physician);
                                }
                            }
                        }
                        req.setAttribute("map", map);
                        req.getRequestDispatcher("/WEB-INF/jsp/prescription/list.jsp").forward(req, resp);
                        return;
                    }
                    catch(ServiceCreatorException | ServiceException e) {
                        throw new ServletException(e);
                    }
                default:
                    resp.sendError(404);
                    break;
                }
            }
        }
    }
}
