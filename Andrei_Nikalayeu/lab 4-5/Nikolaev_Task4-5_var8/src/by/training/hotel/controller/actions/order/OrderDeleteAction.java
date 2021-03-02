package by.training.hotel.controller.actions.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.OrderService;

public class OrderDeleteAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<Long> ids;

        if (idsStr != null) {
            ids = new ArrayList<Long>(idsStr.length);

            for (String id : idsStr) {
                ids.add(Long.valueOf(id));
            }

            try {
                OrderService orderService = getServiceCreator().getOrderService();
                orderService.delete(ids);
            } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
                throw new ServletException(e);
            }

            return new Forward("/order/list.html?message=order.delete.list.message.delete.success");
        } else {
            return new Forward("/order/list.html?message=order.delete.list.message.delete.empty");
        }
    }
}
