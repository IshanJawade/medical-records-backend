package com.ishanjawade.backend.reception;

import com.ishanjawade.backend.reception.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/receptions")
public class ReceptionController {

    private final ReceptionService service;

    public ReceptionController(ReceptionService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ReceptionRes> create(@Valid @RequestBody ReceptionCreateReq req) {
        ReceptionRes created = service.create(req);
        return ResponseEntity.created(URI.create("/api/receptions/" + created.id())).body(created);
    }

    @GetMapping("/{id}")
    public ReceptionRes get(@PathVariable Long id) { return service.get(id); }

    @GetMapping
    public List<ReceptionRes> list() { return service.list(); }

    @PatchMapping("/{id}")
    public ReceptionRes update(@PathVariable Long id, @Valid @RequestBody ReceptionUpdateReq req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
