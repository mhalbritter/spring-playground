package springplayground.service;

import java.util.List;

import springplayground.repository.MyRepository;

/**
 * @author Moritz Halbritter
 */
public class DefaultService implements MyService {
	private final MyRepository myRepository;

	public DefaultService(MyRepository myRepository) {
		this.myRepository = myRepository;
	}

	@Override
	public List<MyBo> findAllBos() {
		return myRepository.loadAll();
	}

	@Override
	public void addBo(long id, String name) {
		myRepository.save(new MyBo(id, name));
	}
}
