package com.example.springProject.springProject.Controller;

import com.example.springProject.springProject.Dao.UserRepository;
import com.example.springProject.springProject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

}
