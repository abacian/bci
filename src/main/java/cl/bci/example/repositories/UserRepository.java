package cl.bci.example.repositories;

import cl.bci.example.models.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
extends JpaRepository <UserDto, Long> {

    UserDto findByEmail (String email);

}