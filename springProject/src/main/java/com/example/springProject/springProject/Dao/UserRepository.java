package com.example.springProject.springProject.Dao;

import com.example.springProject.springProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {


}
