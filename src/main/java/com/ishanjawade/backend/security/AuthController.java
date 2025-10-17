package com.ishanjawade.backend.security;

import com.ishanjawade.backend.security.dto.AuthRequest;
import com.ishanjawade.backend.security.dto.AuthResponse;
import com.ishanjawade.backend.user.User;
import com.ishanjawade.backend.user.UserService;
import com.ishanjawade.backend.user.dto.UserCreateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
	private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserCreateRequest request) {
		User user = userService.create(request);
		UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
		User user = userService.findByEmail(request.email());
		if (!passwordEncoder.matches(request.password(), user.getPassword())) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
		}
		UserDetails userDetails = userService.loadUserByUsername(user.getEmail());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
