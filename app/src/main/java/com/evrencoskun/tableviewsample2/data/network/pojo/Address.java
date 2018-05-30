package com.evrencoskun.tableviewsample2.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("line1")
    @Expose
    public String line1;
    @SerializedName("line2")
    @Expose
    public String line2;
    @SerializedName("zipcode")
    @Expose
    public String zipcode;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("fax")
    @Expose
    public String fax;

}

