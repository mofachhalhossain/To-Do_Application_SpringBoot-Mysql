package com.example.springProject.springProject.Controller;

import com.example.springProject.springProject.Dao.UserRepository;
import com.example.springProject.springProject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user_dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println(username);

        User user = userRepository.getUserByUserName(username);
        System.out.println("user info " + user);

        model.addAttribute("user", user);
        return "user_dashboard";
    }
}
