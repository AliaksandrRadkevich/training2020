package by.training.hotel.controller.actions.room;

import java.io.IOException;
import java.util.List;

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

public class RoomEditAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<RoomType> roomTypes = null;

        try {
            RoomTypeService roomTypeService = getServiceCreator().getRoomTypeService();
            roomTypes = roomTypeService.findAll();

        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }

        if (id != null) {
            try {

                RoomService roomService = getServiceCreator().getRoomService();
                Room room = roomService.findById(Long.parseLong(id));

                if (room != null) {
                    req.setAttribute("room", room);
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
        return null;
    }
}
