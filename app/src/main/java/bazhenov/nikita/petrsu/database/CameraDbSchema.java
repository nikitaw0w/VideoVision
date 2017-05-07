package bazhenov.nikita.petrsu.database;

public class CameraDbSchema {
    public static final class CameraTable {
        public static final String NAME = "camera";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String ID = "id";
            public static final String USER = "user";
            public static final String PASSWORD = "password";
        }
    }
}
