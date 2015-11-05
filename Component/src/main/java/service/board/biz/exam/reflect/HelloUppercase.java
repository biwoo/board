package service.board.biz.exam.reflect;

public class HelloUppercase implements Hello{
	
	private Hello hello;
	
	public HelloUppercase(Hello hello){
		this.hello = hello;
	}
	public String sayHello(String name){
		return hello.sayHello(name).toUpperCase();
	}
}
