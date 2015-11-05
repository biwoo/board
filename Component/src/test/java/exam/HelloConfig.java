package exam;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

	@Bean
	public Hello hello(){
		Hello hello = new Hello();
		hello.setName("String");
		hello.setPrinter(new StringPrinter());
		return hello;
	}
	
}
