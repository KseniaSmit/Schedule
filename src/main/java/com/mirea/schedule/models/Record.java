package com.mirea.schedule.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "records")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "types_id")
    private int typeId;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "name")
    private String name;


}
