import java.util.HashMap;
import java.util.Map;

public class Utils {
	public interface Database {
		Object getDb(String key);

		void addDb(String key, Object value);
	}

	public static void main(String[] args) {
		Database db = new MapDatabase();
		Database db1 = Utils.createDatabase();

	}

	public static class MapDatabase implements Database {
		private Map<String, Object> database;

		MapDatabase() {
			database = new HashMap<>();
		}

		@Override
		public Object getDb(String key) {
			return database.get(key);
		}

		@Override
		public void addDb(String key, Object value) {
			database.put(key, value);
		}
	}

	public static Database createDatabase() {
		return new MapDatabase();
	}

	public static void setFrame() {
		// 구현
	}

	public static void inf() {

	}

	public static void err() {

	}

}
