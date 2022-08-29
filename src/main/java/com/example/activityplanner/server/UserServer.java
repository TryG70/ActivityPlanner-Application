package com.example.activityplanner.server;

import com.example.activityplanner.dto.TaskDTO;
import com.example.activityplanner.dto.UserDTO;
import com.example.activityplanner.model.Task;
import com.example.activityplanner.model.User;

import java.util.List;

public interface UserServer {

    User signUp(UserDTO userDTO);

    String login(String email, String password);

    Task createTask(TaskDTO taskDTO);

    List<Task> viewAllTasks();

    Task viewParticularTask(int id);

    List<Task> viewTasksByStatus(String status);

    Task editTask (TaskDTO taskDTO);

    boolean deleteTask(int id);


}
