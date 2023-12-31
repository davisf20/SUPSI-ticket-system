package ch.supsi.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String title;

    @ManyToOne
    @JoinColumn(name = "fk_type")
    @JsonIgnoreProperties("type")
    private Type type;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonIgnoreProperties("user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_status")
    @JsonIgnoreProperties("status")
    private Status status;

    @Column
    private LocalDateTime creationDate;

    @Column
    private LocalDateTime dueDate;

    @ManyToOne
    @JoinColumn(name = "fk_assignedTo")
    @JsonIgnoreProperties("assignedTo")
    private User assignedTo;

    @Column
    private Integer estimatedTime;

    @Column
    private Integer timeSpent;

    @OneToOne
    @JoinColumn(name = "fk_attachment")
    private Attachment attachment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ticket_watcher",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnoreProperties("watchedTickets")
    private List<User> watchers;
}
