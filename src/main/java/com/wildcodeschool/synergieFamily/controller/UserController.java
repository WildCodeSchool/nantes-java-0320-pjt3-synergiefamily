package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.Role;
import com.wildcodeschool.synergieFamily.entity.User;
import com.wildcodeschool.synergieFamily.repository.RoleRepository;
import com.wildcodeschool.synergieFamily.repository.UserRepository;
import com.wildcodeschool.synergieFamily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/init")
    @ResponseBody
    public User init() {
        if (!roleRepository.findByName("ROLE_COORDINATEUR").isPresent()) {
            roleRepository.save(new Role("ROLE_COORDINATEUR"));
        }
        if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        Optional<Role> optionalRole =roleRepository.findByName("ROLE_ADMIN");
        User user = new User("bastien@gmail.com", passwordEncoder.encode("tacos"));
        if (optionalRole.isPresent()) {
            user.getRoles().add(optionalRole.get());
        }
        return userRepository.save(user);
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index?logout";
    }

    @GetMapping("/user-creation")
    public String getRegister() {
        return "user-creation";
    }

    @PostMapping("/user-creation")
    public String postRegister(HttpServletRequest request,
                               @RequestParam String email) {
        String password = "test";//TODO génerer le mot de passe aléatoirement à envoyer  par mail
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_COORDINATEUR");
        if (optionalRole.isPresent()) {
            user.getRoles().add(optionalRole.get());
            userRepository.save(user);

            userService.autoLogin(request, email, password);
            return "redirect:/profile";

        }
        return "redirect:/user-creation";
    }


    @GetMapping("/user-management")
    public String getUserManagement(Model out) {

        out.addAttribute("users", userRepository.findAll());

        return "user-management";
    }

    @GetMapping("/users")
    public List<User> showAllUsers() {

        return userRepository.findAll();
    }

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

}


