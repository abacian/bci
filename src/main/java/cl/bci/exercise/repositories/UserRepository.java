package cl.bci.exercise.repositories;

import cl.bci.exercise.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
extends JpaRepository <UserEntity, Integer> {

    UserEntity findByEmail (String email);

}