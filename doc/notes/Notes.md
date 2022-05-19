
### TODOS

* test code formatting in google slides
* rewatch Ron P first talk
* code example
    - thread per request
    - thread pool
    - Reactive
    - Loom
    - just code snippets? do they have to work?
* start Keynote or Google presentations?
* find concurrency vs parallel analogy
* flight recorder
* code
    - consider showing that v-threads are moved (as in Venkat's talk)
* x - proper eggs project
* x - friend or foe talk ?
* x - 2nd Ron P talk
* x - Gradle
* x - Venkat talk

### articles, videos

* JEP draft: Structured Concurrency
* https://openjdk.java.net/jeps/8277129
* Ron Pressler
    - he did something big in open-source prior to Oracle/JDK
* let sc := structured concurrency
* let vt := virtual thread
* simple principle: when flow of execution splits into multiple, then rejoin in the same code block
* simplifies: failures, cancellation, deadlines, observability, stack traces, tooling
* sc is new, evolving
* motivation
* historical view of structured programming solving pitfalls
* analogy: sc might help with pitfalls of concurrency
* key example: foo calls bar and baz 
* if single threaded, nice things happen if bar is interrupted or throws an ex
* if ExecutorService, problems:
    - problems if bar or baz fails
    - problems if foo is interrupted 
    - poor info from thread dump, as the relationships are not expressed
* sc solves this with built-in constructs
* pre-requisite: Project Loom and virtual threads
* vt can be used to represent relationships
* in sc, there are parent/sibling relationships between work units 
* sc facilitates a variety of cancellation schemes/policies
* sc is counterpart to parallel streams
* key part of sc is clarity in the syntax of the code
* new: StructuredExecutor (SE)
* nice code example: regains the 3 desired properties
* SE is AutoCloseable, used in a `session` defined by a try-catch block
* at any time, a thread can shutdown the SE entirely
    - i.e. when we know we are done, either for better or worse
* completion handler as a policy
* SE is more than a utility: tree of work units, tracked by runtime
* examples of low-level APIs  
* [1] - https://250bpm.com/blog:71/
* [2] - https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/

* video 
* Loom Q&A plus SC
* Ron Pressler with Barcelona JUG
* https://www.youtube.com/watch?v=i1MgOVf-hIQ
* RP is tech lead for Project Loom
* let PL := project loom
* let SC := Structured Concurrency
* PL allows many threads
* since we're there, let's do sc as well    
* historical view: structured programming (like JEP brief)
* even the same code examples as the JEP !
* will probably just watch
* unclear if `TaskSession` is older form of `StructuredExecutor`
    - i think so, given code examples

### video

* Ron Pressler, Chariot Solutions
* https://www.youtube.com/watch?v=EO9oMiL1fFo
* concurrency: sharing resources among multiple, competing interests
    - see video for ACM definition
* parallelism: dividing work into co-ordinating work units
* Java uses threads for concurrency
* in particular, OS threads
* because the OS is generic and assumes worst-case, the stack is massive
* consequently, about 10k threads is max, which is terrible given the hardware
    - e.g. we can have millions of sockets
* also:
    - task-switching is switch in kernel
    - scheduling is a compromise for all usages (bad locality)
    - page granularity, can't uncommit
    - analogy ??? for using a generic tool 
* usually focus on writing servers
* thread-per-request style
    - makes sense, esp. easy for programmers
    - bad performance, bad utilization of hardware  
* thread-pool
    - e.g. executors
    - leaks thread local info, if not careful 
        - e.g. current user
    - complex cancellation
    - return at end vs return at wait
    - return at wait introduces async APIs which are viral
    - tooling/debuggers assumes the thread have the context info 
* async
    - hardware is happy
    - UX is rough
    - e.g. machine is very busy but all threads are idle (due to async operations)
* devs are given two bad options
    - good graphic
* goal: "codes like sync, scales like async"
* light-weight threads have 2 camps
* camp 1: async/await (C#, JS, Kotlin, C++, Rust)
    - new syntactic category 
    - thread-like 
* camp 2: user-mode threads (Go, Erlang)
    - the runtime manages thread-like structures that are not OS-level threads
* thread vs async/await # 1
    - interleaving: threads roam everywhere except critical section; async/await is the opposite
    - async/await _allows_ interleaving with `await`
* JS is single-threaded, no interleaving
    - async/await fits here because we must make interleaving explicit
* thread vs async/await # 2
* implementation
* thread: requires integration with coroutines (back-end)
* async/await: can be done in the compiler front-end 
    - e.g. Kotlin must use this to be multi-platform (JS, Android, etc)
* thread vs async/await # 3
* recursion, virtual calls, FFI 
    - super wonky
    - hard to know how much stack to create
    - C++, Rust have no GC so async/await is far simpler
* Java
    - user-mode threads
    - control over backend, GC
    - threads already in platform
    - efficient and transparent stack resizing
* quotes about balancing conservation and innovation
    - Ford F150
    - Ford couldn't do the Cyber Truck
* threads in Java
    - legacy use of Thread and ThreadLocal are everywhere
    - people use Future and Executors anyway, so deprecation opportunities
* virtual threads
    - Java runtime can implement threads
    - resizable stacks (because only in Java)
    - context-switching in user-mode
    - Q: what is user-mode, exactly?
        - i.e. not kernel, presumably faster
    - pluggable schedulers
* we decide if thread is 'platform' (i.e. OS) or virtual
* v-threads are cheap to create
* graphic: map v-threads onto p-threads
* use-case
    - v-thread does IO in JDK
    - v-thread is suspended
    - new v-thread (?) is given to OS for non-blocking IO operation
    - when complete, the scheduler unifies
    - for user: as though the thread made a blocking IO call 
* huge effort inside the JDK to make this happen
* v-threads are threads, even in the tooling/debugger
* devs must unlearn certain patterns (e.g. pools)
* creating a new v-thread is basically free
    - e.g. request uses 20 microservices: just use 20 threads
* Little's Law
    - Lam = L / W
    - throughput is level of concurrency / average duration
    - little control over W
    - virtual threads increase L big-time: 1m vs 4k
    - context-switching impact on throughout: t / u
        - t: mean context-switch latency
        - u: mean wait/IO latency
        - context-switch is not the big impact
* Structured Concurrency
    - this makes sense: PL enables SC
    - better mechanism
    - two blog-posts
    - 'go statement considered harmful' 
        - WOW ... i missed this... wow
        - compares Go's `go` to `goto`
* code samples
* time-outs / deadlines
* tools: suggest to IDE vendors to use a tree representation
* Tomasz Nurkiewicz has a counter-argument

### article

* Nathaniel Smith
* Notes on structured concurrency, or: Go statement considered harmful 
* https://vorpus.org/blog/notes-on-structured-concurrency-or-go-statement-considered-harmful/
* code examples: spawn thread
* code examples: callbacks
* Trio lib for Python - https://trio.readthedocs.io/en/stable/ 
* nurseries (?)
* goto 
* goto statement history
* no block syntax: obligatory "spaghetti code"
* structured programming involves syntactical structure
* `go` statement in Go
* control flow for all of the examples is complicated
    - there are ways to `join` the forked flows but the language doesn't know anything about that
* uses `go statement` as a label for the forked flow
    - ties `go statement` with `goto`
* late 1960s, Dijkstra's famous paper 'goto considered harmful'
* goto destroyed abstraction, which was essential for larger programs
* enter ALGOL
* new if, loop, etc had a natural flow, "black-box"
* goto destroys the black box, and devs have to know the code intimately  
* Donald Knuth defended goto: the paper was controversial
* Dijkstra won
* removing goto led to new features, design
* go statements have the same issue
    - break abstraction
    - break auto-resource cleanup 
    - break error handling
    - i.e. you need to know the code transitively to reason about it
* fixing go is the same as fixing goto
* nursery is a real concept here, not just a var name
    - I think Ron P calls them 'sessions'
* when we fork flows, we must join again and preserve the black-box
* nursery holds child work units
    - very similar to the JEP / Ron Pressler on SC
* if background task needs to outlive the function, pass nurseries as objects
* point-by-point argument for Trio/nursery in fixing issues
* Trio vs Twisted
* usual wrap-up conclusion 

###

* video
* Tomasz Nurkiewicz for Developers World Academy 
* Project Loom: Revolution in concurrency or obscure implementation detail?
* https://www.youtube.com/watch?v=t8vaZJG0kt4
* possibly the same talk as [1], same slide deck
* slides: https://nurkiewicz.com/slides/loom
* but! questions might be different
* Q: on Liskov principle and CompletableFuture
    - couldn't cancel CF, leaky abstraction
    - TN wrote an article about this in the past
    - program won't be exactly the same with v-threads
        - e.g. throttling
* Q: benchmarks
    - premature
    - jetty: GC was doing a lot of work
* Q: on context switch
    - platform thread switch much more expensive than v-thread

* [1] video
* Tomasz Nurkiewicz & Dr Heinz Kabutz
* Project Loom: Revolution in concurrency or obscure implementation detail?
* https://www.youtube.com/watch?v=n_XRUljffu0
* key points
    - SC is over-stated as it is just a small part of the picture
    - PL can't replace Reactive as it doesn't address back-pressure, composability  
        - a bit of a shot at Brian Goetz? 
    - dynamic stack size and avoiding kernel context switches is huge
    - some good Q&A but probably won't annotate
* Q: what is a Java Champion?
* summary: create 1m threads ; block/sleep for 'free' ; does it matter?   
* user thread: e.g. new Thread().run( ... )  
    - created by JVM
* kernel thread: OS level
    - OS scheduler: which kernel threads get a CPU
* green threads: many-to-one model
    - ancient and short-lived
* before PL, typical Java: one-to-one
    - outsource scheduling to the OS
    - `jstack` tool with `tool -H`
* in Linux: a thread is usually a process !!!
    - for scheduler, 1000 threads is equivalent to 1000 processes
* why expensive
    - 1 MB of memory (not counting heap) ; -Xss
    - kernel thread
    - leads to reactive programming, event loops, etc
* with PL
* 3 concepts:
    - virtual thread
        - user thread, not kernel thread
        - cheap
        - no need to pool
    - carrier thread
        - real/kernel thread running virtual thread
    - continuation
* v-thread runs on c-thread "pinned"
    - when v-thread sleeps/blocks, c-thread can swap it out
    - stack is packed and set aside on the heap: continuation
    - many, many more v-threads than c-threads 
    - v-threads are suspended when blocking etc ... takes no resources
        - only uses v-thread stack 
        - nowhere near the 1 MB
* new Executor for v-threads but it is just the familiar interface
    - it is not a thread-pool: just creates new v-threads
* v-thread: continuation + scheduler
    - scheduler is a fancy thread-pool of c-threads
        - forkjoinpool 
    - often # of c-threads matches # of CPU
    - continuation
* continuation
    - weird function
    - can be stopped/suspended
    - can be resumed by other code (at point of suspension)
    - remembers recursion, iteration of loop, etc
* good code example
* also seen in Go, Kotlin
    - coroutines in Kotlin is similar 
* also in JS (async/await) and Python (generators)
* good Java example: scheduleUnpark()
* huge effort in the JDK to yield if a v-thread and blocking
    - e.g. socket, semaphores, phasers, cyclic barriers, etc 
* now can use Apache HTTP client, REST template, etc in v-thread
* "superficially, it means we can throw away Reactive and get simpler again"
    - REST templates were not scalable, but now maybe so
* Jan 2022: limitation is that v-threads cannot be pre-empted
    - so 5 v-threads crunching numbers with 4 CPU cores
    - one v-thread will starve
* continuations are not part of public API for PL 
* code examples
* SC example
    - code splits into N flows, and then joins
    - auto-closeable try-catch 
* Cay Horstman: tasks, not threads
    - shout-out to Dale W
* thread per request is now possible
* v-threads consume ~1 KB not 1 MB
* download 10k images
* key point
    - old style was inherent throttling mechanism
    - that is gone, but creating N v-threads might be DDOS to someone else
    - so CountdownLatch, Phasers, etc all come back into play
    - i.e. we need to throttle _ourselves_ to be good citizens
* problems and limitations
* easy to illustrate 'hello, world' examples
* spring security has a deep stack: 200-300 deep stack
    - amount of memory is same order of mag as p-thread
    - burden on GC because continuations are on heap
* pre-emption
* unsupported APIs
    - a lot of APIs are reimplemented to be aware of v-thread
    - file
    - synchronized
    - native code 
* do we still need Reactive?
    - PL just addresses async
    - what about nice API, and:
        - back-pressure
        - change propogation
        - composability
* tweet about JDK 17 
* links include Jetty team and Dr HK

* [1] - slides - https://nurkiewicz.com/slides/loom

###

* article
* Jetty, though 2020
* Do Loom's claims stack up? 1 million threads?
* part 1
* link: https://webtide.com/do-looms-claims-stack-up-part-1/
* RP quote: one thread per task
* history of Jetty
* v0: thread per request
* v1: thread per connection, pooled with N k threads
* 2006: async io
* 2012: fully async, though still problems
* claim: native threads with 1:1 was tauted as a fix for green threads (M:N) 
    - scalability, simplicity, etc
* if green threads didn't work, what is different now?
* 32k platform threads vs 1m virtual threads
* sarcastic "free lunch!"
* all about stack size:
    - kernal threads have large stack by default
    - 1m examples have minimal stack
    - authors will work with realistic stack
* eg: 26k v-threads, then GC, then 55k v-threads and crash
* v-threads are OK but not as advertised
    - gym memberships, banking on not everyone going at once
* good comparison between k-threads and v-threads
    - regarding stack, heap, etc  

* article
* Jetty, though 2020
* Do Loom's claims stack up? 1 million threads?
* part 2
* link: https://webtide.com/do-looms-claims-stack-up-part-2/
* seem to be arguing:
    - most apps don't need Loom
    - Loom wouldn't satisfy the 1% apps 
* experiment: v-threads are quicker to start (lighter) than k-threads
* counter: thread pools are usually 100s whereas k-threads can be 1000s ... why?
* thread-pool is built-in throttle for resources consumption (e.g. backend services)
* throughput argument?
* thread-pool is a limiter for heap usage as well
    - running out of memory is fatal
* convention
    - if thread pool of N is not sufficient
    - go to async mode for more efficiency
    - design for waiting
    - i.e. cranking # of threads is not desirable 
* v-threads vs async api
    - gist is that Jetty can't just swap in Loom
    - various scheduling algos ("eat what you kill") 
* v-threads can be hung up on CPU-bound or `synchronized` 
* note about thread local
    - possibly addressed by now
* conclusion
* Loom may require new programming models: e.g. structured concurrency
* where is the limiting?
* pros:
    - blocking code is easier to write
    - cheap to create
    - cheap to block
* cons:
    - rhetoric around scaling v-threads: doesn't address scaling resources
    - "forget about thread-pools", "reactive is dead" (?) 
* good comment from RP via reddit

 
* code on github - https://github.com/webtide/loom-trial/blob/main/src/main/java/org/webtide/loom/MaxVThreads.java 
* reddit thread - https://www.reddit.com/r/java/comments/kmn6m3/do_looms_claims_stack_up_part_1_millions_of/
    - really good between RP and authors
    - I think jetty author is Greg Wilkins
* mailing list thread - https://mail.openjdk.java.net/pipermail/loom-dev/2021-January/001976.html
* RP on reddit - https://www.reddit.com/user/pron98/

* reddit post
* https://www.reddit.com/r/java/comments/uckae4/5000000_persistent_connections_200000000_messages/
* 5m connections
* reference to project: https://github.com/sormuras/junit5-looming 

* article
* https://jbaker.io/2022/05/09/project-loom-for-distributed-systems/
* Jespen and FoundationDB: test properties of distributed DB
* Jespen
    - injects 'nemeses'/pathogens 
    - clock skew, network latency, etc
    - Kyle Kingsbury
    - https://jepsen.io/talks
    - has problems: Linux, small-scale, takes time
    - white box testing, non-deterministic
* FoundationDB
    - Strange Loop, 2014
    - BDD style!
    - built a deterministic simulation of a database 
    - mocks
    - tests: deterministic and fast
    - Flow language
* dichotomy: but Loom shakes it up
    - good table
* example of testing for a thread/interleaving bug 
    - fairly sophisticated
* illustration of testing with toy impl of Raft
* intense stuff here
    - not sure i grok where Loom fits in
    - thread exits the runnable ??? 
    - from hn, appears to use a pluggable executor/scheduler that was removed from the api 
    - like continuations: scheduling is there, but internal for initial releases
    - pron: Ron P  
* money quote on hn:
    - I spent the last few years really working hard to understand async frameworks/libraries like VertX, Mutiny etc I've gone from "I hate it" to "I can live with it". Now I dont have to. :) Yay.
    - Loom is [a particular type of] continuations in the JVM, which can be used to implement green threads / fibers.
* [1] - see article for github link
* [2] - on hacker news: https://news.ycombinator.com/item?id=31314006

* video
* Dr. Venkat S
* link: https://www.youtube.com/watch?v=UqlF6Mfhnz0
* July 2021  
* text slides!
* "biggest thing in a long time"
    - 10 years in the making?
* when? unknown
* parallel vs concurrent
    - weak analogy: walk/talk vs talk/drink water
* parallel vs async
    - orthogonal
* how many threads?
    - # of cores, amount of memory
    - typical example of creating many threads with sleep
    - limit near 10k
* multithreading
    - pre-19, threads tied to a task 
    - coffee machine example
    - waiter example
    - code example #2 (near 19:00)
        - http request with delay
        - countdown latch
        - demostrates sync, threads held hostage
        - not efficient
    - we want threads to be decoupled from tasks
* continuations
    - # of threads = # of cores / 1 - BF ; blocking factor
        - BF is time spent waiting
    - computational tasks tends toward # of cores
    - waiting for IO tends toward N * # of cores
    - subroutine
    - coroutine !!
        - retains conversational state
        - continuation is the data structure for state
            - JS example of generator
            - stack, local vars (lexical scope), etc
            - Kotlin example
                - `suspend`
                - `javap` shows continuation is used
    - MEGA: continuation in the API but we know it is no longer public
        - let's skip Java example
* coroutines
    - near 50:00 explains that f1, f2 can co-ordinate by sharing state: got it
* at 50m, still no explanation of virtual threads!
* v threads
    - super lightweight
        - point: threads are lightweight compared to a process
    - managed by JVM, not OS 
    - tasks can block but does not block underlying thread
    - way more v-threads
    - revisit code example of threads/sleep: 1m threads 
    - revisit thread id code example
        - shows v-threads being moved to carrier threads
    - thread local
        - V punts: bad idea so avoid it 
    - JDK itself is modified to handle virtual threads
        - massive undertaking
* summary
    - where does it make sense?
        - for blocking IO re: equation 
        - not for computationally intensive
    - Q: reactive systems
        - functional pipeline is foundational and mandatory
        - V argues for choice
        - Loom allows async with imperative style
            - thread-per-request
    - big impacts/effects:
        - enjoy better resource utilization
        - impact the way we write
    - Q: thread pools thing of the past?
    - Q: replace thread pools in Tomcat
    - Q: go-routines
        - nothing new here
* [1] - httpstat.us

### video

* video
* Ron Pressler Q&A, Carlos O
* MAR 2022
* link: https://www.youtube.com/watch?v=cAHW96omBAc
* history
* thread-per-request in servlet model
    - coupling requests to threads
    - throughput problems due to N threads 
    - 1:1 model for Java thread to OS thread
        - OS threads have large stack for worst, generic case
    - even though threads are waiting for IO: we reach the limit
    - inefficient hardware utilization
    - wastes $$$ on hardware
* alternative: async
    - CompletableFuture, Reactive
    - request uses thread until it blocks, then yields the thread
    - when IO complete, callback is used
    - this solves the scalability problem
    - high price:
        - non-blocking APIs
        - complex code
        - JVM doesn't know anything about the small tasks
            - e.g. debugger support, profiling, stack trace (for exception)
    - wastes $$$ on dev
* alt x 2: Loom
    - allow N >> 1000 threads
    - v-threads
    - addresses issues with debugger, profiler, stack trace etc
    - simple code that harmonious with the platform 
        - imperative
* great slide, internal to Oracle
    - reactive scales well but terrible programming model
    - Little's Law
    - Jetty with 200 platform threads: latency spike / 2% CPU util 
    - Jetty with 800 platform threads: latency spike / 10% CPU util 
    - Jetty with virtual thread per request: no latency until 100% CPU 
* explicit decision to create p-thread or v-thread
    - v-threads are not faster: can have many of them
    - more req/sec: higher throughput
    - but it is easy to switch between v-thread and p-thread
    - v-threads help more when IO/blocking (vs compute bound)
    - MEGA: N concurrent computations for N cores, but M waiting actors where M >> N
* Q: what about synchronization
    - `synchronized` will pin the thread
    - prefer ReentrantLock 
    - JFR will emit pin-events
* JEP preview
    - requires `--enable-preview`
    - feature is 95% done
    - multiple previews, then GA
    - "within a year, it will be done"
* incubation: less guarantee than preview
    - changes are more likely
* Loom: 3 features, 3 JEPs
    - 1. JEP 425 v-thread
        - very little changes to the API
    - 2. structured concurrency 
    - 3. scope locals
        - faster, more correct flavour of thread locals
    - 2,3 are incubation
* structured concurrency
    - relatively new idea, but there is prior art
    - Rust, Kotlin, C++, Python do/want to use it
    - Java team likes to wait
        - shot at C# and async/await "horrible idea" ... coloured functions? 
        - but s.c. is an exception
    - code example is same as blog post
    - behaviour of program is reflected in syntactical structure
* Q: about other langs
    - good discussion on golang
* discussion about how to get JDK, Javadoc
    - Thread factory methods
    - use ExecutorService/quasi-thread-pool 
        - `newVirtualThreadPerTaskExecutor`
    - common mistakes:
        - create a v-thread per task
        - use Executors
* Q: about reactive !!
    - no one is taking away R
    - BINGO: v-threads offer same scalability as R, with thread-per-request/imperative
    - with preview, there will be a demo web server for scale
* misconception on ms
    - 20 concurrent connections 
    - use thread-pool with 20 threads
    - for v-threads, we don't want to pool
        - pointless, but also dangerous for thread local
    - use JDK constructs:
        - countdown latches, semaphores, etc
* personal Qs
    - ugh

### video

* video
* Loom: Friend or Foe
* Oleh Dokuka, VMware & Andrii Rodionov
* mock adversarial prezi
* link: https://www.youtube.com/watch?v=YwG04UZP2a0
* Nov 2021
* not covered: loom internals, Project R re-impl/impact  
* BINGO: will R be killed by Loom or not?
* sensors and mobile app
* e.g. temp sensor in apartment
* find h in houses, find sensors for h 
    - block on DB
    - find data for each sensor s
    - not efficient
    - use Futures/Callables: thread for each sensor
    - motivates timeout on `invokeAll`
    - OD objects: `invokeAll` is blocking
    - alternative: countdownlatch
* unhappy graph of scalability story: threads
* reactive async: CompletableFuture
    - reduction framework
    - functional, compose
    - complicated, "strange"
    - cancellation won't work
* Project Reactor
    - Flux, Mono
    - pipeline of operators
    - elegant 
* fun graph of co-routines vs FRP
    - looking at someone else's FR code 
    - debugging difficulty/stack trace
* "most simple solution as Brian Goetz says, is Project Loom"
    - with a quote of BG himself
    - link is to the "AMA" with Nicolai P and BG 
    - wow
* revisit Executor code example, switch to virtual threads
* claim: Jetty/Spring frameworks will use virtual threads as well 
* arg against countdownlatch
* AR concedes it isn't pretty but: sc to the rescue
* arg: sensors can _now_ stream data
    - AR: use Iterable
* back pressure with limited blocking queue
* OD argues that they have re-discovered the need for R
    - full spec, with many impls
    - back pressure
* this one is gold, gold, gold
* harmony: loom + R together
* can be friends
* loom: for simple apps
    - loom can also help R in spots 
* R: for complex, streaming
 
### brainstorm

* Nicolai video
    - parallelism: when splitting up tasks is part of the domain
    - concurrency: serving multiple actors which compete for resources
    - really good table
* find sandwich analogy for continuation
    - also: anesthetic in medicine
    - or teleporter from Star Trek 


