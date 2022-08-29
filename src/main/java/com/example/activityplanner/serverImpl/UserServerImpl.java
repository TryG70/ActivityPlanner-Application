package com.example.activityplanner.serverImpl;

import com.example.activityplanner.dto.TaskDTO;
import com.example.activityplanner.dto.UserDTO;
import com.example.activityplanner.model.Task;
import com.example.activityplanner.model.User;
import com.example.activityplanner.repository.TaskRepository;
import com.example.activityplanner.repository.UserRepository;
import com.example.activityplanner.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImpl implements UserServer {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Autowired
    public UserServerImpl(UserRepository userRepository, TaskRepository taskRepository) {
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

        User user = userRepository.findUserByEmail(email);

        if (user.getPassword().equals(password)) {

        }

    }

    Task createTask(TaskDTO taskDTO);

    List<Task> viewAllTasks();

    Task viewParticularTask(int id);

    List<Task> viewTasksByStatus(String status);

    Task editTask (TaskDTO taskDTO);

    boolean deleteTask(int id);
}
