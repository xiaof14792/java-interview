package k;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExcutorTest {
	//ScheduledExecutorService 接口具有为预定执行或重复执行任务而设计的方法
	public static void main(String[] args) {
		ScheduledExecutorService scheduledPool = Executors.newSingleThreadScheduledExecutor();
		
		
		
		Runnable r = () ->{
			System.out.println("我的家在湖北红安县");
		};
		
		//只要丢进ThreadPool，执行逻辑就立马执行
		scheduledPool.scheduleAtFixedRate(r, 1000, 1000, TimeUnit.MILLISECONDS);
	}
}
