package by.training.hotel.controller.actions.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.order.Order;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.OrderService;

public class OrderListAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            OrderService orderService = getServiceCreator().getOrderService();
            List<Order> orders = orderService.findAll();
            req.setAttribute("orders", orders);

            return null;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
