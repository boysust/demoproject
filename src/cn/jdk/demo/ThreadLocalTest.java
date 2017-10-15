package cn.jdk.demo;
class MessageSend{
	public static void send(){
		MessageA temp = MessageSpace.getMessageA();
		System.out.println("【"+Thread.currentThread().getName()+"】消息发送-"+temp.getMsg());
	}
}
class MessageA{
	private String msg = "www.mldn.cn";
	public  void setMsg(String msg){
		this.msg = msg;
	}
	public  String getMsg(){
		return msg;
	}
}
class MessageSpace{
	private static ThreadLocal<MessageA> threadLocal = new ThreadLocal<MessageA>();
	public static void setMessage(MessageA t){
		threadLocal.set(t);
	}	
	public static  MessageA getMessageA(){
		return threadLocal.get();
	} 
}
public class ThreadLocalTest {
   public static void main(String[] args) {
	  new Thread(()->{
		  MessageA msg = new MessageA();
		  msg.setMsg("AAAAA");
		  MessageSpace.setMessage(msg);
		  MessageSend.send();
	  },"发送者A").start();
	  new Thread(()->{
		  MessageA msg = new MessageA();
		  msg.setMsg("BBBBB");
		  MessageSpace.setMessage(msg);
		  MessageSend.send();
	  },"发送者 B").start();
	  new Thread(()->{
		  MessageA msg = new MessageA();
		  msg.setMsg("CCCCC");
		  MessageSpace.setMessage(msg);
		  MessageSend.send();
	  },"发送者 C").start();
   }
}
