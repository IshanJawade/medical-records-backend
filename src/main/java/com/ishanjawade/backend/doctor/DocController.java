package com.ishanjawade.backend.doctor;

import com.ishanjawade.backend.doctor.dto.DoctorCreateReq;
import com.ishanjawade.backend.doctor.dto.DoctorRes;
import com.ishanjawade.backend.doctor.dto.DoctorUpdateReq;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DocController {

    @Autowired
    private DocService service;

    public DocController(DocService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DoctorRes> create(@Valid @RequestBody DoctorCreateReq req) {
        Doctor created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/doctors/" + created.getId()))
                .body(DoctorRes.of(created));
    }

    @GetMapping("/{id}")
    public DoctorRes getById(@PathVariable Long id) {
        return DoctorRes.of(service.get(id));
    }

    @GetMapping
    public List<DoctorRes> list() {
        return service.list().stream().map(DoctorRes::of).toList();
    }

    @PatchMapping("/{id}")
    public DoctorRes update(@PathVariable Long id, @Valid @RequestBody DoctorUpdateReq req) {
        return DoctorRes.of(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
