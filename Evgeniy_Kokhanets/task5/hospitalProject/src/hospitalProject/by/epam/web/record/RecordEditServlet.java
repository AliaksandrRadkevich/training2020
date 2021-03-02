package hospitalProject.by.epam.web.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;

import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.SicknessRecord;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.SicknessRecordService;
import hospitalProject.by.epam.service.UserService;

public class RecordEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session != null) {
            User user = (User)session.getAttribute("session_user");
            req.setAttribute("login", user.getLogin());
            req.setAttribute("userRole", user.getRole());
            req.setAttribute("roles", Role.values());
        }
        String id = req.getParameter("id");
        if(id != null) {
            try(ServiceCreator creator = new ServiceCreator()){
                SicknessRecordService service = creator.getSicknessRecordService();
                SicknessRecord record = service.findById(Long.parseLong(id));
                if(record != null) {
                    req.setAttribute("record", record);
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
            catch(IllegalArgumentException e) {
                resp.sendError(404);
                return;
            }
        }
        List<User> patients = new ArrayList<>();
        try (ServiceCreator creator = new ServiceCreator()){
            UserService service = creator.getUserService();
            for(User patient : service.findAll()) {
                if(patient.getRole() == Role.PATIENT) {
                    patients.add(patient);
                }
            }
        }
        catch(ServiceCreatorException | ServiceException e) {
            throw new ServletException(e);
        }
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/WEB-INF/jsp/record/edit.jsp").forward(req, resp);;
    }
}
