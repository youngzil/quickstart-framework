package org.quickstart.javase.java7.coin.switchstmt;

import org.quickstart.javase.java7.domain.Trade;

public class NewSwitch {

    private static final String PENDING = "PENDING";
    private static final String EXECUTE = "EXECUTE";
    private static final String NEW = "NEW";

    public void processTrade(Trade t) {
        String status = t.getStatus();

        switch (status) {
            case NEW:
                newTrade(t);
                break;
            case EXECUTE:
                executeTrade(t);
                break;
            case PENDING:
                pendingTrade(t);
                break;

            default:
                break;
        }
    }

    private void pendingTrade(Trade t) {
        // impl goes here
    }

    private void executeTrade(Trade t) {
        // impl goes here
    }

    private void newTrade(Trade t) {
        // impl goes here
    }

    public static void main(String[] args) {

    }

}
