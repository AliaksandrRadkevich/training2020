package by.epam.project.service;

import by.epam.project.model.Route;

import by.epam.project.model.User;
import by.epam.project.repository.RouteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

        private final RouteRepository routeRepository;

        @Autowired
        public RouteService(RouteRepository routeRepository) {
            this.routeRepository = routeRepository;
        }

    public Route findById(Long id){
        return routeRepository.getOne(id);
    }

    public List<Route> findAll(){
        return routeRepository.findAll();
    }

    public Route saveRoute(Route route){
        return routeRepository.save(route);
    }

    public void deleteById(Long id){
        routeRepository.deleteById(id);
    }
}
