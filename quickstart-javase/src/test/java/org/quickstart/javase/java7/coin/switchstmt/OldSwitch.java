package org.quickstart.javase.java7.coin.switchstmt;

import org.quickstart.javase.java7.domain.Trade;

public class OldSwitch {

    private void processTrade(Trade t) {
        String status = t.getStatus();

        if (status.equalsIgnoreCase("NEW")) {
            newTrade(t);
        } else if (status.equalsIgnoreCase("EXECUTED")) {
            executeTrade(t);
        } else if (status.equalsIgnoreCase("PENDING")) {
            pendingTrade(t);
        }
    }

    private void newTrade(Trade t) {
        // impl goes here
    }

    private void executeTrade(Trade t) {
        // impl goes here
    }

    private void pendingTrade(Trade t) {
        // impl goes here
    }

    public static void main(String[] args) {

    }
}
