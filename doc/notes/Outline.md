
### daily checks

- https://inside.java/tag/loom
- reddit
    - ron pressler
    - reddit.com/u/pron98
- hacker news
- twitter
    - ron pressler
    - https://twitter.com/pressron

### talk brainstorm

- definitions
    - parallel vs concurrency
- goal
    - beat Node JS (not just dot net) for throughput
- questions
    - when do we get it?
    - async/await
    - Kotlin
    - thread local
        - new thread for each task - https://inside.java/2022/05/16/quality-heads-up/
        - will require work
- what to gloss over?
    - continuations
    - cores?
    - structured concurrency
- standard "create N threads that sleep"
- grocery lanes as analogy?
    - Venkat has waiter example
    - scheduler as supervisor
    - kernal thread is another cashier
        - physically swapping out
        - pallet jack
    - what is a virtual thread: an avatar?
    - maybe cart size is stack (OS vs v-thread)
        - small cart vs full cart vs "pallet jack"
    - the waiting zone is the heap
    - continuations as complete state
    - blocking operations: price check, partner goes to car to get wallet
    - physical movement is kernal mode; magic wand is user mode 
- understand Go, Kotlin etc
- understand async/await
- solid diagrams
- Lambda Loom logo
- understand Little's Law
- how much code?
- wasn't there an analogy of lines re: parallel and concurrency?
    - maybe find that 

### talk outline

* Loom 
    - thread per request
    - # of threads can match # of sockets
    - no thread pools
    - tooling
    - debugging
    - structured concurrency
* Reactive
    - thread efficiency is just part of it
    - back pressure
    - composition
    - thread pool _is_ a throttle constraint, in a way 
        - Loomers will need to write their own with semaphores, phasers, etc
    - concedes:
        - debugging
        - simplicity
        - scales well but perhaps too much for smaller projects?
            - good fit for "data-intensive" systems
    - async concedes:
        - coloured functions: hard to combine async with sync

[1] - image with lambda loom
    - https://cr.openjdk.java.net/~jrose/pres/201907-LoomBlanket/loom-blanket.html

