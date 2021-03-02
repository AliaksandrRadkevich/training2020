package by.epam.jvd.vitebsk.task4.controller.faculty;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;

public class FacultyListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FacultyService facultyService = getServiceFactory().getFacultyService();
            List<Faculty> faculties = facultyService.findAll();
            req.setAttribute("faculties", faculties);
            return null;
        } catch (ServiceFactoryException | ServiceException e) {
            throw new ServletException(e);
        } catch (Exception e1) {
            throw new ServletException(e1);
        }
    }
}
