
### intro 

* about me
* project loom banner
* JDK 19
    - Sept 2022
* Virtual Threads JEP
* Structured Concurrency JEP

### part 1.1

* header: virtual threads
* max p threads
* max v threads
* TODO
    - CPU vs sockets vs threads
    - show bottleneck
* TODO
    - server socket example
* TODO
    - throughput (Little's Law?)
* grocery store
* map grocery store to Loom 
* pros
    - debug, monitor, tooling
    - scale
    - no async/await
    - thread per request
* cons
    - scale
    - imperative is back
    - BG will kill reactive
* Brian Goetz

### part 1.2

* header: reactive
* rainbow emoji
* what is reactive
    - not react
* spec
    - Rx Java, Project Reactor, Spring WebFlux
* architecture diagram
    - async
    - resilient
    - message-passing 
    - look at book !
* points
    - FAANG vs MAANA
    - concede
        - debugging, tooling, monitoring
        - # of threads 
    - but
        - mature spec
        - back-pressure
        - composition
        - change propogation
        - # of threads 
            - thread pools are a throttle

### part 2

* header: structured concurrency
* concurrency
    - diagram of threads
    - scenarios: invokeAll, invokeAny, deadline
* structured
    - assembly example
    - structured programming book 
    - code example
    - Knuth considered it controversial ?
* unite: structured concurrency
* go considered harmful
* code example: invokeAll
* code example: invokeAny
* code example: invokeDeadline
* code example: invokeSome(n)

### wrap up

* thank you
