package springplayground.repository;

import java.util.ArrayList;
import java.util.List;

import springplayground.service.MyBo;

/**
 * @author Moritz Halbritter
 */
public class InMemoryRepository implements MyRepository {
	private final List<MyEntity> entities = new ArrayList<>();

	@Override
	public void save(MyBo bo) {
		entities.add(MyEntity.fromBo(bo));
	}

	@Override
	public List<MyBo> loadAll() {
		return entities.stream().map(MyEntity::toBo).toList();
	}
}
