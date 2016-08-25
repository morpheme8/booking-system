package pl.morpheme.bookingsystem.domain;

import java.io.Serializable;

/**
 * Created by sylwek on 2016-05-24.
 */
public enum State implements Serializable {

    ACTIVE("Active"),
    INACTIVE("Inactive"),
    LOCKED("Locked");

    private String state;

    private State(final String state){
        this.state = state;
    }

    public String getState(){
        return this.state;
    }

    @Override
    public String toString(){
        return this.state;
    }
}