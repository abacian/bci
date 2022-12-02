package cl.bci.example.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity (name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    private UUID id = UUID.randomUUID ();

    @NonNull
    private String email;
    private String name;
    private String password;
    private String token;
    private String created;
    private String modified;
    private String lastLogin;
    private Boolean isActive;

    @OneToMany
    private List <PhoneEntity> phones;

}