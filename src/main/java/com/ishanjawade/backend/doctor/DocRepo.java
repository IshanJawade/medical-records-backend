package com.ishanjawade.backend.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocRepo extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByNpi(String npi);
    boolean existsByNpi(String npi);
}
