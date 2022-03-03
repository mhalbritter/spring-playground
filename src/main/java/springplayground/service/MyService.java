package springplayground.service;

import java.util.List;

/**
 * @author Moritz Halbritter
 */
public interface MyService {
	List<MyBo> findAllBos();

	void addBo(long id, String name);
}
