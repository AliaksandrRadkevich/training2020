package by.epam.jvd.vitebsk.task4.controller.enrolledperson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.jvd.vitebsk.task4.controller.Action;
import by.epam.jvd.vitebsk.task4.controller.Forward;
import by.epam.jvd.vitebsk.task4.di.ServiceFactoryException;
import by.epam.jvd.vitebsk.task4.domain.EnrolledPerson;
import by.epam.jvd.vitebsk.task4.domain.Faculty;
import by.epam.jvd.vitebsk.task4.domain.StatementByEntrant;
import by.epam.jvd.vitebsk.task4.service.ServiceException;
import by.epam.jvd.vitebsk.task4.service.enrolled_person.EnrolledPersonService;
import by.epam.jvd.vitebsk.task4.service.faculty.FacultyService;
import by.epam.jvd.vitebsk.task4.service.statement.StatementService;

public class EnrolledPersonListAction extends Action {
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            EnrolledPersonService enrolledService;
            List<EnrolledPerson> enrolledPersons;
            enrolledService = getServiceFactory().getEnrolledPersonService();
            enrolledPersons = enrolledService.findAll();

            if (enrolledPersons.isEmpty()) {
                StatementService statementService;
                FacultyService facultyService;

                List<StatementByEntrant> statements;
                Map<Long, List<StatementByEntrant>> mapStatements;

                List<Faculty> allFaculties;
                Map<Long, Integer> facultyIdRecruitmentPlan;

                statementService = getServiceFactory().getStatementService();
                facultyService = getServiceFactory().getFacultyService();
                enrolledService = getServiceFactory().getEnrolledPersonServiceWithTransaction();

                statements = statementService.findAll(); // все поданные заявления

                allFaculties = facultyService.findAll();
                facultyIdRecruitmentPlan = new HashMap<Long, Integer>();
                allFaculties.forEach(faculty -> {
                    facultyIdRecruitmentPlan.put(faculty.getId(), faculty.getRecruitment_plan());
                });                                             // здесь мы создали Map (id факультета - план набора)
                mapStatements = statementService.separateStatementsByFaculty(statements); // все поданные заявления,
                // разделенные по факультетам
                enrolledPersons = enrolledService.createEnrolledList(mapStatements, facultyIdRecruitmentPlan);
                try {
                    enrolledService.saveAll(enrolledPersons);
                } catch (ServiceException e) {
                    throw new ServletException(e);
                }
            }

            req.setAttribute("enrolledPersons", enrolledPersons);
        } catch (ServiceFactoryException | ServiceException e) {
            throw new ServletException(e);
        } catch (Exception e1) {
            throw new ServletException(e1);
        }
        return null;
    }
}
