package com.example.activityplanner.server;

import com.example.activityplanner.dto.TaskDTO;
import com.example.activityplanner.dto.UserDTO;
import com.example.activityplanner.model.Task;
import com.example.activityplanner.model.User;

import java.util.List;

public interface UserService {

    User signUp(UserDTO userDTO);

    User getUserByEmail(String email);

    String login(String email, String password);

    Task createTask(TaskDTO taskDTO);

    List<Task> viewAllTasks();

    List<Task> viewTasksByStatus(String status);

    Task editTask (TaskDTO taskDTO, int id);

    boolean deleteTask(int id);

    Task getTaskById (int id);

    boolean updateTaskStatus(String status, int id);


}
