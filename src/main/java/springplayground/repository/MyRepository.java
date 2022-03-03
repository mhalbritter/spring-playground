package springplayground.repository;

import java.util.List;

import springplayground.service.MyBo;

/**
 * @author Moritz Halbritter
 */
public interface MyRepository {
	void save(MyBo entity);

	List<MyBo> loadAll();
}
