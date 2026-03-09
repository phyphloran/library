import models.Reader;
import models.Writer;
import models.File;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final int READERS_COUNT = 15;
    private static final int WRITERS_COUNT = 4;

    public static void main(String[] args) {
        File storage = new File();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= READERS_COUNT; i++) {
            long id = i;
            Reader reader = new Reader(lock.readLock(), storage);
            threads.add(new Thread(() -> {
                reader.read(id);
            }));
        }

        for (int i = 1; i <= WRITERS_COUNT; i++) {
            long id = i;
            Writer writer = new Writer(lock.writeLock(), storage);
            threads.add(new Thread(() -> {
                writer.write(id);
            }));
        }

        Collections.shuffle(threads);

        for (Thread t : threads) {
            t.start();
        }
    }
}