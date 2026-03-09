import models.Reader;
import models.Writer;
import models.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {

    private static final int READERS_COUNT = 15;
    private static final int WRITERS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        File file = new File();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= READERS_COUNT; i++) {
            long id = i;
            threads.add(new Thread(() -> {
                new Reader(lock.readLock(), file).read(id);
            }));
        }

        for (int i = 1; i <= WRITERS_COUNT; i++) {
            long id = i;
            threads.add(new Thread(() -> {
                new Writer(lock.writeLock(), file).write(id);
            }));
        }

        Collections.shuffle(threads);

        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
}