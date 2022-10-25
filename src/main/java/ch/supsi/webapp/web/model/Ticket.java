package ch.supsi.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonIgnoreProperties("tickets")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    @JsonIgnoreProperties("tickets")
    private Status status;

    @Column
    private LocalDateTime creationDate;
}
