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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping(value = "/homePage")
   public String dashboard(Model model, HttpSession session) {

        List<Task> taskList = userService.viewAllUserTasks((Integer) session.getAttribute("id"));
        model.addAttribute("allTasks", taskList);
        model.addAttribute("newTask", new TaskDTO());
        return "homePage";
   }

    @GetMapping(value = "/")
    public String index(Model model, HttpSession session) {
        return "index";
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
            session.setAttribute("id", user.getId());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("name", user.getName());


            return "redirect:/user/homePage";
        } else {
            model.addAttribute("errorMessage", message);
            return "redirect:/user/login";
        }
    }

    @GetMapping(value = "/signUp")
    public String signUpPage(Model model) {

        model.addAttribute("newUser", new UserDTO());
        return "signUp";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.invalidate();

        return "redirect:/";
    }

    @PostMapping("/signUp")
    public String userRegistration(@ModelAttribute UserDTO userDTo) {

        User user = userService.signUp(userDTo);

        if(user != null) {
            return "redirect:/user/login";
        } else {
            return "redirect:/user/signUp";
        }

    }

    @GetMapping(value = "/task/{status}")
    public String AllTaskByStatus(@PathVariable(name = "status") String status, Model model, HttpSession session) {
        int userId = (int) session.getAttribute("id");
        List<Task> taskList = userService.viewTasksByStatus(userId, status);
        model.addAttribute("allTasksByStatus", taskList);
        return "AllTaskByStatus";
    }

    @PostMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        userService.deleteTask(id);

        return "redirect:/user/homePage";
    }


    @GetMapping(value = "/editPage/{id}")
    public String editPage(@PathVariable(name = "id") int id, Model model) {

        Task task = userService.getTaskById(id);
        model.addAttribute("singleTask", task);
        model.addAttribute("taskBody", new TaskDTO());
        return "editPage";
    }

    @PostMapping("/editTask{id}")
    public String editTask(@PathVariable(name = "id") String id, @ModelAttribute TaskDTO taskDto) {
        int taskId = Integer.parseInt(id);
        userService.editTask(taskDto, taskId);

        return "redirect:/user/homePage";
    }

    @GetMapping(value = "/taskCreation")
    public String creatTaskPage(Model model) {

        model.addAttribute("newTask", new TaskDTO());
        return "homePage";
    }

    @PostMapping("/createTask")
    public String creatTask(@ModelAttribute TaskDTO taskDTO) {

        userService.createTask(taskDTO);

        return "redirect:/user/homePage";
    }

    @PostMapping(value = "/changeStatus/{id}")
    public String moveToInProgress(@PathVariable(name = "id")   String id, @RequestParam String status ){
        int taskId = Integer.parseInt(id);
        userService.updateTaskStatus(status, taskId);
        return "redirect:/user/homePage";
    }

    @PostMapping(value = "/complete/{id}")
    public String complete(@PathVariable(name = "id")   String id){
        int taskId = Integer.parseInt(id);
        userService.markTaskCompleted(taskId);
        return "redirect:/user/homePage";
    }

    @GetMapping(value = "/singleTask/{id}")
    public String getSingleTask(@PathVariable("id") String taskId, Model model) {
        Task task = userService.getTaskById(Integer.parseInt(taskId));
        model.addAttribute("task", task);

        return "singleTask";
    }








}
