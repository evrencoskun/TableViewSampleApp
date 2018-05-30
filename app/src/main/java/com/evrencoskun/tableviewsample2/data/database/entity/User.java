package com.evrencoskun.tableviewsample2.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by evrencoskun on 1.12.2017.
 */
@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public int id;
    public String name;
    public String nickname;
    public String email;
    public String birthdate;
    public String gender;
    public String salary;
    public String job;
    public Date created_at;
    public Date updated_at;
    public int age;
    public String address;
    public String zipcode;
    public String mobile;
    public String fax;


}
