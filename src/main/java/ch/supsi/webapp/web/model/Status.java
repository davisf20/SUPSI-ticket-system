package ch.supsi.webapp.web.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "status")
    @JsonIgnoreProperties("status")
    private List<Ticket> tickets;
}
