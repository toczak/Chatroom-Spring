package pl.potoczak.chatroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.chatroom.entity.User;
import pl.potoczak.chatroom.repository.RoleRepository;
import pl.potoczak.chatroom.service.PostService;
import pl.potoczak.chatroom.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Arrays;

@Controller
public class MainController {

    private UserService userService;
    private PostService postService;
    private RoleRepository roleRepository;

    @Autowired
    public MainController(UserService userService, PostService postService, RoleRepository roleRepository) {
        this.userService = userService;
        this.postService = postService;
        this.roleRepository = roleRepository;
    }


    @GetMapping(value = {"/", "/index"})
    public String showIndexPage(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Principal principal) {
        return (principal == null) ? "login" : "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, Errors errors, BindingResult result) {

        if(userService.findUserByUsername(user.getUsername()) != null){
            result.rejectValue("username", "error.user",
                    "There is already a user registered with the same user name.");
        }
        if(!userService.checkPasswordMatching(user)){
            result.rejectValue("matchingPassword", "error.matchingPassword",
                    "Password and confirm password don't match.");
        }
        if(errors.hasErrors()) {
            return "register";
        }
        else {
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            userService.saveUser(user);
            return "redirect:/login?register=true";
        }
    }

}
