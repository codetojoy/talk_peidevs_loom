
// note: I no longer own this domain
package net.codetojoy;

import java.time.Duration;
import java.util.concurrent.Future;
import jdk.incubator.concurrent.*;

// javadoc here: https://download.java.net/java/early_access/loom/docs/api/

public class Runner {
    int taskFooDelayInMillis = 2000;
    int taskBarDelayInMillis = 15000;

    String taskFoo() {
        String result = "";
        try {
            result = new Worker().doWork(taskFooDelayInMillis, "taskFoo", "foo-5150");
        } catch (Exception ex) {
            System.err.println("TRACER foo caught ex: " + ex);
        }
        return result;
    }

    String taskBar() {
        String result = "";
        try {
            result = new Worker().doWork(taskBarDelayInMillis, "taskBar", "bar-6160");
        } catch (Exception ex) {
            System.err.println("TRACER bar caught ex: " + ex);
        }
        return result;
    }

    String run() throws Exception {
        try (var scope =
                new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            Future<String> foo = scope.fork(() -> taskFoo());
            Future<String> bar = scope.fork(() -> taskBar());

            scope.join();

            return scope.result();
        }
    }

    public static void main(String... args) {
        var runner = new Runner();

        try {
            String result = runner.run();
            System.out.println("TRACER result: " + result);
        } catch (Exception ex) {
            System.err.println("TRACER caught exception: " + ex.getMessage());
        }
    }
}
