package by.training.hotel.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.di.ServiceCreator;
import by.training.hotel.model.di.impl.ServiceCreatorImpl;

public class ServletDriver extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = getRequestUrl(req);
        ActionInvoker actionInvoker = new ActionInvoker();

        Action action = actionInvoker.invoke(url);
        String context = req.getContextPath();
        Forward forward = null;

        if (action != null) {

            try (ServiceCreator serviceCreator = new ServiceCreatorImpl()) {
                action.setServiceCreator(serviceCreator);
                forward = action.execute(req, resp);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        if (forward != null && forward.isRedirect()) {
            resp.sendRedirect(context + forward.getUrl());
        } else {
            if (forward != null && forward.getUrl() != null) {
                url = forward.getUrl();
            }
            req.getRequestDispatcher("/WEB-INF/jsp" + url + ".jsp").forward(req, resp);
        }
    }

    private String getRequestUrl(HttpServletRequest req) {
        String url = req.getRequestURI(); // полный путь /hotel/......*.html
        String context = req.getContextPath(); // только имя контекста hotel
        int postfixIndex = url.lastIndexOf(".html");

        if (postfixIndex != -1) {
            return url.substring(context.length(), postfixIndex);
        } else {
            return url.substring(context.length());
        }
    }
}
