package by.epam.project.controller;

import by.epam.project.model.Route;
import by.epam.project.model.User;
import by.epam.project.service.RouteService;
import by.epam.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    public String findAll(Model model){
        List<Route> routes = routeService.findAll();
        model.addAttribute("routes", routes);
        return "routelist";
    }

    @GetMapping("/routecreate")
    public String createRouteForm(Route route){
        return "routecreate";
    }

    @PostMapping("/routecreate")
    public String createRoute(Route route){
        routeService.saveRoute(route);
        return "redirect:/routes";
    }

    @GetMapping("routedelete/{id}")
    public String deleteRoute(@PathVariable("id") Long id){
        routeService.deleteById(id);
        return "redirect:/routes";
    }

    @GetMapping("/routeupdate/{id}")
    public String updateRouteForm(@PathVariable("id") Long id, Model model){
        Route route = routeService.findById(id);
        model.addAttribute("route", route);
        return "routeupdate";
    }

    @PostMapping("/routeupdate")
    public String updateRoute(Route route){
        routeService.saveRoute(route);
        return "redirect:/routes";
    }
}
