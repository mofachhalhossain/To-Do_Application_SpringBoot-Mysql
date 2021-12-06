package com.example.springProject.springProject.Controller;

import com.example.springProject.springProject.Dao.ToDoRepository;
import com.example.springProject.springProject.Dao.UserRepository;
import com.example.springProject.springProject.entities.ToDo;
import com.example.springProject.springProject.entities.User;
import com.example.springProject.springProject.helper.message;
import org.aspectj.bridge.Message;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoRepository toDoRepository;


    /*common data for response*/
    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println(username);

        User user = userRepository.getUserByUserName(username);
        System.out.println("user info " + user);

        model.addAttribute("user", user);
    }

    /*Dashboard home*/
    @RequestMapping("/user_dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "user_dashboard";
    }

    /*Write Todo handler*/
    @GetMapping("/write_ToDo")
    public String openAddWriteToDo(Model model) {
        model.addAttribute("title", "Write To-Do");
        model.addAttribute("ToDo", new ToDo());

        return "write_ToDo";
    }

    @GetMapping("/home_dashboard")
    public String homeDashboard(Model model) {
        model.addAttribute("title", "Home Dashboard");
        return "home_dashboard";
    }


    /*write to do process handler*/
    @PostMapping("/process-ToDo")
    public String processToDo(@ModelAttribute ToDo to_do, Principal principal, HttpSession session) {

        try {
            String name = principal.getName();
            User user = this.userRepository.getUserByUserName(name);
            to_do.setUser(user);

            user.getTodo().add(to_do);

            /*fetching user entity in jparepository*/
            this.userRepository.save(user);

            /*message success*/
            session.setAttribute("message", new message("Successfully added", "success"));

            /*debug console*/
            System.out.println("Data" + to_do);
            System.out.println("Added to database");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            e.printStackTrace();

            /*error message*/
            session.setAttribute("message", new message("Went wrong! Try again", "danger"));
        }


        return "home_dashboard";
    }


    /*Dashboard handler*/
    @GetMapping("show_dashboard")
    public String show_dashboard(Model m, Principal principal) {
        m.addAttribute("title", "Dashboard");

        String name = principal.getName();
        User user = this.userRepository.getUserByUserName(name);

        List<ToDo> toDoByUser = this.toDoRepository.findToDoByUser(user.getId());
        m.addAttribute("todo", toDoByUser);


        /*String userName = principal.getName();
        User user = this.userRepository.getUserByUserName(userName);
        List<ToDo> todo = user.getTodo();*/

        return "show_dashboard";
    }


    /*Delete Button Handler*/
    @GetMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") Integer id, Model model, HttpSession session, Principal principal) {

        Optional<ToDo> toDoOptional = this.toDoRepository.findById(id);
        ToDo toDo = toDoOptional.get();

        /*For delete info in database also*/
        User user = this.userRepository.getUserByUserName(principal.getName());
        user.getTodo().remove(toDo);
        this.userRepository.save(user);


        /*For keep record in database*/
        /*toDo.setUser(null);

        this.toDoRepository.delete(toDo);*/


        session.setAttribute("message", new message("Deleted Successfully", "Successful"));

        return "delete_success";
    }

    /*User profile*/
    @RequestMapping("/profile")
    public String userProfile(Model model) {
        model.addAttribute("title", "Profile");

        return "profile";
    }


}
