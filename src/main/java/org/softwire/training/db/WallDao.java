package org.softwire.training.db;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The Wall DAO (Data Access Object) provides an interface for interacting with Social Events in the database.
 *
 * In particular, users of this class don't need to know any details about the database itself.
 */
public class WallDao {

    private final Jdbi jdbi;

    public WallDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<SocialEvent> readWall(User user) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT author AS username, content, id FROM social_events WHERE user = :user")
                    .bind("user", user.getUsername())
                    .mapToBean(SocialEvent.class)
                    .list();
        }
    }

    public List<User> getAllUsers() {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT * FROM users")
                    .mapToBean(User.class)
                    .list();
        }
    }


    public void writeOnWall(User user, SocialEvent socialEvent) {
        try (Handle handle = jdbi.open()) {
            handle.createCall("INSERT INTO social_events (user, author, content) VALUES (:user, :author, :content)")
                    .bind("author", socialEvent.getAuthor().getUsername())
                    .bind("user", user.getUsername())
                    .bind("content", socialEvent.getContent())
                    .invoke();
        }
    }

    public SocialEvent getSocialEventById(int id) {
        List<SocialEvent> socialEvent;
        try (Handle handle = jdbi.open()) {
            socialEvent = handle.createQuery("SELECT * FROM social_events WHERE id = " + id)
                    .mapToBean(SocialEvent.class).list();
        }
        return socialEvent.get(0);
    }


    public void deletePost(int id) {


        try (Handle handle = jdbi.open()) {
            handle.createUpdate("DELETE FROM social_events WHERE id = " + id)
                    .execute();
        }
    }
}
