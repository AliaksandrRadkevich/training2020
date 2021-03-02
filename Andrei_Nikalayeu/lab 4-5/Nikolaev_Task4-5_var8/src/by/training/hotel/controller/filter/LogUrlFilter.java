package by.training.hotel.controller.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.training.hotel.model.domain.user.User;

public class LogUrlFilter implements Filter {
    private static Logger log = LogManager.getLogger();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws java.io.IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;

        String url = httpReq.getRequestURI();
        HttpSession session = httpReq.getSession(false);

        String ipAddress = req.getRemoteAddr();

        String sessionLogin = "empty";

        if (session != null) {
            User user = (User) session.getAttribute("session_user");

            if (user != null) {
                sessionLogin = user.getLogin();
            }

        }

        log.trace("User " + sessionLogin + ", IP " + ipAddress + ", Page: " + url);
        chain.doFilter(req, resp);
    }

}