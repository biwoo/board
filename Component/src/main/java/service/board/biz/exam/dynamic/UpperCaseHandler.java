package service.board.biz.exam.dynamic;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;




public class UpperCaseHandler implements InvocationHandler{
	
	private Object target;
	
	public UpperCaseHandler(Object target){
		this.target = target;
	}
	
	public Object invoke(Object proxy,  Method method, Object[] args) throws Throwable{
		
		String ret = (String)method.invoke(target, args);
		
		if(method.getName().startsWith("sayHe")){
			return ret.toUpperCase();
		}else{
			return ret;
		}
	}
}
