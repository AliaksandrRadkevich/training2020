package by.epam.project.service;

import by.epam.project.model.Request;
import by.epam.project.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request findById(Long id){
        return requestRepository.getOne(id);
    }

    public List<Request> findAll(){
        return requestRepository.findAll();
    }

    public Request saveRequest(Request request){
        return requestRepository.save(request);
    }

    public void deleteById(Long id){
        requestRepository.deleteById(id);
    }
}
