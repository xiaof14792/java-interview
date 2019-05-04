package o;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/**
 * 交换器-Exchanger
 * 两个线程到达同步点后，相互交换数据
 * @author 14792
 *
 */
public class ExchangerDemo {
	
	public static void main(String[] args) {
		Exchanger<String> exchanger = new Exchanger<>();
		//代表男生和女生
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		service.execute(() -> {
			try {
				//男生对女生说的话
				String girl = exchanger.exchange("我其实暗恋你很久了");
				System.out.println("女生说：" + girl);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		});
		
		service.execute(() -> {
			try {
				System.out.println("女生慢慢从教室里走出来...");
				TimeUnit.SECONDS.sleep(3);
				
				//男生对女生说的话
				String boy = exchanger.exchange("我很喜欢你...");
				System.out.println("男生说：" + boy);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		});
	}
}
