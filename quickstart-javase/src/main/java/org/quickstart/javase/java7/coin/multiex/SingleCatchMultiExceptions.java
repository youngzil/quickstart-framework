package org.quickstart.javase.java7.coin.multiex;

public class SingleCatchMultiExceptions {
    public void newMultiCatch() {
        try {
            methodThatThrowsThreeExceptions();
        } catch (ExceptionOne | ExceptionTwo | ExceptionThree e) {
            // log and deal with all Exceptions
        }

    }

    public void newMultiMultiCatch() {
        try {
            methodThatThrowsThreeExceptions();
        } catch (ExceptionOne e) {
            // log and deal with ExceptionOne

        } catch (ExceptionTwo | ExceptionThree e) {
            // log and deal with ExceptionTwo and ExceptionThree
        }

    }

    public void oldMultiCatch() {
        try {
            methodThatThrowsThreeExceptions();
        } catch (ExceptionOne e) {
            // log and deal with ExceptionOne
        } catch (ExceptionTwo e) {
            // log and deal with ExceptionTwo
        } catch (ExceptionThree e) {
            // log and deal with ExceptionTwo
        }
    }

    public void methodThatThrowsThreeExceptions() throws ExceptionOne, ExceptionTwo, ExceptionThree {

    }

    public static void main(String[] args) {

    }
}
