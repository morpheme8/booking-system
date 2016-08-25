package pl.morpheme.bookingsystem.domain;

import java.io.Serializable;

/**
 * Created by sylwek on 2016-05-25.
 */
public enum UserProfileType implements Serializable {

    USER("user"),
    DBA("dba"),
    ADMIN("admin");

    private String userProfileType;

    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }

}