
### Post-Scripts

Here are some bonus notes that are not included in the talk:

* in my semaphore example, I used a database (vs microservice)
    - Ron Pressler (Loom lead) has argued on Twitter that one would still use a [connection pool](https://twitter.com/pressron/status/1533423657585938433) in this case
    - generally, [this thread](https://twitter.com/gunnarmorling/status/1533166270639751170) is very good, though long
* As of JUN 2022, virtual threads are *not* swapped out in code blocks protected by `synchronized`
    - see 11m18s [here](https://www.youtube.com/watch?v=lKSSBvRDmTg)
    - such code could be changed to use `ReentrantLock`
* As of JUN 2022, virtual threads can be "pinned" (i.e. stuck) to the platform thread
    - see note on `synchronized` above
    - also when doing computationally intensive work
    - generally, this is a Bad Thing: there are ways to detect this via Java Flight Recorder
* Because virtual threads are stored on the heap, there can be memory pressure re: garbage collection in extreme cases
    - Jetty reported this in a major test
    - unfortunately, it looks like links to [their posts](https://mail.openjdk.java.net/pipermail/loom-dev/2020-December/001974.html) aren't working properly
* more resources:
    - [What Color Is Your Function?](https://journal.stuffwithstuff.com/2015/02/01/what-color-is-your-function/)
    - [seminal article on structured concurrency](https://250bpm.com/blog:71/)
        - Martin Sústrik is credited with coining structured concurrency
    - [NJS blog](https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/) on 'go statement considered harmful'
* Matt Duffy pointed out that ALGOL 60 also introduced the concept of *continuations*
    - ALGOL 58/60 was amazing
    - as Matt mentioned in person, the period of 1955-1965 was incredible for computing
        - i.e. ALGOL, Fortran, Lisp, and other languages covered a ton of ground

