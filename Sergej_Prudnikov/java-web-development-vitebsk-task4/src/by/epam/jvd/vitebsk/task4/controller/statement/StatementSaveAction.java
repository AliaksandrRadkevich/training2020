package by.epam.jvd.vitebsk.task4.controller.statement;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class StatementSaveAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatementByEntrant statementByEntrant = new StatementByEntrant(); // dao-сущность, заявление абитуриента
        try {
            statementByEntrant.setId(Long.parseLong(req.getParameter("id")));
        } catch (NumberFormatException e) {
        }
        try {
            statementByEntrant.setUserId(Long.parseLong(req.getParameter("userId")));
        } catch (NumberFormatException e) {
        }
        try {
            Faculty faculty = new Faculty();
            faculty.setId(Long.parseLong(req.getParameter("faculty"))); // TODO проинициализирован только id
            statementByEntrant.setFaculty(faculty);
        } catch (NumberFormatException e) {
        }
        statementByEntrant.setLastName(req.getParameter("last_name"));
        statementByEntrant.setFirstName(req.getParameter("first_name"));
        statementByEntrant.setPatronymic(req.getParameter("patronymic"));
        statementByEntrant.setPassportId(req.getParameter("passport_id"));
        try {
            statementByEntrant.setCertificateScore(Integer.parseInt(req.getParameter("certificate_score")));
        } catch (NumberFormatException e) {
        }
        try {
            statementByEntrant.setSubjectScore1(Integer.parseInt(req.getParameter("subject_1_score")));
        } catch (NumberFormatException e) {
        }
        try {
            statementByEntrant.setSubjectScore2(Integer.parseInt(req.getParameter("subject_2_score")));
        } catch (NumberFormatException e) {
        }
        try {
            statementByEntrant.setSubjectScore3(Integer.parseInt(req.getParameter("subject_3_score")));
        } catch (NumberFormatException e) {
        }
        statementByEntrant.setDate(new Date());
        // проверяем, что бы все параметры были != null(условие в базе данных)
        if (statementByEntrant.getFaculty() != null && !statementByEntrant.getFirstName().isBlank()
                && !statementByEntrant.getLastName().isBlank() && !statementByEntrant.getPatronymic().isBlank()
                && !statementByEntrant.getPassportId().isBlank() && statementByEntrant.getCertificateScore() > 0
                && statementByEntrant.getCertificateScore() < 101 && statementByEntrant.getSubjectScore1() > 0
                && statementByEntrant.getSubjectScore1() < 101 && statementByEntrant.getSubjectScore2() > 0
                && statementByEntrant.getSubjectScore2() < 101 && statementByEntrant.getSubjectScore3() > 0
                && statementByEntrant.getSubjectScore3() < 101) {
            try {
                StatementService statementService = getServiceFactory().getStatementServiceWithTransaction();
                statementService.save(statementByEntrant);
            } catch (ServiceFactoryException | ServiceException e) {
                throw new ServletException(e);
            }
        }
        return new Forward("/statement/edit.html");
    }
}