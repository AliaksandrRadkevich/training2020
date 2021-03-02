package hospitalProject.by.epam.web.record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hospitalProject.by.epam.di.ServiceCreator;
import hospitalProject.by.epam.di.ServiceCreatorException;

import hospitalProject.by.epam.service.ServiceException;
import hospitalProject.by.epam.service.SicknessRecordService;

public class RecordDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<Long> ids = new ArrayList<>(idsStr.length);
        try(ServiceCreator creator = new ServiceCreator()){
            for(String id : idsStr) {
                ids.add(Long.valueOf(id));
            }
            SicknessRecordService service = creator.getSicknessRecordService();
            service.delete(ids);
        }
        catch(IllegalFormatException | ServiceException | ServiceCreatorException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/record/myRecords.html");
    }
}
