package com.example.activityplanner.serverImpl;

import com.example.activityplanner.dto.TaskDTO;
import com.example.activityplanner.dto.UserDTO;
import com.example.activityplanner.exception.TaskNotFoundException;
import com.example.activityplanner.exception.UserNotFoundException;
import com.example.activityplanner.model.Task;
import com.example.activityplanner.model.User;
import com.example.activityplanner.repository.TaskRepository;
import com.example.activityplanner.repository.UserRepository;
import com.example.activityplanner.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    public User signUp(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return userRepository.save(user);
    }

    public String login(String email, String password) {

        String message = "incorrect password";
        User user = getUserByEmail(email);

        if(user.getPassword().equals(password)){
            message = "successful";
        }

        return message;
    }


    public Task createTask(TaskDTO taskDTO) {

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        return taskRepository.save(task);

    }


    public List<Task> viewAllTasks() {
        return taskRepository.findAll();
    }


    public List<Task> viewTasksByStatus(String status) {
        return taskRepository.getAllTasksByStatus(status);
    }


    public Task editTask (TaskDTO taskDTO, int id) {

        Task task = getTaskById(id);
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());

        return taskRepository.save(task);
    }


    public boolean deleteTask(int id) {
        taskRepository.deleteById(id);
        return true;
    }

    public User getUserByEmail (String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public Task getTaskById (int id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
}
