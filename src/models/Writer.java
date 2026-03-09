package models;


import java.util.concurrent.locks.Lock;


public class Writer {

    private final Lock writeLock;

    private final File file;

    public Writer(Lock writeLock, File file) {
        this.writeLock = writeLock;
        this.file = file;
    }

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
