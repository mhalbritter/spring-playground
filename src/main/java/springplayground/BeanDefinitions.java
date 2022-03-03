package springplayground;

import springplayground.controller.MyController;
import springplayground.repository.InMemoryRepository;
import springplayground.service.DefaultService;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author Moritz Halbritter
 */
class BeanDefinitions {
	public static BeanDefinition getMyController() {
		var beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(MyController.class);
		beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		return beanDefinition;
	}

	public static BeanDefinition getMyService() {
		var beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(DefaultService.class);
		beanDefinition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		return beanDefinition;
	}

	public static BeanDefinition getMyRepository() {
		var beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(InMemoryRepository.class);
		return beanDefinition;
	}
}
