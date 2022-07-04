package ai.growfin.desk.util;

public class SingletonUtil {
    private static SingletonUtil instance = null;

    private SingletonUtil() {
        // Protect against instantiation via reflection
        if (instance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    }

    public static synchronized SingletonUtil getInstance() {
        if (instance == null) {
            synchronized (SingletonUtil.class) {
                if (instance == null) {
                    instance = new SingletonUtil();
                }
            }
        }
        return instance;
    }
}
