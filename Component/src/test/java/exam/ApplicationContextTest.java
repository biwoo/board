package exam;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

public class ApplicationContextTest {
	
	@Test
	public void staticApplicationContext(){
		StaticApplicationContext ac = new StaticApplicationContext();
		
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
 		helloDef.getPropertyValues().addPropertyValue("name","String");
 		
		ac.registerSingleton("hello1", Hello.class);
		
		ac.registerBeanDefinition("hello2", helloDef);
		
		Hello hello1 = ac.getBean("hello1",Hello.class);
		Hello hello2 = ac.getBean("hello2",Hello.class);
		
		assertThat(hello1, is(notNullValue()));
		assertThat(hello2.sayHello(), is("Hello String"));
		
	}
	
	@Test
	public void genericApplicationContext(){
		GenericApplicationContext ac = new GenericApplicationContext();
		
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		reader.loadBeanDefinitions("/config/application-hello-context.xml");
		
		ac.refresh();
		
		Hello hello = ac.getBean("hello", Hello.class);
		assertThat(hello.sayHello(), is("Hello String"));
	}
	
	@Test
	public void genericXmlApplicationContext(){
		GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/config/application-hello-context.xml");
		
		Hello hello = ac.getBean("hello", Hello.class);
		assertThat(hello.sayHello(), is("Hello String"));
	}
	
	@Test
	public void annotaionConfigApplicationContext(){
		ApplicationContext ac = new AnnotationConfigApplicationContext(HelloConfig.class);
		Hello hello = ac.getBean("hello", Hello.class);
		assertThat(hello.sayHello(), is("Hello String"));
	}
}
