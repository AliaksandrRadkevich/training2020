package by.training.hotel.controller.actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.hotel.controller.Forward;
import by.training.hotel.model.di.ServiceCreator;

public abstract class Action {
    private ServiceCreator serviceCreator;

    public ServiceCreator getServiceCreator() {
        return serviceCreator;
    }

    public void setServiceCreator(ServiceCreator serviceCreator) {
        this.serviceCreator = serviceCreator;
    }

    public abstract Forward execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;
}
