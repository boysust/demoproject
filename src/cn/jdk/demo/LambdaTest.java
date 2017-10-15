package cn.jdk.demo;
//接口只有一个抽象方法
interface IMessage{   //函数式接口
	//public void print();
	public String print();
}
public class LambdaTest {

	public static void main(String[] args) {
//       IMessage msg = ()->System.out.println("www.sust.edu.cn");
//       msg.print();
		IMessage msg = ()->{
			String str = "www.sust.edu.cn";
			return str;
		};
		System.out.println(msg.print());
	}

}
