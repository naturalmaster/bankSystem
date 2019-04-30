package dao;

import bean.Transfer;

import java.util.List;

/**
 * @author huangFuJin
 * @date 2019/4/30 16:02
 */
public interface TransferDao {
    void insert(Transfer account);
    void delete(int aId);
    Transfer find(int aId);
    List<Transfer> findAll();
    void update(Transfer account);
}
