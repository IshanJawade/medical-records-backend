package com.ishanjawade.backend.doctor;

import com.ishanjawade.backend.doctor.dto.DoctorCreateReq;
import com.ishanjawade.backend.doctor.dto.DoctorUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repo;

    public DoctorService(DoctorRepository repo) {
        this.repo = repo;
    }

    public Doctor create(DoctorCreateReq req) {
        if (req.npi() != null && !req.npi().isBlank() && repo.existsByNpi(req.npi())) {
            throw new IllegalArgumentException("NPI already exists");
        }
        Doctor d = Doctor.builder()
                .name(req.name())
                .npi(req.npi())
                .specialty(req.specialty())
                .phone(req.phone())
                .build();
        return repo.save(d);
    }

    @Transactional(readOnly = true)
    public Doctor get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
    }

    @Transactional(readOnly = true)
    public List<Doctor> list() {
        return repo.findAll();
    }

    public Doctor update(Long id, DoctorUpdateReq req) {
        Doctor d = get(id);
        if (req.name() != null) d.setName(req.name());
        if (req.specialty() != null) d.setSpecialty(req.specialty());
        if (req.phone() != null) d.setPhone(req.phone());
        return repo.save(d);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Doctor not found");
        repo.deleteById(id);
    }
}
