package springplayground;

import java.util.List;

import springplayground.controller.MyController;
import springplayground.controller.MyView;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static springplayground.BeanDefinitions.getMyController;
import static springplayground.BeanDefinitions.getMyRepository;
import static springplayground.BeanDefinitions.getMyService;

/**
 * @author Moritz Halbritter
 */
class MainBeanFactory {
	public static void main(String[] args) {
		var beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("myRepository", getMyRepository());
		beanFactory.registerBeanDefinition("myService", getMyService());
		beanFactory.registerBeanDefinition("myController", getMyController());

		var controller = beanFactory.getBean(MyController.class);
		controller.add(1, "Apple");
		controller.add(2, "Banana");
		controller.add(3, "Clementine");

		List<MyView> views = controller.findAll();
		for (MyView view : views) {
			System.out.println(view);
		}
	}
}
