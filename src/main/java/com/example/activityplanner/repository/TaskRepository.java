package com.example.activityplanner.repository;

import com.example.activityplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query(value = "SELECT * FROM task WHERE user_id=?1 AND status=?2", nativeQuery = true)
    List<Task> getAllTasksByStatus(int userId, String status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET status = ?1 where  id = ?2" , nativeQuery = true)
    int updateTaskByIdAndStatus(String status , int id);

    @Query(value = "SELECT * FROM task WHERE user_id = ?1" , nativeQuery = true)
    List<Task> findAllByUser_id(int user_id);
}
