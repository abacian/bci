package cl.bci.example.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Data
@Entity (name = "users")
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    //private String user_id = UUID.randomUUID ().toString ();

    @Column (unique = true)
    private String email;

    private String name;
    private String password;
    private String token;
    private Boolean isActive;

    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private LocalDateTime modified;
    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastLogin;

    @OneToMany (targetEntity = Phone.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "up_fk", referencedColumnName = "id")
    private List <Phone> phones;

}