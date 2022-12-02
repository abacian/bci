package cl.bci.exercise.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity (name = "phones")
public class PhoneEntity {

    @Id
    private String phone_id = UUID.randomUUID ().toString ();

    private String cityCode;
    private String countryCode;
    private String number;

    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;
    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private LocalDateTime modified;
    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private LocalDateTime lastLogin;

}