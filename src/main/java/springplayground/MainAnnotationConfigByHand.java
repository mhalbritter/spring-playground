package springplayground;

import java.util.List;

import springplayground.controller.MyController;
import springplayground.controller.MyView;
import springplayground.repository.InMemoryRepository;
import springplayground.repository.MyRepository;
import springplayground.service.DefaultService;
import springplayground.service.MyService;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

/**
 * @author Moritz Halbritter
 */
class MainAnnotationConfigByHand {
	public static void main(String[] args) {
		var beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("myConfiguration", getConfiguration());
		var configurationClassPostProcessor = new ConfigurationClassPostProcessor();
		configurationClassPostProcessor.postProcessBeanDefinitionRegistry(beanFactory);

		var controller = beanFactory.getBean(MyController.class);
		controller.add(1, "Apple");
		controller.add(2, "Banana");
		controller.add(3, "Clementine");

		List<MyView> views = controller.findAll();
		for (MyView view : views) {
			System.out.println(view);
		}
	}

	private static BeanDefinition getConfiguration() {
		var beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(MyConfiguration.class);
		return beanDefinition;
	}

	@Configuration(proxyBeanMethods = false)
	private static class MyConfiguration {
		@Bean
		static MyController myController(MyService myService) {
			return new MyController(myService);
		}

		@Bean
		MyService myService(MyRepository myRepository) {
			return new DefaultService(myRepository);
		}

		@Bean
		MyRepository myRepository() {
			return new InMemoryRepository();
		}
	}
}
