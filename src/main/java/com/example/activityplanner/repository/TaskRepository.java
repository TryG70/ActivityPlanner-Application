package com.example.activityplanner.repository;

import com.example.activityplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM task WHERE status=?1", nativeQuery = true)
    List<Task> getAllTasksByStatus(String status);

    @Query(value = "UPDATE task SET status = ?1 where  id = ?2" , nativeQuery = true)
    boolean updateTaskByIdAndStatus(String status , int id);
}
