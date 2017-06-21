package exercise1.exercise298;

import java.util.concurrent.*;

public class ThreadCooperation {
    private static Account account = new Account();

    public static void main(String[] args) {
        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();

        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }

    // A task for adding an amount to the account
    public static class DepositTask implements Runnable {
        public void run() {
            try { // Purposely delay it to let the withdraw method proceed
                while (true) {
                    account.deposit((int) (Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    // A task for subtracting an amount from the account
    public static class WithdrawTask implements Runnable {
        public void run() {
            while (true) {
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        }
    }

    // An inner class for account
    private static class Account {

        // Create an Object
        private static final Object object = new Object();

        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void withdraw(int amount) {
            synchronized (object) {
                try {
                    while (balance < amount) {
                        System.out.println("\t\t\tWait for a deposit");
                        object.wait();
                    }

                    balance -= amount;
                    System.out.println("\t\t\tWithdraw " + amount + "\t\t" + getBalance());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public void deposit(int amount) {
            synchronized (object) {
                balance += amount;
                System.out.println("Deposit " + amount + "\t\t\t\t\t" + getBalance());

                // Notify thread waiting on the condition
                object.notifyAll();
            }
        }
    }
}