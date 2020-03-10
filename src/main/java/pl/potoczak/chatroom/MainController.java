package pl.potoczak.chatroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.potoczak.chatroom.entity.User;
import pl.potoczak.chatroom.repository.PostRepository;
import pl.potoczak.chatroom.repository.UserRepository;
import pl.potoczak.chatroom.service.PostService;
import pl.potoczak.chatroom.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {

    private UserService userService;
    private PostService postService;

    @Autowired
    public MainController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
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
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid User user, Errors errors, BindingResult result) {

        User userExists = userService.findUserByUsername(user.getUsername());
        if(userExists != null){
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
            userService.saveUser(user);
            return "redirect:/login?register=true";
        }
    }

    @PostMapping("/add")
    public String addMessage(@RequestParam("textareaMessage") String message, Principal principal)
    {
        if(message.isEmpty()){
            return "redirect:/index";
        }
        postService.savePost(message,userService.findUserByUsername(principal.getName()));
        return "redirect:/index";
    }
}
