package org.kzlot.login;
/**
 * IAccountRepository interface represents a repository of accounts, allowing for account retrieval.
 */

public interface IAccountRepository {
    /**
     * Finds an account by its ID.
     *
     * @param accountId the ID of the account to be found.
     * @return the account associated with the given ID, or null if no such account exists.
     */
    IAccount find(String accountId);
}
