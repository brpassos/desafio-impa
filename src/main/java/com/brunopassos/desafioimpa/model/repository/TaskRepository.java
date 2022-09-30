package com.brunopassos.desafioimpa.model.repository;

import com.brunopassos.desafioimpa.model.domain.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {

    @Query("from Task t where t.user.id = :userid")
    List<Task> findAllByUser(Integer userid);

}
