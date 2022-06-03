
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

class MyTask implements Runnable {
    @Override
    public void run() {
        var user = database.findUser(id);
        // ...
    }

    int id;
    Database database;

    MyTask(int id, Database database) {
        this.id = id;
        this.database = database;
    }

}

public class Runner {
    Database database = new Database();

void run() throws Exception {
    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
        int numTasks = 10;
        for (int i = 0; i < numTasks; i++) {
            executor.submit(new MyTask(i, database));
        }
    }
}

    static public void main(String... args) throws Exception {
        new Runner().run();
        try { Thread.sleep(10* 1000L); } catch (Exception ex) {}
        System.out.println("Ready.");
    }
}
