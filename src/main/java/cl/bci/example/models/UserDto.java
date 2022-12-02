package cl.bci.example.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity (name = "users")
@Getter
@Setter
@ToString
public class UserDto {

    @Id
    private String id = UUID.randomUUID ().toString ();

    @Column (unique = true)
    @NonNull
    private String email;
    private String name;
    private String password;
    private String token;
    private String lastLogin;
    private Boolean isActive;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime modified;

    @OneToMany
    private List <PhoneDto> phones;

}