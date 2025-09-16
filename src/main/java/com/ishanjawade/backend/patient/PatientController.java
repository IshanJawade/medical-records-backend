package com.ishanjawade.backend.patient;

import com.ishanjawade.backend.patient.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService service;
    public PatientController(PatientService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<PatientRes> create(@Valid @RequestBody PatientCreateReq req) {
        PatientRes created = service.create(req);
        return ResponseEntity.created(URI.create("/api/patients/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public PatientRes get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<PatientRes> list() { return service.list(); }

    @PatchMapping("/{id}")
    public PatientRes update(@PathVariable Long id, @Valid @RequestBody PatientUpdateReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
