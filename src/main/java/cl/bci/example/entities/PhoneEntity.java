package cl.bci.example.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity (name = "phones")
@Getter
@Setter
public class PhoneEntity {

    @Id
    private String id;
    private String cityCode;
    private String countryCode;
    private String number;
    private String created;
    private String modified;

}
