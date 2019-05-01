package service;

import bean.Account;
import bean.Transfer;
import dao.AccountDao;
import dao.TransferDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("account_service")
@Transactional(rollbackFor = Exception.class)
public class AccountService implements IAccountService{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransferDao transferDao;

    public Account open(String name, String password, String sex, String code, double openMoney) {
        Account account = new Account();
        account.setName(name);
        account.setPassWord(password);
        account.setSex(sex);
        account.setCode(code);
        account.setOpenMoney(openMoney);
        account.setBalance(openMoney);

        accountDao.insert(account);
        return account;
    }

    public boolean alterPasswd(int aId, String oldPasswd, String newPasswd1, String newPasswd2) {
        if (!newPasswd1.equals(newPasswd2)){
            return false;
        }
        Account originalAccount = accountDao.find(aId);
        if (oldPasswd == null || !oldPasswd.equals(originalAccount.getPassWord())){
            return false;
        }
        originalAccount.setPassWord(newPasswd1);
        accountDao.update(originalAccount);
        return true;
    }

    public TransferStatus transfer(int aidFrom, int aidTo, double transferMoney) {
        Account fromAccount = accountDao.find(aidFrom);
        Account toAccount = accountDao.find(aidTo);
        if (fromAccount.getBalance() < transferMoney){
            return TransferStatus.NOT_ENOUGH;
        }
        //更改减少余额
        fromAccount.setBalance(fromAccount.getBalance() - transferMoney);
        accountDao.update(fromAccount);
        //更改增加余额
        toAccount.setBalance(toAccount.getBalance() + transferMoney);
        accountDao.update(toAccount);

        //添加转出交易记录
        Transfer fromTransferRecord = new Transfer();
        fromTransferRecord.setaId(fromAccount.getaId());
        fromTransferRecord.setTransTime(new Date());
        fromTransferRecord.setType("转出");
        fromTransferRecord.setTransferMoney(transferMoney);
        transferDao.insert(fromTransferRecord);
        //添加转入交易记录
        Transfer toTransferRecord = new Transfer();
        toTransferRecord.setaId(toAccount.getaId());
        toTransferRecord.setTransferMoney(transferMoney);
        toTransferRecord.setType("转入");
        toTransferRecord.setTransTime(new Date());
        transferDao.insert(toTransferRecord);

        return TransferStatus.SUCESS;
    }

    public List<Transfer> getAllTransferByAid(int aid) {
        return transferDao.findAll();
    }

    public boolean checkAccount(int aid) {
        Account account = accountDao.find(aid);
        double in = transferDao.in(aid);
        double out = transferDao.out(aid);
        return account.getBalance() == account.getOpenMoney() + in - out;
    }
}
