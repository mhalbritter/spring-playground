package springplayground.repository;

import springplayground.service.MyBo;

/**
 * @author Moritz Halbritter
 */
public record MyEntity(long id, String name) {
	static MyEntity fromBo(MyBo bo) {
		return new MyEntity(bo.id(), bo.name());
	}

	public MyBo toBo() {
		return new MyBo(this.id, this.name);
	}
}
