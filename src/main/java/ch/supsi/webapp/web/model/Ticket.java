package ch.supsi.webapp.web.model;

import lombok.*;

import javax.persistence.*;


enum STATUS {
    OPEN,
    IN_PROGRESS,
    DONE,
    CLOSED
}

@Entity
@Data
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

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private STATUS status;
}
