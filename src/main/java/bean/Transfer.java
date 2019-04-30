package bean;

import java.util.Date;

public class Transfer {
    private int tId;

    /**
     * 账户Id，外键链接Account的aId
     */
    private int aId;

    /**
     * 转账类型，可以有以下四种
     * 转入，转出，存入，取出
     */
    private String type;
    private Date transTime;
    private int transferMoney;


}
