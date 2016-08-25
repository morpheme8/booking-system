package pl.morpheme.bookingsystem.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sylwek on 2016-05-24.
 */
@Entity
@Table(name = "PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable {

    //private static final long serialVersionUID = 365846515458546655L;

    @Id
    private String series;

    @Column(name="USERNAME", unique=false, nullable=false)
    private String username;

    @Column(name="TOKEN", unique=false, nullable=false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date last_used;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLast_used() {
        return last_used;
    }

    public void setLast_used(Date last_used) {
        this.last_used = last_used;
    }


}

