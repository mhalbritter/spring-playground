package springplayground.controller;

import springplayground.service.MyBo;

/**
 * @author Moritz Halbritter
 */
public record MyView(long id, String name) {
	static MyView fromBo(MyBo bo) {
		return new MyView(bo.id(), bo.name());
	}
}
