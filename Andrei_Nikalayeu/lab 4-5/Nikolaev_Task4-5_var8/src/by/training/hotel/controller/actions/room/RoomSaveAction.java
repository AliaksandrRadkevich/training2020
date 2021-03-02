package by.training.hotel.controller.actions.room;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.domain.room.RoomType;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.RoomService;
import by.training.hotel.model.service.RoomTypeService;
import by.training.hotel.model.service.exception.RoomNumberNotUniqueException;

public class RoomSaveAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        String seats = req.getParameter("seats");
        String price = req.getParameter("price");
        RoomType roomType = null;
        Long id = null;
        Room room = null;

        try {
            RoomTypeService roomTypeService = getServiceCreator().getRoomTypeService();
            roomType = roomTypeService.findById(Long.parseLong(req.getParameter("roomType")));
        } catch (NullPointerException | IllegalArgumentException | ServiceException | ServiceCreationException e) {}

        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e) {
        }

        if (number != null & !number.isEmpty() && seats != null & !seats.isEmpty() && price != null & !price.isEmpty()
                && roomType != null) {

            room = new Room();
            room.setId(id);
            room.setRoomNumber(number);
            room.setRoomType(roomType);
            room.setSeats(Byte.parseByte(seats));
            room.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));

            try {
                RoomService roomService = getServiceCreator().getRoomService();
                roomService.save(room);
            } catch (RoomNumberNotUniqueException e) {
                return new Forward("/room/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
                        + "message=room.edit.message.edit.number.error");
            } catch (ServiceCreationException | ServiceException e) {
                throw new ServletException(e);
            }

        } else {
            return new Forward("/room/edit.html?" + ((id != null) ? "id=" + id + "&" : "")
                    + "message=room.edit.message.edit.error");
        }

        return new Forward("/room/list.html?message=room.edit.message.edit.success");
    }
}
