package com.example.activityplanner.controller;

import com.example.activityplanner.dto.TaskDTO;
import com.example.activityplanner.dto.UserDTO;
import com.example.activityplanner.model.Task;
import com.example.activityplanner.model.User;
import com.example.activityplanner.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/tancShoes")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping(value = "/homePage")
   public String index(Model model) {

        List<Task> taskList = userService.viewAllTasks();
        model.addAttribute("allTasks", taskList);
        return "homePage";
   }
    @GetMapping(value = "/login")
    public String loginPage(UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        String message = userService.login(email, password);

        if("successful".equals(message)) {
            User user = userService.getUserByEmail(email);
            session.setAttribute("user", user);

            return "redirect:/homePage";
        } else {
            model.addAttribute("errorMessage", message);
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/signUp")
    public String signUpPage(Model model) {

        model.addAttribute("newUser", new UserDTO());
        return "signUp";
    }

    @PostMapping("/signUp")
    public String userRegistration(@ModelAttribute UserDTO userDTo) {

        User user = userService.signUp(userDTo);

        if(user != null) {
            return "redirect:/login";
        } else {
            return "redirect:/signUp";
        }

    }

    @GetMapping(value = "/task/{status}")
    public String AllTaskByStatusPage(@PathVariable(name = "status") String status, Model model) {

        List<Task> taskList = userService.viewTasksByStatus(status);
        model.addAttribute("allTasksByStatus", taskList);
        return "redirect:/AllTaskByStatus";
    }

    @PostMapping("/deleteTask{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        userService.deleteTask(id);

        return "redirect:/homePage";
    }


    @GetMapping(value = "/editPage/{id}")
    public String editPage(@PathVariable(name = "id") int id, Model model) {

        Task task = userService.getTaskById(id);
        model.addAttribute("singleTask", task);
        model.addAttribute("taskBody", new TaskDTO());
        return "redirect:/editPage";
    }

    @PostMapping("/editTask{id}")
    public String editTask(@PathVariable(name = "id") int id, @ModelAttribute TaskDTO taskDto) {

        userService.editTask(taskDto, id);

        return "redirect:/editPage";
    }

    @GetMapping(value = "/taskCreation")
    public String creatTaskPage(Model model) {

        model.addAttribute("newTask", new TaskDTO());
        return "createTask";
    }

    @PostMapping("/createTask")
    public String creatTask(@ModelAttribute TaskDTO taskDTO) {

        Task task = userService.createTask(taskDTO);

        if (task != null) {
            return "redirect:/homePage";
        } else {
            return "redirect:/createTask";
        }

    }








}
