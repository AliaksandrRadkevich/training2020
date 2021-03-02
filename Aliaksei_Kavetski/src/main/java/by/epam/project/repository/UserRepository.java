package by.epam.project.repository;
import by.epam.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByLogin(String login);
}
