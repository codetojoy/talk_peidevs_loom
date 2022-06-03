
### TODO

* 22 min to get to part 1.B
* drop shadow on images ?
* change battery in trackpad
* virtual thread / carrier thread in diagram
    - tie it back to the code examples
* code example from book for Reactive

### intro 

* about me
* project loom banner
* JDK 19
    - Sept 2022
* Virtual Threads JEP
* Structured Concurrency JEP

### part 1.1

* header: virtual threads
* code: max p threads
* code: max v threads
* architecture: CPU, sockets, RAM
* grocery store
    - set up
    - etc 
* banner redux
    - 4+ years
    - PhD thesis 
* PR github
* revisit code: sync egg_4
* pros
    - scale
    - because JVM: debug, monitor, tooling
    - no async/await
        - no bipolar
        - consistent
    - thread per request
* cons
    - scale
    - imperative is back
    - BG will kill reactive
* 5 million connections
* reddit: loom can't come fast enough
* Brian Goetz

### part 1.2

## brainstorm ##
-----------------------------------

* timeline
* 2005
    - Erik Meijer, Microsoft
    - large-scale async, data-intensive service architectures 
* 2009
    - Rx.NET
* 2012
    - Rx.NET open source 
    - ported to RxJava
* 2013
    - RxJava open source
    - Netflix
* 2011
    - ReactiveX, Reactive Extensions
    - RxJava is JVM impl of this
    - https://en.wikipedia.org/wiki/ReactiveX
    - API for async programming with observable streams
        - observers, subscriptions, operators
    - link: https://reactivex.io/
* 2014
    - Reactive Manifesto 
    - v 2.0 
    - https://www.reactivemanifesto.org/
* 2015
    - Project Reactor v2
    - by Spring
    - v3 is similar to RxJava v2 
* 2017 
    - Spring 5 is Reactive
    - uses Project Reactor
    - Spring WebFlux

-----------------------------------

* header: reactive
* rainbow emoji
* what is reactive
    - not react
    - scalable, resilient
    - "be like water"
    - threads: be dormant or be doing work

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
            - thread constructs?

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
