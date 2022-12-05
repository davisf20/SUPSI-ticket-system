package ch.supsi.webapp.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties("users")
    private List<User> users;
}
