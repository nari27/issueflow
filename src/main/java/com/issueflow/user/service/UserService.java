package com.issueflow.user.service;

import com.issueflow.config.jwt.JwtProvider;
import com.issueflow.global.exception.NotFoundException;
import com.issueflow.user.dto.LoginRequest;
import com.issueflow.user.dto.LoginResponse;
import com.issueflow.user.dto.SignupRequest;
import com.issueflow.user.dto.UserMeResponse;
import com.issueflow.user.entity.User;
import com.issueflow.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;


    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .name(request.getName())
                .role("USER")
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtProvider.createToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new LoginResponse(
                "로그인 성공",
                user.getEmail(),
                user.getName(),
                accessToken
        );
    }

    public UserMeResponse getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("사용자를 찾을 수 없습니다."));

        return new UserMeResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole()
        );
    }
}