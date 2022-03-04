package springplayground;

import java.util.List;

import springplayground.controller.MyController;
import springplayground.controller.MyView;
import springplayground.repository.InMemoryRepository;
import springplayground.repository.MyRepository;
import springplayground.service.DefaultService;
import springplayground.service.MyService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Moritz Halbritter
 */
class MainAnnotationConfig {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);

		var controller = context.getBean(MyController.class);
		controller.add(1, "Apple");
		controller.add(2, "Banana");
		controller.add(3, "Clementine");

		List<MyView> views = controller.findAll();
		for (MyView view : views) {
			System.out.println(view);
		}
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
