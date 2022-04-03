package compute;

import org.junit.Test;
import org.tcrow.compute.ComputePension;

import java.util.concurrent.locks.ReentrantLock;

public class TestComputePension {

    private final static ReentrantLock lock = new ReentrantLock();

    public void lock() {
        lock.lock();
    }

    public static void main(String[] args) {
        lock.lock();
        try {
            lock.lock();
            System.out.println("" + lock.isLocked());
        }finally {
            lock.unlock();
        }
    }

}
