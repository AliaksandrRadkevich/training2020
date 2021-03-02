package by.epam.jvd.vitebsk.task4.controller.statement;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class StatementDeleteAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        if (id != null) {
            try {
                StatementService statementService = getServiceFactory().getStatementService();
                statementService.delete(id);
            } catch (ServiceFactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/statement/edit.html");
    }
}
