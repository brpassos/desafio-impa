package com.brunopassos.desafioimpa.model.repository;

import com.brunopassos.desafioimpa.model.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
