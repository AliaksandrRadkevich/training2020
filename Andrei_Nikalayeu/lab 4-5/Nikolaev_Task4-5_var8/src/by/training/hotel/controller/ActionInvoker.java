package by.training.hotel.controller;

import java.util.HashMap;
import java.util.Map;

import by.training.hotel.controller.actions.Action;
import by.training.hotel.controller.actions.IndexPageAction;
import by.training.hotel.controller.actions.InfoPageAction;
import by.training.hotel.controller.actions.LoginAction;
import by.training.hotel.controller.actions.LogoutAction;
import by.training.hotel.controller.actions.order.OrderDeleteAction;
import by.training.hotel.controller.actions.order.OrderDeleteListAction;
import by.training.hotel.controller.actions.order.OrderEditAction;
import by.training.hotel.controller.actions.order.OrderListAction;
import by.training.hotel.controller.actions.order.OrderSaveAction;
import by.training.hotel.controller.actions.room.RoomListAction;
import by.training.hotel.controller.actions.room.RoomSaveAction;
import by.training.hotel.controller.actions.room.RoomDeleteAction;
import by.training.hotel.controller.actions.room.RoomDeleteListAction;
import by.training.hotel.controller.actions.room.RoomEditAction;
import by.training.hotel.controller.actions.user.UserListAction;
import by.training.hotel.controller.actions.user.UserSaveAction;
import by.training.hotel.controller.actions.user.UserDeleteListAction;
import by.training.hotel.controller.actions.user.UserDeleteAction;
import by.training.hotel.controller.actions.user.UserEditAction;

public class ActionInvoker {

    private Map<String, Action> actionMap = new HashMap<String, Action>();

    public ActionInvoker() {
        actionMap.put("/", new IndexPageAction());
        actionMap.put("/index", new IndexPageAction());

        actionMap.put("/info", new InfoPageAction());

        actionMap.put("/login", new LoginAction());
        actionMap.put("/logout", new LogoutAction());

        actionMap.put("/user/list", new UserListAction());
        actionMap.put("/user/edit", new UserEditAction());
        actionMap.put("/user/save", new UserSaveAction());
        actionMap.put("/user/delete", new UserDeleteAction());
        actionMap.put("/user/delete_list", new UserDeleteListAction());

        actionMap.put("/room/list", new RoomListAction());
        actionMap.put("/room/edit", new RoomEditAction());
        actionMap.put("/room/save", new RoomSaveAction());
        actionMap.put("/room/delete", new RoomDeleteAction());
        actionMap.put("/room/delete_list", new RoomDeleteListAction());

        actionMap.put("/order/list", new OrderListAction());
        actionMap.put("/order/edit", new OrderEditAction());
        actionMap.put("/order/save", new OrderSaveAction());
        actionMap.put("/order/delete", new OrderDeleteAction());
        actionMap.put("/order/delete_list", new OrderDeleteListAction());
    }

    public Action invoke(String command) {
        return actionMap.get(command);
    }
}
