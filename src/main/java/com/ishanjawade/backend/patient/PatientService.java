package com.ishanjawade.backend.patient;

import com.ishanjawade.backend.patient.dto.PatientCreateReq;
import com.ishanjawade.backend.patient.dto.PatientRes;
import com.ishanjawade.backend.patient.dto.PatientUpdateReq;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatientService {

    @Autowired
    private PatientRepository repo;

    public PatientService(PatientRepository repo) {
        this.repo = repo;
    }

    public PatientRes create(PatientCreateReq req) {
        if (repo.existsByMrn(req.mrn())) {
            throw new IllegalArgumentException("MRN already exists");
        }
        Patient p = Patient.builder()
                //.userId(req.userId())
                .mrn(req.mrn())
                .name(req.name())
                .dob(req.dob())
                .sex(req.sex())
                .phone(req.phone())
                .addressLine1(req.addressLine1())
                .addressLine2(req.addressLine2())
                .city(req.city())
                .state(req.state())
                .zip(req.zip())
                .build();
        return PatientRes.of(repo.save(p));
    }

    @Transactional(readOnly = true)
    public Patient getEntity(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Patient not found"));
    }

    @Transactional(readOnly = true)
    public PatientRes get(Long id) { return PatientRes.of(getEntity(id)); }

    @Transactional(readOnly = true)
    public List<PatientRes> list() {
        return repo.findAll().stream().map(PatientRes::of).toList();
    }

    public PatientRes update(Long id, PatientUpdateReq req) {
        Patient p = getEntity(id);
        if (req.name()!=null) p.setName(req.name());
        if (req.dob()!=null) p.setDob(req.dob());
        if (req.sex()!=null) p.setSex(req.sex());
        if (req.phone()!=null) p.setPhone(req.phone());
        if (req.addressLine1()!=null) p.setAddressLine1(req.addressLine1());
        if (req.addressLine2()!=null) p.setAddressLine2(req.addressLine2());
        if (req.city()!=null) p.setCity(req.city());
        if (req.state()!=null) p.setState(req.state());
        if (req.zip()!=null) p.setZip(req.zip());
        return PatientRes.of(repo.save(p));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Patient not found");
        repo.deleteById(id); // for HIPAA-style systems you may want soft-delete/archive instead
    }
}

