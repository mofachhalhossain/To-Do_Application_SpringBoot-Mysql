package com.example.springProject.springProject.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity                                               //0.0.006->defines entity
@Table(name = "Todo")                                   //0.0.007->for changing tables in database

/**/
/*This Method will define to-do properties. Added Getter Setter &
/**/

public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     //0.0.008->for changing tables in database
    private int id;
    private String time;
    private String work;
    @Column(length = 180)                            //0.0.009->for changing field
    private String description;
    private String imageUrl;

    public ToDo() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /*ManyToOne mapping for user's to do functionality*/
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*@Override
    public String toString() {
        return "ToDo{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", work='" + work + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", user=" + user +
                '}';
    }*/

    @Override
    public boolean equals(Object obj) {
        return this.id == ((ToDo) obj).getId();
    }

}
