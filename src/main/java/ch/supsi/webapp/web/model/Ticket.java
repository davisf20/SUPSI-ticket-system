package ch.supsi.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


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

    @OneToOne
    @JoinColumn(name = "fk_attachment")
    private Attachment attachment;
}
