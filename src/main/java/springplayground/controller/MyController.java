package springplayground.controller;

import java.util.List;

import springplayground.service.MyService;

/**
 * @author Moritz Halbritter
 */
public class MyController {
	private final MyService myService;

	public MyController(MyService myService) {
		this.myService = myService;
	}

	public List<MyView> findAll() {
		return myService.findAllBos().stream().map(MyView::fromBo).toList();
	}

	public void add(long id, String name) {
		myService.addBo(id, name);
	}
}
