package dao;

import bean.Transfer;

import java.util.List;

/**
 * @author huangFuJin
 * @date 2019/4/30 16:02
 */
public interface TransferDao {
    void insert(Transfer transfer);
    void delete(int tId);
    Transfer find(int tId);
    List<Transfer> findAll();
    void update(Transfer transfer);

    /**
     * 某个账户的转入金额
     * @param tId
     * @return
     */
    double in(int tId);

    /**
     * 某个账户所有的转入金额
     * @param tId
     * @return
     */
    double out(int tId);
}
