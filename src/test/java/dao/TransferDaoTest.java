package dao;

import bean.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class TransferDaoTest {
    TransferDao transferDao;
    Transfer transferToInsert;
    @Before
    public void setup(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        transferDao = (TransferDao) applicationContext.getBean("transfer_dao");
        transferToInsert = new Transfer();
        transferToInsert.setType("转入");
        transferToInsert.setTransTime(new Date());
        transferToInsert.setTransferMoney(5625);
        transferToInsert.settId(123);
        transferToInsert.setaId(567);
    }

    @Test
    public void accountDaoTest(){
        Transfer findTransfer = transferDao.find(3360);
        double expectedMoney = 100;
        Assert.assertEquals(expectedMoney,findTransfer.getTransferMoney(),0.5   );

        transferDao.insert(transferToInsert);
        findTransfer = transferDao.find(transferToInsert.gettId());
        Assert.assertNotNull(findTransfer);
        int size = transferDao.findAll().size();

        int expectedSizeAfterDel = size - 1;
        transferDao.delete(transferToInsert.gettId());
        Assert.assertEquals(expectedSizeAfterDel,transferDao.findAll().size());

    }

}
