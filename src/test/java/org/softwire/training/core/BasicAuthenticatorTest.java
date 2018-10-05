package org.softwire.training.core;

import io.dropwizard.auth.basic.BasicCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.User;
import org.softwire.training.models.UserPrincipal;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasicAuthenticatorTest {

    private BasicAuthenticator basicAuthenticator;

    UserDao mockUserDao = mock(UserDao.class);
    Utils utils = new Utils();

    @BeforeEach
    public void beforeEach() {
        basicAuthenticator = new BasicAuthenticator(mockUserDao);
    }

    @Test
    public void handleCorrectPassword() {
        String password = "password";
        String hashedPassword = utils.hashString("password");

        User fakeUser = new User("username","fake user",hashedPassword);
        when(mockUserDao.getUserByUsername(anyString())).thenReturn(fakeUser);

        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(fakeUser.getUsername(), password));

        assertThat(userPrincipal, equalTo(Optional.of(new UserPrincipal(new User(fakeUser.getUsername())))));
    }

    @Test
    public void handleIncorrectPassword() {
        String incorrectPassword = "notPassword";
        String hashedPassword = utils.hashString("password");

        User fakeUser = new User("username","fake user",hashedPassword);
        when(mockUserDao.getUserByUsername(anyString())).thenReturn(fakeUser);

        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(fakeUser.getUsername(), incorrectPassword));

        assertThat(userPrincipal, equalTo(Optional.empty()));
    }
}
