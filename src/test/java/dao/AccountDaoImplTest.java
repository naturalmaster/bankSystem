package dao;

import bean.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author huangFuJin
 * @date 2019/4/30 16:33
 * mail: naaturalmaster@gmail.com
 */
public class AccountDaoImplTest {
    AccountDao accountDao;
    Account accountForInsert;
    @Before
    public void setup(){
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        accountDao = (AccountDao) applicationContext.getBean("account_dao");
        accountForInsert = new Account();
        accountForInsert.setSex("男");
        accountForInsert.setPassWord("123456");
        accountForInsert.setName("小黄");
        accountForInsert.setCode("441602199605280888");
        accountForInsert.setaId(888);


    }

    @Test
    public void accountDaoTest(){
        Account findAccount = accountDao.find(567);
        String expectedName = "刘明";
        Assert.assertEquals(expectedName,findAccount.getName());

        accountDao.insert(accountForInsert);
        findAccount = accountDao.find(accountForInsert.getaId());
        Assert.assertNotNull(findAccount);
        int size = accountDao.findAll().size();

        int expectedSizeAfterDel = size - 1;
        accountDao.delete(accountForInsert.getaId());
        Assert.assertEquals(expectedSizeAfterDel,accountDao.findAll().size());

    }
}
