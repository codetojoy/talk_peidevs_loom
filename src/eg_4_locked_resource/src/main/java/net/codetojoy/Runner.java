
// NOTE: I no longer own this domain
package net.codetojoy;

import java.util.concurrent.*;
import java.util.*;

class Worker {
    void simulateWork(String message, int delayInSeconds) {
        try {
            long delayInMillis = delayInSeconds * 1000L;
            System.out.println("TRACER " + message + 
                                " t: " + Thread.currentThread() + 
                                " sleeping...");
            Thread.sleep(delayInMillis);
        } catch(Exception ex) {
        }
    }
}

class User {}

class Database {
    User findUser(int id) {
        new Worker().simulateWork("querying database", id);        
        return new User();
    } 
}

class ConnectionPool {
    private static final int MAX_AVAILABLE = 2;
    private final Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    private final Database database = new Database();

    Database acquireDatabase() throws InterruptedException {
        available.acquire();
        return database;
    }

    void releaseDatabase() {
        available.release();
    }
} 

class MyTask implements Runnable {
    int id;
    ConnectionPool pool;

    MyTask(int id, ConnectionPool pool) {
        this.id = id;
        this.pool = pool;
    }

    @Override 
    public void run() {
        try {
            var database = pool.acquireDatabase();
            var user = database.findUser(id); 
            // ...
        } catch (Exception ex) {
            System.err.println("caught ex: " + ex.getMessage());
        } finally {
            pool.releaseDatabase();
        }
    }
}

public class Runner {
    ConnectionPool pool = new ConnectionPool();
    
void run() throws Exception {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        int numTasks = 10; 
        for (int i = 0; i < numTasks; i++) {
            executor.submit(new MyTask(i, pool)); 
        }
    }
}

    static public void main(String... args) throws Exception {
        new Runner().run();
        try { Thread.sleep(10* 1000L); } catch (Exception ex) {}
        System.out.println("Ready.");
    }
}
