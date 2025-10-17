package com.ishanjawade.backend.user;

import com.ishanjawade.backend.user.dto.UserCreateRequest;
import com.ishanjawade.backend.user.dto.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User create(UserCreateRequest req) {
        if (req.getEmail() != null && !req.getEmail().isBlank() && repo.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        user.setRole(req.getRole());
        user.setEntityId(req.getEntityId());
        return repo.save(user);
    }

    @Transactional(readOnly = true)
    public User get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<User> list() {
        return repo.findAll();
    }

    public User update(Long id, UserUpdateRequest req) {
        User user = get(id);
        if (req.getEmail() != null) user.setEmail(req.getEmail());
        if (req.getPassword() != null) user.setPassword(req.getPassword());
        if (req.getRole() != null) user.setRole(req.getRole());
        if (req.getEntityId() != null) user.setEntityId(req.getEntityId());
        return repo.save(user);
    }

    public void delete(Long id) {
        User user = get(id);
        repo.delete(user);
    }
}
