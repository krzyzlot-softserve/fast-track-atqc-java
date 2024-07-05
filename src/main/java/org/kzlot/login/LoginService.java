package org.kzlot.login;
/**
 * LoginService class provides login functionality, including password verification and account management.
 */

public class LoginService {
    private final IAccountRepository accountRepository;
    private int failedAttempts = 0;

    /**
     * Constructs a new LoginService with the specified account repository.
     *
     * @param accountRepository the repository to retrieve accounts from.
     */
    LoginService(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        System.out.println("Instantiated the repository: " + accountRepository);

    }

    /**
     * Attempts to log in with the specified account ID and password.
     * If the login is successful, the account's logged-in status is set to true.
     * If the login fails, the number of failed attempts is incremented.
     * If there are three failed attempts, the account is revoked.
     *
     * @param accountId the ID of the account to be logged in.
     * @param password the password to be checked.
     */
    void login(String accountId, String password) {
        System.out.println("Login started: User: " + accountId + ", password: " + password);

        IAccount account = accountRepository.find(accountId);
        System.out.println("Got account from repo: " + account);

        if (account.passwordMatches(password)) {
            account.setLoggedIn(true);
            System.out.println("Login success");
        } else {
            ++failedAttempts;
            System.out.println(failedAttempts + " failed attempts");
        }

        if (failedAttempts == 3) {
            account.setRevoked(true);
            System.out.println("Account revoked");
        }
    }
}



