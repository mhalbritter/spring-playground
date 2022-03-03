package springplayground;

import java.util.List;

import springplayground.controller.MyController;
import springplayground.controller.MyView;

import org.springframework.context.support.GenericApplicationContext;

import static springplayground.BeanDefinitions.getMyController;
import static springplayground.BeanDefinitions.getMyRepository;
import static springplayground.BeanDefinitions.getMyService;

/**
 * @author Moritz Halbritter
 */
class MainGenericApplicationContext {
	public static void main(String[] args) {
		var context = new GenericApplicationContext();
		context.registerBeanDefinition("myRepository", getMyRepository());
		context.registerBeanDefinition("myService", getMyService());
		context.registerBeanDefinition("myController", getMyController());
		context.refresh();

		var controller = context.getBean(MyController.class);
		controller.add(1, "Apple");
		controller.add(2, "Banana");
		controller.add(3, "Clementine");

		List<MyView> views = controller.findAll();
		for (MyView view : views) {
			System.out.println(view);
		}
	}
}
