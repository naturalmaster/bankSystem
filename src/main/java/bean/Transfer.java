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
    private double transferMoney;


    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public double getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(double transferMoney) {
        this.transferMoney = transferMoney;
    }
}
