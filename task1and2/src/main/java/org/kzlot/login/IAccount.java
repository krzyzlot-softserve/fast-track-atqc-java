package org.kzlot.login;

/**
 * IAccount interface represents an account with methods to manage login status and password verification.
 */

public interface IAccount {
    /**
     * Sets the logged-in status of the account.
     *
     * @param value true if the account is logged in, false otherwise.
     */
    void setLoggedIn(boolean value);

    /**
     * Checks if the provided password matches the account's password.
     *
     * @param candidate the password to be checked.
     * @return true if the password matches, false otherwise.
     */
    boolean passwordMatches(String candidate);

    /**
     * Sets the revoked status of the account.
     *
     * @param value true if the account is revoked, false otherwise.
     */
    void setRevoked(boolean value);
}
