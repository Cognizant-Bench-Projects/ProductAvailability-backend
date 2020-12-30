package com.cognizant.model;

import java.util.List;

public class BalanceList {

    private List<Balance> balances;
    private int count;

    public BalanceList() {
    }

    public BalanceList(List<Balance> balances, int count) {
        this.balances = balances;
        this.count = count;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
