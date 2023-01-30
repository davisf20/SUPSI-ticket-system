package ch.supsi.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String text;

    @Column
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonIgnoreProperties("author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "fk_ticket")
    @JsonIgnoreProperties("ticket")
    private Ticket ticket;
}
