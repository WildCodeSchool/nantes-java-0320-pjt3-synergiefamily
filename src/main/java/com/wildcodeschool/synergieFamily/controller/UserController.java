package com.wildcodeschool.synergieFamily.controller;

import com.wildcodeschool.synergieFamily.entity.Role;
import com.wildcodeschool.synergieFamily.entity.User;
import com.wildcodeschool.synergieFamily.repository.RoleRepository;
import com.wildcodeschool.synergieFamily.repository.UserRepository;
import com.wildcodeschool.synergieFamily.service.EmailService;
import com.wildcodeschool.synergieFamily.service.UserService;
import com.wildcodeschool.synergieFamily.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    @Autowired
    private EmailService emailService;

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
    public String getRegister(Model out,
                              @RequestParam(required = false) Long id) {
        User user = new User();
        if (id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        out.addAttribute("user", user);
        return "user-creation";
    }

    @PostMapping("/user-creation")
    public String postRegister(HttpServletRequest request,
                               @RequestParam String email,
                               @RequestParam(name = "role_id") Long roleId) {
        User user = new User();
        String password = user.randomPassword(8); // "test"
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Optional<Role> optionalRole = roleRepository.findById(roleId);
        if (optionalRole.isPresent()) {
            user.getRoles().add(optionalRole.get());
            userRepository.save(user);
            emailService.sendNewUserEmail(user.getEmail(), password);
        }
        return "redirect:/user-management";
    }

    @GetMapping("/user-edition")
    public String getUserCreation(Model out,
                                  @RequestParam(required = false) Long id) {

        User user = new User();
        if (id != null) {
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
        }
        out.addAttribute("user", user);
        return "user-edition";
    }

    @PostMapping("/user-edition")
    public String postUser(@RequestParam Long id,
                    @RequestParam String email,
                    @RequestParam Long roleId) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isPresent()) {
                List<Role> roles = new ArrayList<>();
                roles.add(role.get());
                user.get().setRoles(roles);
                userRepository.save(user.get());
            }
        }

        return "redirect:/user-management";
    }

    @GetMapping("/profile")
    public String getProfileUser(Model out) {

        User user = userService.getLoggedUser();
        out.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public String modificationProfile(@ModelAttribute User user) {

        User logged = userService.getLoggedUser();
        logged.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(logged);
        return "profile";
    }

    @GetMapping("/user-management")
    public String getUserManagement(Model out) {

        out.addAttribute("users", userRepository.findAllActiveUsers());
        out.addAttribute("loggedId", userService.getLoggedUser().getId());
        return "user-management";
    }

    @GetMapping("/user/disable")
    public String disableUser(@RequestParam Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            if (!user.getEmail().equals(userService.getLoggedUser().getEmail())) {

                user.setDisabled(true);
                userRepository.save(user);
            } else {

                //TODO: display a message to prevent the logged user that he can't delete his own account.
            }
        }

        return "redirect:/user-management";
    }

    @GetMapping("/login")
    public String getLogin() {

        return "login";
    }

}


