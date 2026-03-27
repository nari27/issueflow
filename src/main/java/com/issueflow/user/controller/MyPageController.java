package com.issueflow.user.controller;

import com.issueflow.user.dto.UserMeResponse;
import com.issueflow.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final UserService userService;

    @GetMapping("/users/me")
    public UserMeResponse getMyInfo(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return userService.getMyInfo(userId);
    }
}