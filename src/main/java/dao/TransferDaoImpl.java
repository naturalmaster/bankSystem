package dao;

import bean.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("transfer_dao")
public class TransferDaoImpl implements TransferDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insert(Transfer transfer) {
        String sql = "insert into TRANSFER values(?,?,?,?,?)";
        jdbcTemplate.update(sql,transfer.gettId(),transfer.getaId(),transfer.getType(),
                transfer.getTransTime(),transfer.getTransferMoney());

    }

    public void delete(int tId) {
        String sql = "delete from TRANSFER where tid = ?";
        jdbcTemplate.update(sql,tId);
    }

    public Transfer find(int tId) {
        String sql = "select * from transfer where tid=?";
        return jdbcTemplate.queryForObject(sql,transferRowMapper,tId);
    }

    public List<Transfer> findAll() {
        String sql = "select * from TRANSFER";
        return jdbcTemplate.query(sql,transferRowMapper);
    }

    public void update(Transfer transfer) {
        String sql = "update TRANSFER set a_id=?,type=?,trans_time=?," +
                "trans_money=? where tid=?";
        jdbcTemplate.update(sql,transfer.getaId(),transfer.getType(),
                transfer.getTransTime(),transfer.getTransferMoney(),transfer.gettId());
    }


    public double in(int aId) {
        String inSql = "select nvl(sum(trans_money),0) from TRANSFER where" +
                "aId = ? and type in ('转入','存入')";
        return jdbcTemplate.queryForObject(inSql,Double.class,aId);
    }

    public double out(int aId) {
        String inSql = "select nvl(sum(trans_money),0) from TRANSFER where" +
                "aId = ? and type in ('转出','取出')";
        return jdbcTemplate.queryForObject(inSql,Double.class,aId);
    }

    private RowMapper<Transfer> transferRowMapper = new RowMapper<Transfer>() {
        public Transfer mapRow(ResultSet resultSet, int i) throws SQLException {
            Transfer transfer = new Transfer();
            transfer.settId(resultSet.getInt("tid"));
            transfer.setaId(resultSet.getInt("a_id"));
            transfer.setTransferMoney(resultSet.getDouble("trans_money"));
            transfer.setTransTime(resultSet.getDate("trans_time"));
            transfer.setType(resultSet.getString("type"));
            return transfer;
        }
    };
}
