package service;

import bean.Account;
import bean.Transfer;

import java.util.List;

public interface IAccountService {
    /**
     *
     * @return
     */
    Account open(String name,String password,String sex,String code,
                 double openMoney);

    /**
     * 修改密码
     * @return
     */
    boolean alterPasswd(int aId,String oldPasswd,String newPasswd1,String newPasswd2);

    /**
     * 转账
     * @param aidFrom 从哪里来
     * @param aidTo 转账目的账户
     * @param transferMoney
     */
    TransferStatus transfer(int aidFrom,int aidTo,double transferMoney);

    /**
     * 获得该账户哦所有的交易记录
     * @param aid
     * @return
     */
    List<Transfer> getAllTransferByAid(int aid);

    /**
     * 对账
     * @param aid
     * @return
     */
    boolean checkAccount(int aid);


    /**  oracle account和transfer 的自增触发器和序列
     *
     create sequence ACCOUNT_INSERT_ID_INCREMENT
     minvalue 1
     start with 1
     nocache;

     create or replace trigger account_id_trigger
     before insert on account
     for each row
     when (new.aid is null)
     declare
     a int;
     begin
     a:=ACCOUNT_INSERT_ID_INCREMENT.NEXTVAL;
     select a into :new.aid from sys.dual;
     end account_id_trigger;

     create sequence TRANSFER_INSERT_ID_INCREMENT
     minvalue 1
     start with 1
     nocache;

     create or replace trigger TRANSFER_id_trigger
     before insert on TRANSFER
     for each row
     when (new.Tid is null)
     declare
     a int;
     begin
     a:=TRANSFER_INSERT_ID_INCREMENT.NEXTVAL;
     select a into :new.Tid from sys.dual;
     end account_id_trigger;

     */
}
