package cl.bci.exercise.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity (name = "users")
public class UserEntity {

    @Id
    private String user_id = UUID.randomUUID ().toString ();
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

    @OneToMany (targetEntity = PhoneEntity.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "user_id", referencedColumnName = "user_id")
    private List <PhoneEntity> phones;

}