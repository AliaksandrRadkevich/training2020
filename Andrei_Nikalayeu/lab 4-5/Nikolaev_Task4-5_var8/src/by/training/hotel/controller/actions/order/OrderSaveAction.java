package by.training.hotel.controller.actions.order;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
//import by.training.hotel.model.service.exception.InvalidDateException;
import by.training.hotel.model.service.exception.InvalidRoomException;

public class OrderSaveAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String seats = req.getParameter("seats");
        RoomType roomType = null;
        Room room = null;
        User user = null;
        Long id = null;
        List<Room> rooms = null;
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");

        try {
            RoomTypeService roomTypeService = getServiceCreator().getRoomTypeService();
            roomType = roomTypeService.findById(Long.parseLong(req.getParameter("roomType")));

            RoomService roomService = getServiceCreator().getRoomService();
            room = roomService.findById(Long.parseLong(req.getParameter("room")));
            rooms = roomService.findAll();
        } catch (NullPointerException | IllegalArgumentException | ServiceException | ServiceCreationException e) {}

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {}

        if (seats != null && !seats.isEmpty() && roomType != null && startDate != null && !startDate.isEmpty()
                && endDate != null && !endDate.isEmpty()) {

            try {
                OrderService orderService = getServiceCreator().getOrderService();

                Order order = new Order();
                order.setId(id);
                order.setCreationDate(LocalDateTime.now());

                if (session != null && id == null) {
                    user = (User) session.getAttribute("session_user");
                    if (user != null) {
                        order.setUser(user);
                    }
                } else {
                    order.setUser(orderService.findById(id).getUser());
                }

                order.setRoomSeats(Byte.parseByte(seats));
                order.setRoomType(roomType);
                order.setStartDate(LocalDate.parse(startDate));
                order.setEndDate(LocalDate.parse(endDate));

                order.setRoom(room);

                orderService.save(order);
            } 
//  OrderServiceImpl, 111 строка. Убрал, так как необходимо устранить ошибку. Не успел.
//            catch (InvalidDateException e) {
//                return new Forward("/order/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
//                        + "message=order.edit.message.edit.date.error");
//            } 
            catch (InvalidRoomException e) {
                return new Forward("/order/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
                        + "message=order.edit.message.edit.room.error");
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }
        } else {
            return new Forward("/order/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
                    + "message=order.edit.message.edit.error");
        }
        return new Forward("/order/list.html?message=order.edit.message.edit.success");
    }
}
