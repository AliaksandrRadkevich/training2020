package by.epam.project.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name= "requests")
public class Request {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "description")
        private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
