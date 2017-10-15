package cn.jdk.demo;
/**
 * sleep进行短暂的休眠，时间一到，自动唤醒
 * wait,java.lang.Object中的方法，必须通过notify或者notifyAll唤醒，为其追加一个超时时间
 * @author JDK
 *
 */
class MyMath{
	private int number = 0;
	private boolean flag  = true;
	public synchronized void add() {
		while(this.flag == false){
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("【add-"+Thread.currentThread().getName()+"num】="+this.number++);
		this.flag = false;
		super.notifyAll();
	}
	public synchronized void sub(){
		while(this.flag == true){
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try{
			Thread.sleep(200);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("【sub-"+Thread.currentThread().getName()+"num】="+this.number --);
		this.flag = true;
		super.notifyAll();
	}
	
}
public class ThreadMarkTest {

	public static void main(String[] args) {
        MyMath my = new MyMath();
        for(int x = 0;x<2;x++){
        	new Thread(()->{
        		for(int y = 0;y<100;y++){
        			my.add();
        		}
        	},"加法线程").start();
        }
        for(int x = 0;x<2;x++){
        	new Thread(()->{
        		for(int y = 0;y<100;y++){
        			my.sub();
        		}
        	},"减法线程").start();
        }
	}

}
