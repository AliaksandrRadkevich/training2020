package by.training.hotel.controller.actions.room;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.controller.actions.Action;
import by.training.hotel.model.domain.room.Room;
import by.training.hotel.model.di.ServiceCreationException;
import by.training.hotel.model.service.ServiceException;
import by.training.hotel.model.service.RoomService;

public class RoomListAction extends Action{
    
    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            RoomService roomService = getServiceCreator().getRoomService();
            List<Room> rooms = roomService.findAll();
            req.setAttribute("rooms", rooms);

            return null;
        } catch (ServiceCreationException | ServiceException e) {
            throw new ServletException(e);
        }
    }
}
