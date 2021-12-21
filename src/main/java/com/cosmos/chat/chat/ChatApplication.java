package com.cosmos.chat.chat;

//import java.util.Date;

//import com.cosmos.chat.chat.authentication.domain.model.MyBean;

//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);

		/*GenericBeanDefinition gbd = new GenericBeanDefinition();
		gbd.setBeanClass(MyBean.class);  
		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("date", new Date());      //alternatively we can use:
		// gbd.getPropertyValues().addPropertyValue("date", new Date());
		gbd.setPropertyValues(mpv); 
		context.registerBeanDefinition("myBeanName", gbd);
		MyBean bean = context.getBean(MyBean.class);
		bean.doSomething();*/
	}

}