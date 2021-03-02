package by.training.hotel.controller.actions.order;

import java.io.IOException;
import java.util.ArrayList;
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

public class OrderDeleteListAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<Order> orders;

        if (idsStr != null) {
            orders = new ArrayList<Order>(idsStr.length);

            try {
                OrderService orderService = getServiceCreator().getOrderService();

                for (String id : idsStr) {
                    orders.add(orderService.findById(Long.valueOf(id)));
                }

                req.setAttribute("orders", orders);
            } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
                throw new ServletException(e);
            }

            return null;
        } else {
            return new Forward("/order/list.html?message=order.delete.list.message.delete.empty");
        }
    }
}
