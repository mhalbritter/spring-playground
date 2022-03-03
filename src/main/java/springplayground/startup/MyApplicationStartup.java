package springplayground.startup;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.core.metrics.ApplicationStartup;
import org.springframework.core.metrics.StartupStep;
import org.springframework.core.metrics.StartupStep.Tag;
import org.springframework.core.metrics.StartupStep.Tags;

/**
 * @author Moritz Halbritter
 */
public class MyApplicationStartup implements ApplicationStartup {
	private long id = 0;

	@Override
	public StartupStep start(String name) {
		var step = new Step(id, id == 0 ? null : id, name);
		id++;
		return step;
	}

	private static class Step implements StartupStep {

		private final long id;

		private final Long parentId;

		private final String name;

		private final long started;

		private final MyTags tags = new MyTags();

		private Step(long id, Long parentId, String name) {
			this.id = id;
			this.parentId = parentId;
			this.name = name;
			this.started = System.nanoTime();
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public long getId() {
			return id;
		}

		@Override
		public Long getParentId() {
			return parentId;
		}

		@Override
		public StartupStep tag(String key, String value) {
			tags.add(key, value);
			return this;
		}

		@Override
		public StartupStep tag(String key, Supplier<String> value) {
			tags.add(key, value.get());
			return this;
		}

		@Override
		public Tags getTags() {
			return tags;
		}

		@Override
		public void end() {
			long took = System.nanoTime() - this.started;
			System.out.printf("Startup step %d '%s' %s took %s%n", this.id, this.name, this.tags, Duration.ofNanos(took));
		}
	}

	private static class MyTags implements Tags {
		private final List<Tag> tags = new ArrayList<>();

		@Override
		public Iterator<Tag> iterator() {
			return tags.iterator();
		}

		void add(String key, String value) {
			tags.add(new MyTag(key, value));
		}

		@Override
		public String toString() {
			return tags.toString();
		}
	}

	private static class MyTag implements Tag {
		private final String key;

		private final String value;

		MyTag(String key, String value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getValue() {
			return getValue();
		}

		@Override
		public String toString() {
			return "%s=%s".formatted(this.key, this.value);
		}
	}
}
