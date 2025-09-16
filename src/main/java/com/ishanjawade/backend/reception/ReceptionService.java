package com.ishanjawade.backend.reception;

import com.ishanjawade.backend.reception.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReceptionService {

    private final ReceptionRepository repo;

    public ReceptionService(ReceptionRepository repo) { this.repo = repo; }

    public ReceptionRes create(ReceptionCreateReq req) {
        if (req.email() != null && repo.existsByEmail(req.email())) {
            throw new IllegalArgumentException("Email already exists");
        }
        Reception r = Reception.builder()
                //.userId(req.userId())
                .name(req.name())
                .phone(req.phone())
                .email(req.email())
                .build();
        return ReceptionRes.of(repo.save(r));
    }

    @Transactional(readOnly = true)
    public Reception getEntity(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Reception not found"));
    }

    @Transactional(readOnly = true)
    public ReceptionRes get(Long id) { return ReceptionRes.of(getEntity(id)); }

    @Transactional(readOnly = true)
    public List<ReceptionRes> list() {
        return repo.findAll().stream().map(ReceptionRes::of).toList();
    }

    public ReceptionRes update(Long id, ReceptionUpdateReq req) {
        Reception r = getEntity(id);
        if (req.name() != null) r.setName(req.name());
        if (req.phone() != null) r.setPhone(req.phone());
        if (req.email() != null) r.setEmail(req.email());
        return ReceptionRes.of(repo.save(r));
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Reception not found");
        repo.deleteById(id);
    }
}
