
// NOTE: I no longer own this domain
package net.codetojoy;

// from https://javahippie.net/java/concurrency/2022/04/12/getting-started-with-virtual-threads.html
// and https://www.youtube.com/watch?v=UqlF6Mfhnz0

public class Runner {
    static void doWork() {
        System.out.println("TRACER working t: " + Thread.currentThread());
        try { Thread.sleep(5000); } catch (Exception ex) {}
    }

void createManyThreads(int numThreads) throws Exception {
    Thread thread = null;

    for (int i = 0; i < numThreads; i++) {
        thread = Thread.startVirtualThread(Runner::doWork);
    }

    thread.join();
}

    public static void main(String... args) throws Exception {
        int numThreads = 20;
        new Runner().createManyThreads(numThreads);
        System.out.println("TRACER Ready v2");
    }
}
