package service.board.biz.exam.dynamic;

import java.lang.reflect.InvocationHandler;



public class HelloUppercase implements Hello{
	
	InvocationHandler invocationHandler = new UpperCaseHandler(new HelloImpl());
	
	public String sayHello(String name){
		
		String ret = null;
		
		try {
			ret =  (String)invocationHandler.invoke(this, Hello.class.getMethod("sayHello"), new Object[]{name});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	public String sayHi(String name){
		
		String ret = null;
		
		try {
			ret =  (String)invocationHandler.invoke(this, Hello.class.getMethod("sayHi"), new Object[]{name});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
}
