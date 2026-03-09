package models;


import java.util.concurrent.locks.Lock;


public class Reader {

    private final Lock readLock;

    private final File file;

    public Reader(Lock readLock, File file) {
        this.readLock = readLock;
        this.file = file;
    }

    public void read(long readerId) {
        readLock.lock();
        try {
            System.out.println("Reader " + readerId + " started reading");
            Thread.sleep(500);
            System.out.println("Reader " + readerId + " read file. value=" + file.getValue());
            System.out.println("Reader " + readerId + " finished reading");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

}
