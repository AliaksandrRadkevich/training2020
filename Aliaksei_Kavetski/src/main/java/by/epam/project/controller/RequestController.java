package by.epam.project.controller;

import by.epam.project.model.Request;
import by.epam.project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RequestController {

    private final RequestService requestService;


    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }



    @GetMapping("/requests")
    public String findAll(Model model){
        List<Request> requests = requestService.findAll();
        model.addAttribute("requests", requests);
        return "requestlist";
    }

    @GetMapping("/requestcreate")
    public String createApplicationForm(Request request){
        return "requestcreate";
    }

    @PostMapping("/requestcreate")
    public String createRequest(Request request){
        requestService.saveRequest(request);
        return "redirect:/requests";
    }

    @GetMapping("requestdelete/{id}")
    public String deleteRequest(@PathVariable("id") Long id){
        requestService.deleteById(id);
        return "redirect:/requests";
    }

    @GetMapping("/requestupdate/{id}")
    public String updateRequestForm(@PathVariable("id") Long id, Model model){
        Request request = requestService.findById(id);
        model.addAttribute("request", request);
        return "requestupdate";
    }

    @PostMapping("/requestupdate")
    public String updateUser(Request request){
        requestService.saveRequest(request);
        return "redirect:/requests";
    }
}
