
### Post-Scripts

Here are some bonus notes that are not included in the talk:

* in my semaphore example, I used a database (vs microservice)
    - Ron Pressler (Loom lead) has argued on Twitter that one would still use a [connection pool](https://twitter.com/pressron/status/1533423657585938433) in this case
    - generally, [this thread](https://twitter.com/gunnarmorling/status/1533166270639751170) is very good, though long
* As of JUN 2022, virtual threads are *not* swapped out in code blocks protected by `synchronized`
    - see 11m18s [here](https://www.youtube.com/watch?v=lKSSBvRDmTg)
    - such code could be changed to use `ReentrantLock`
* As of JUN 2022, virtual threads can be "pinned" (i.e. stuck) to the platform thread
* Because virtual threads are stored on the heap, there can be memory pressure re: garbage collection in extreme cases
    - Jetty reported this in a major test
    - unfortunately, it looks like links to [their posts](https://mail.openjdk.java.net/pipermail/loom-dev/2020-December/001974.html) aren't working properly

