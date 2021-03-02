package by.epam.project.repository;


import by.epam.project.model.Request;
import by.epam.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
