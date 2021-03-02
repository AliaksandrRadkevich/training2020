package by.training.hotel.controller.actions.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.order.Order;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.domain.user.User;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.OrderService;
import by.training.hotel.model.service.RoomService;
import by.training.hotel.model.service.RoomTypeService;
import by.training.hotel.model.service.UserService;

public class OrderEditAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<RoomType> roomTypes = null;
        List<Room> rooms = null;
        List<User> users = null;
        List<Order> orders = null;

        try {
            RoomTypeService roomTypeService = getServiceCreator().getRoomTypeService();
            roomTypes = roomTypeService.findAll();

            RoomService roomService = getServiceCreator().getRoomService();
            rooms = roomService.findAll();

            UserService userService = getServiceCreator().getUserService();
            users = userService.findAll();

        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }

        if (id != null) {
            try {
                OrderService orderService = getServiceCreator().getOrderService();
                Order order = orderService.findById(Long.parseLong(id));

                orders = orderService.findAll();

                if (order != null) {
                    req.setAttribute("order", order);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            } catch (IllegalArgumentException e) {
                resp.sendError(404);
                return null;
            }
        }

        req.setAttribute("roomTypes", roomTypes);
        req.setAttribute("rooms", rooms);
        req.setAttribute("users", users);
        req.setAttribute("orders", orders);
        return null;
    }
}
