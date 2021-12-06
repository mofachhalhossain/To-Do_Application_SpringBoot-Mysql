package com.example.springProject.springProject.Dao;


import com.example.springProject.springProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    /*JPA Query*/
    @Query("select u from User u where u.email = :email")   //u.email property of user, email parameter
    /*method will return useremail as username*/
    /*Dynamic value emplementation*/
    public User getUserByUserName(@Param("email") String email);


}
