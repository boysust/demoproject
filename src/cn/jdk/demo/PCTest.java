package cn.jdk.demo;
class Message{
	private String title;
	private String note;
	public boolean flag = true;
	public synchronized void set(String title,String note){
//		this.title = title;
//	    try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	    this.note = note;
		if(this.flag==true){
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.title = title;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.note = note;
		this.flag = false;   //生产过了
		super.notify();  //唤醒其他线程
	}
	public synchronized void get(){
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		if(this.flag==false){  //生产过了
			try {
				super.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("【"+Thread.currentThread().getName()+"】title:"+this.title+",note；"+this.note);
		this.flag = true;
		super.notify();
	}
}
public class PCTest {

	public static void main(String[] args) {
       Message msg = new Message();
       new Thread(()->{
    	  for(int x =  0;x<10;x++){
    		  if(x%2==0){
    			  msg.set("东方不败", "陈乔恩");
    		  }else{
    			  msg.set("大唐荣耀", "景甜");
    		  }
    	  } 
       },"生产者x").start();
       new Thread(()->{
    	  for(int x = 0;x<100;x++){
    		  msg.get();
    	  } 
       },"消费者").start();
	}
}
