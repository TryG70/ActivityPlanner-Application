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

    List<Task> viewAllUserTasks(Integer id);

    List<Task> viewTasksByStatus(int userId, String status);

    Task editTask (TaskDTO taskDTO, int id);

    void deleteTask(int id);

    Task getTaskById (int id);

    int updateTaskStatus(String status, int id);

    Task markTaskCompleted(int id);


}
