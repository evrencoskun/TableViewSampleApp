package com.evrencoskun.tableviewsample2.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("nickname")
    @Expose
    public String nickname;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("birthdate")
    @Expose
    public String birthdate;
    @SerializedName("gender")
    @Expose
    public String gender;
    @SerializedName("salary")
    @Expose
    public String salary;
    @SerializedName("group_id")
    @Expose
    public Integer groupId;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("age")
    @Expose
    public Integer age;
    @SerializedName("group")
    @Expose
    public Group group;
    @SerializedName("address")
    @Expose
    public Address address;


}
