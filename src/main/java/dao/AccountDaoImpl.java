package dao;

import bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author huangFuJin
 * @date 2019/4/30 16:03
 */

@Repository("account_dao")
public class AccountDaoImpl implements AccountDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void insert(Account account) {
        String sql = "insert into ACCOUNT values(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,account.getaId(),account.getName(),account.getPassWord(),
                account.getSex(),account.getCode(),account.getOpenMoney(),account.getOpenDate(),
                account.getBalance());
    }

    public void delete(int aId) {
        String sql = "delete from account where aid = ?";
        jdbcTemplate.update(sql,aId);
    }

    public Account find(int aId) {
        String sql = "select * from ACCOUNT where aid=?";
        return jdbcTemplate.queryForObject(sql,accountRowMapper,aId);
    }

    public List<Account> findAll() {
        String sql = "select * from ACCOUNT";
        return jdbcTemplate.query(sql,accountRowMapper);
    }

    public void update(Account account) {
        String sql = "update ACCOUNT set Name=?,password=?," +
                "sex=?,code=?,open_money=?,open_time=?,balance=? " +
                "where aid=?";
        jdbcTemplate.update(sql,account.getName(),account.getPassWord(),
                account.getSex(),account.getCode(),account.getOpenMoney(),
                account.getOpenDate(),account.getBalance(),account.getaId());
    }

    private RowMapper<Account> accountRowMapper = new RowMapper<Account>() {
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setaId(rs.getInt("aid"));
            account.setBalance(rs.getDouble("balance"));
            account.setCode(rs.getString("code"));
            account.setName(rs.getString("name"));
            account.setOpenDate(rs.getDate("open_time"));
            account.setOpenMoney(rs.getDouble("open_money"));
            account.setPassWord(rs.getString("password"));
            account.setSex(rs.getString("sex"));
            return account;
        }
    };
}
