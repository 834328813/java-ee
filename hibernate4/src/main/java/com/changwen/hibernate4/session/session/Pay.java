package com.changwen.hibernate4.session.session;

/**
 * Pay
 *
 * @author lcw 2015/12/23
 */
public class Pay {

    private int monthlyPay;
    private int yearPay;
    private int vocationWithPay;

    private Worker worker;



    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }
    public int getMonthlyPay() {
        return monthlyPay;
    }
    public void setMonthlyPay(int monthlyPay) {
        this.monthlyPay = monthlyPay;
    }
    public int getYearPay() {
        return yearPay;
    }
    public void setYearPay(int yearPay) {
        this.yearPay = yearPay;
    }
    public int getVocationWithPay() {
        return vocationWithPay;
    }
    public void setVocationWithPay(int vocationWithPay) {
        this.vocationWithPay = vocationWithPay;
    }



}

