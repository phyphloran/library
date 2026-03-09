package models;


import lombok.RequiredArgsConstructor;
import java.util.concurrent.locks.Lock;


@RequiredArgsConstructor
public class Writer {

    private final Lock writeLock;

    private final File file;

    public void write(long writerId) {
        writeLock.lock();
        try {
            System.out.println("Writer " + writerId + " started writing");
            Thread.sleep(500);
            file.increment();
            System.out.println("Writer " + writerId + " changed value. value=" + file.getValue());
            System.out.println("Writer " + writerId + " finished writing");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

}
