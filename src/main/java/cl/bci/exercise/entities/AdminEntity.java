package cl.bci.exercise.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity (name = "admins")
public class AdminEntity {

    @Id
    private String admin_id = UUID.randomUUID ().toString ();
    @Column (unique = true)
    private String email;

    private String password;
    private String username;

}