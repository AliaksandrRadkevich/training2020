package by.training.hotel.controller.actions.room;

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
import by.training.hotel.model.service.RoomService;

public class RoomDeleteAction extends Action {

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
                RoomService roomService = getServiceCreator().getRoomService();
                roomService.delete(ids);
            } catch (ServiceCreationException | ServiceException | NumberFormatException e) {
                throw new ServletException(e);
            }

            return new Forward("/room/list.html?message=room.delete.list.message.delete.success");
        } else {
            return new Forward("/room/list.html?message=room.delete.list.message.delete.empty");
        }
    }
}
