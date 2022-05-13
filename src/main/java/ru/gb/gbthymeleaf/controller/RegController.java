package ru.gb.gbthymeleaf.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.gbapi.security.AuthenticationUserDto;
import ru.gb.gbapi.security.UserDto;
import ru.gb.gbshopmart.service.UserService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class RegController {

    private final UserService userService;


    @GetMapping("/register")
    public String getRegister(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "reg-form";
    }

    @PostMapping
    public String registerUser(UserDto user) {
        ResponseEntity<UserDto> response = userService.register(user);
        if (response.getStatusCode() == HttpStatus.CREATED)
            return "redirect:/auth-form";
        else
            return "reg-form";
    }
}