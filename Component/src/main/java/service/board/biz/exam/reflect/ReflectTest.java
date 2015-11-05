package service.board.biz.exam.reflect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;

import org.junit.Test;

public class ReflectTest {
	@Test
	public void reflect() throws Exception{
		String name = "Spring";
		
		assertThat(name.length(), is(6));
		
		Method method = String.class.getMethod("length");
		
		assertThat(method.invoke(name), is(6));
				
		method = String.class.getMethod("replaceAll", String.class, String.class);
		
		assertThat(method.invoke(name,"S",""), is("pring"));
		
		Hello hello = new HelloImpl();
	
		assertThat(hello.sayHello("tobe"), is("tobe Hello"));
		
		hello = new HelloUppercase(hello);
		
		assertThat(hello.sayHello("tobe"), is("TOBE HELLO"));
	}
}
