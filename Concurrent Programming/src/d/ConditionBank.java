package d;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用ReentrantLock 和 自定义的条件对象来编写同步程序
 * @author 14792
 *
 */
public class ConditionBank {
	//可重入的锁
	private Lock bankLock;
	//条件对象，账户余额大于转账数目
	private Condition sufficientFunds;
	
	private final double[] accounts;

	/**
	 * Constructs the bank.
	 * 
	 * @param n
	 *            the number of accounts
	 * @param initialBalance
	 *            the initial balance for each account
	 */
	public ConditionBank(int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
		
		//
		bankLock = new ReentrantLock();
		sufficientFunds = bankLock.newCondition(); 
	}

	/**
	 * Transfers money from one account to another.
	 * 
	 * @param from
	 *            the account to transfer from
	 * @param to
	 *            the account to transfer to
	 * @param amount
	 *            the amount to transfer
	 * @throws InterruptedException 
	 */
	public void transfer(int from, int to, double amount) throws InterruptedException {
		bankLock.lock();
		try {
			//余额不满足条件，继续等待
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
		
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
			//通知所有等待线程值得再次去检测条件
			sufficientFunds.signalAll();
		} finally {
			bankLock.unlock();
		}
	}

	/**
	 * Gets the sum of all account balances.
	 * 
	 * @return the total balance
	 */
	public double getTotalBalance() {
		double sum = 0;

		for (double a : accounts)
			sum += a;

		return sum;
	}

	/**
	 * Gets the number of accounts in the bank.
	 * 
	 * @return the number of accounts
	 */
	public int size() {
		return accounts.length;
	}
}
