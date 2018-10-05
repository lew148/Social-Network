package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

/**
 * A user of the social network.
 */
public class User {

    /**
     * The user name uniquely identifies a user
     */

    private String username;
    private String fullname;
    private String password;

    public User() {}

    public User(String username, String fullname, String password) {
            this.setUsername(username);
            this.setFullname(fullname);
            this.setPassword(password);
    }

    public User(String name) {
        this.username = name;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String name) {
        this.username = name;
    }

    public String getFullname() {return fullname;}
    public void setFullname(String fullname) {this.fullname = fullname;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}


    /**
     * Below methods were automatically generated using IntelliJ and Guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .toString();
    }

}
