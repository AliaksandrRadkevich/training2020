package by.training.hotel.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.training.hotel.controller.Forward;
import by.training.hotel.model.domain.user.User;

public class IndexPageAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("session_user");
            if (user != null) {
                return new Forward("/info.html");
            }
        }

        return new Forward("/login.html");
    }
}
