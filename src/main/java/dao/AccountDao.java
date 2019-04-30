package dao;

import bean.Account;

import java.util.List;

/**
 * @author huangFuJin
 * @Date 2019/4/30 16:00
 */
public interface AccountDao {
    void insert(Account account);
    void delete(int aId);
    Account find(int aId);
    List<Account> findAll();
    void update(Account account);


}
