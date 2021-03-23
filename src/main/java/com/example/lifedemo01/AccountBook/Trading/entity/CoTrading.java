package com.example.lifedemo01.AccountBook.Trading.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (CoTrading)实体类
 *
 * @author makejava
 * @since 2021-02-07 17:01:38
 */
@Data
public class CoTrading implements Serializable {
    private static final long serialVersionUID = 373496816610876274L;
    /**
    * 交易编码
    */
    private String payNo;
    /**
    * 交易发起者编码
    */
    private String custNo;
    /**
    * 交易时间
    */
    private String payTime;
    /**
    * 收款方名称
    */
    private String payeeName;
    /**
    * 收款方机构
    */
    private String commodityName;
    /**
    * 交易金额
    */
    private String amount;
    /**
    * 收入/支出
    */
    private String io;
    /**
    * 支付状态
    */
    private String payStatus;
    /**
    * 消费类型
    */
    private String spendingKind;
    /**
    * 收入类型
    */
    private String incomeKind;
    /**
    * 是否有效
    */
    private String isEffect;
    /**
    * 交易标签
    */
    private String payLable;
    /**
    * 备注
    */
    private String note;

//
//    public String getPayNo() {
//        return payNo;
//    }
//
//    public void setPayNo(String payNo) {
//        this.payNo = payNo;
//    }
//
//    public String getCustNo() {
//        return custNo;
//    }
//
//    public void setCustNo(String custNo) {
//        this.custNo = custNo;
//    }
//
//    public String getPayTime() {
//        return payTime;
//    }
//
//    public void setPayTime(String payTime) {
//        this.payTime = payTime;
//    }
//
//    public String getPayeeName() {
//        return payeeName;
//    }
//
//    public void setPayeeName(String payeeName) {
//        this.payeeName = payeeName;
//    }
//
//    public String getCommodityName() {
//        return commodityName;
//    }
//
//    public void setCommodityName(String commodityName) {
//        this.commodityName = commodityName;
//    }
//
//    public String getAmount() {
//        return amount;
//    }
//
//    public void setAmount(String amount) {
//        this.amount = amount;
//    }
//
//    public String getIo() {
//        return io;
//    }
//
//    public void setIo(String io) {
//        this.io = io;
//    }
//
//    public String getPayStatus() {
//        return payStatus;
//    }
//
//    public void setPayStatus(String payStatus) {
//        this.payStatus = payStatus;
//    }
//
//    public String getSpendingKind() {
//        return spendingKind;
//    }
//
//    public void setSpendingKind(String spendingKind) {
//        this.spendingKind = spendingKind;
//    }
//
//    public String getIncomeKind() {
//        return incomeKind;
//    }
//
//    public void setIncomeKind(String incomeKind) {
//        this.incomeKind = incomeKind;
//    }
//
//    public String getIsEffect() {
//        return isEffect;
//    }
//
//    public void setIsEffect(String isEffect) {
//        this.isEffect = isEffect;
//    }
//
//    public String getPayLable() {
//        return payLable;
//    }
//
//    public void setPayLable(String payLable) {
//        this.payLable = payLable;
//    }
//
//    public String getNote() {
//        return note;
//    }
//
//    public void setNote(String note) {
//        this.note = note;
//    }

}