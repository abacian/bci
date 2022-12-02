package cl.bci.example.repositories;

import cl.bci.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
extends JpaRepository <User, Integer> {

    User findByEmail (String email);

}