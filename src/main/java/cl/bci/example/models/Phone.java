package cl.bci.example.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@Entity (name = "phones")
@NoArgsConstructor
@ToString
public class Phone {

    @Id
    private Integer pid;
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