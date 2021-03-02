package hospitalProject.by.epam.web.patient;

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
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.UserService;

public class PatientGetServlet extends HttpServlet{
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
        String patientName = req.getParameter("patientName");
        if(patientName != null && !patientName.isEmpty()) {
            List<User> patients = new ArrayList<>();
            try(ServiceCreator creator = new ServiceCreator()){
                UserService service = creator.getUserService();
                for(User user : service.findAll()) {
                    if(user.getName().equalsIgnoreCase(patientName) && user.getRole() == Role.PATIENT) {
                        patients.add(user);
                    }
                }
            }
            catch(ServiceCreatorException | ServiceException e) {
                throw new ServletException(e);
            }
            if(!patients.isEmpty()) {
                req.setAttribute("patients", patients);
                req.getRequestDispatcher("/WEB-INF/jsp/patient/find.jsp").forward(req, resp);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/patient/find.html?message=Couldn't find anything");
    }
}
