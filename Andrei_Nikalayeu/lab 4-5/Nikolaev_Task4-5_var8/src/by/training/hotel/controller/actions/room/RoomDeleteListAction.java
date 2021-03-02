package by.training.hotel.controller.actions.room;

import java.io.IOException;
import java.util.ArrayList;
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

public class RoomDeleteListAction extends Action {

    @Override
    public Forward execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idsStr[] = req.getParameterValues("id");
        List<Room> rooms;

        if (idsStr != null) {
            rooms = new ArrayList<Room>(idsStr.length);

            try {
                RoomService roomService = getServiceCreator().getRoomService();

                for (String id : idsStr) {
                    rooms.add(roomService.findById(Long.valueOf(id)));
                }

                req.setAttribute("rooms", rooms);
            } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
                throw new ServletException(e);
            }

            return null;
        } else {
            return new Forward("/room/list.html?message=room.delete.list.message.delete.empty");
        }
    }
}
