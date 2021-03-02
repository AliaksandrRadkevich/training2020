package hospitalProject.by.epam.web.record;

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
import hospitalProject.by.epam.domain.Role;
import hospitalProject.by.epam.domain.SicknessRecord;
import hospitalProject.by.epam.domain.User;
import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.SicknessRecordService;
import hospitalProject.by.epam.service.UserService;

public class RecordListServlet extends HttpServlet{
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
                    try(ServiceCreator creator = new ServiceCreator()) {
                        SicknessRecordService recordService = creator.getSicknessRecordService();
                        UserService userService = creator.getUserService();
                        List<SicknessRecord> records = recordService.findByPhysicianId(user.getId());
                        List<User> users = userService.findAll();
                        Map<SicknessRecord, User> map = new HashMap<>();
                        for(SicknessRecord record : records) {
                            for(User patient : users) {
                                if(record.getPatientId() == patient.getId()) {
                                    map.put(record, patient);
                                }
                            }
                        }
                        req.setAttribute("map", map);
                        req.getRequestDispatcher("/WEB-INF/jsp/record/list.jsp").forward(req, resp);
                        return;
                    } 
                    catch (ServiceException | ServiceCreatorException e) {
                        throw new ServletException(e);
                    }
                case PATIENT:
                    try(ServiceCreator creator = new ServiceCreator()) {
                        SicknessRecordService recordService = creator.getSicknessRecordService();
                        UserService userService = creator.getUserService();
                        List<SicknessRecord> records = recordService.findByPatientId(user.getId());
                        List<User> users = userService.findAll();
                        Map<SicknessRecord, User> map = new HashMap<>();
                        for(SicknessRecord record : records) {
                            for(User physician : users) {
                                if(record.getPhysicianId() == physician.getId()) {
                                    map.put(record, physician);
                                }
                            }
                        }
                        req.setAttribute("map", map);
                        req.getRequestDispatcher("/WEB-INF/jsp/record/list.jsp").forward(req, resp);
                        return;
                    } 
                    catch (ServiceException | ServiceCreatorException e) {
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
