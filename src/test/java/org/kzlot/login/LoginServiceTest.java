package org.kzlot.login;

import org.testng.annotations.Test;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @Test
    public void testLoggedInAfterPasswordMatches() {

        //given
        IAccount account = mock(IAccount.class);
        given(account.passwordMatches("1234")).willReturn(true);
        IAccountRepository repository = mock(IAccountRepository.class);
        given(repository.find("1")).willReturn(account);

        //when
        LoginService loginService = new LoginService(repository);
        loginService.login("1", "1234");

        //then
        verify(account).setLoggedIn(true);
    }

    @Test
    public void testAccountRevokedAfterThreeFailedLoginAttempts() {

        //given
        IAccount account = mock(IAccount.class);
        given(account.passwordMatches(anyString())).willReturn(false);
        IAccountRepository repository = mock(IAccountRepository.class);
        given(repository.find("2")).willReturn(account);

        //when
        LoginService loginService = new LoginService(repository);
        loginService.login("2", anyString());
        loginService.login("2", anyString());
        loginService.login("2", anyString());

        //then
        verify(account).setRevoked(true);
    }

    @Test
    public void testNotLoggedInAfterPasswordNotMatches() {

        //given
        IAccount account = mock(IAccount.class);
        given(account.passwordMatches("4321")).willReturn(false);
        IAccountRepository repository = mock(IAccountRepository.class);
        given(repository.find("1")).willReturn(account);

        //when
        LoginService loginService = new LoginService(repository);
        loginService.login("1", "4321");

        //then
        verify(account, never()).setLoggedIn(true);
    }
}
