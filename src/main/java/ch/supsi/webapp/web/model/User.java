package ch.supsi.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "fk_role")
    @JsonIgnoreProperties("role")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("tickets")
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "assignedTo")
    @JsonIgnoreProperties("assignedTickets")
    private List<Ticket> assignedTickets;

    @ManyToMany(mappedBy = "watchers")
    @JsonIgnoreProperties("watchers")
    private List<Ticket> watchedTickets;
}
