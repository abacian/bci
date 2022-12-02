package cl.bci.example.repositories;

import cl.bci.example.models.PhoneDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository
extends JpaRepository <PhoneDto, Long> {
}