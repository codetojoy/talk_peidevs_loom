
### reactive

* Spring Async
* Spring Webflux
* Vert.x
* RxJava
* Reactor 

### questions

* is a carrier thread the same as a platform thread?
    - yes

### resources

* Ron Pressler - https://inside.java/u/RonPressler/
* github - https://github.com/openjdk/loom
* JEP 425
* mailing list - https://mail.openjdk.java.net/pipermail/loom-dev/
* HN thread - https://news.ycombinator.com/item?id=31214253
* function coloring essay
    - Jekyl/Hyde of async/await
* 09-MAY-2022
    - structured task scope
        - https://download.java.net/java/early_access/loom/docs/api/jdk.incubator.concurrent/jdk/incubator/concurrent/StructuredTaskScope.html

-----------------

### reactive section

* article
* https://www.baeldung.com/java-reactive-systems
* META: fantastic article
* Reactive Manifesto by Jonas Boner in 2013
* receipe for system that is reactive, loosely coupled, scalable
* responsive, resilient, elastic, message-driven
* r := reactive
* r programming vs r systems
* r programming
    - async, non-blocking components
    - data stream as observable with back pressure
    - non-blocking and *fewer threads* of execution
* r programming is building block for r systems
* r streams is a standard for async stream processing with back-pressure
    - Akka streams, Rat pack, Vert.x
    - known as Java Flow library in JDK 9 
* in Java
    - RxJava is ReactiveX or Reactive Extensions
    - Project Reactor
        - used in Spring for reactive
* strawman architecture example
    - 3 ms
    - REST Template
    - Spring Web
* order ms orchestrates shipping ms and inventory ms
* example introduces distributed transactions, which are nasty 
    - presumably solved with message passing ? 
* ng front-end with reassuring references to RxJS etc
* deploy example with Docker Compose
* problems
    - failure can cascade
    - blocking calls
    - deployment is clunky
* 5 reactive programming
* we don't want to tie up threads with blocking 
    - perfect for Loom, right in the wheelhouse
* Spring Webflux
    - used in servant ms
    - non-blocking, back pressure
    - uses Reactor, Reactor Netty
    - web client
* Spring Data Mongo Reactive
    - used to interface with Mongo DB
* updates the example
* notes that it can be hard to debug
    - stack trace, tooling
* APIs now stream events as they occur
* front-end can accept this with EventSource
* 6 message-driven arch
* service-to-service communication now uses message broker
* Kafka with Spring glue
* order service listens for messages
    - init-success
    - inventory-success
    - shipping-failure
    - etc
* example is now much simpler but:
    - eventual consistency
    - complex debugging, monitoring
* Kubernetes over Docker Compose
* 8 reactive system
* 
* github repo: https://github.com/eugenp/tutorials/tree/master/reactive-systems 

* website
* Project Reactor
* non-blocking apps on the JVM
* 4th gen impl of Reactive Streams spec
* used in Spring Boot and WebFlux, others
* uses Netty
* non-blocking and backpressure-ready 

* video
* Project Reactor
* https://www.youtube.com/watch?v=O26jhgk682Q
* Daily Code Buffer
    - annoying diction but good content
* trad arch: blocking calls with a thread pool 
* gold: not feasible to maintain million threads 
    - blocking code leads to inefficient threads
* we want parallel calls to resources
* r programming
    - async, non-blocking
    - event/message driven
    - functional code style
    - back pressure on streams 
* MEGA: database emits data
    - so server is listening for events from DB
    - likewise, client is listening for events from server
    - this is the key to async 
    - must be glue code on the database (e.g. Spring Reactive Mongo Data)
* no blocking calls: so threads are freed
* back pressure (!)
    - use-case: app queries DB, result set has many records
    - DB could overwhelm the app, so we need to tell the DB to chill out  
    - app sends `request(3)` even though there is N
    - DB will honour request and send `onComplete()` for 3 recs
* when to use r 
* high load 
* r arch
    - Netty as non-blocking server
    - Project Reactor 
* r streams spec interfaces:
* publisher
    - e.g. database
    - `subscribe()` method
* subscriber
    - methods: onSubscribe, onNext, onError, onComplete
* subscription
    - connects the pub and sub
    - methods: request and cancel
* processor
    - extends both pub and sub: both
* project reactor
* implementation of r stream spec
* r library
* used by Spring Webflux 
* two types
    - Flux
        - 0 to N elements
        - good diagram of time flow with operators
    - Mono
        - 0 to 1 elements
        - similar diagram
* project setup
* https://start.spring.io
* consider duplicating this ? 
* good examples
* very intuitive, given all the work with RxJS, ng, React, etc
* skip junit
* operators
    - presumably the semantics will mirror RxJS ?
* map ... Flux<String> -> Flux<String>
* filter
* flatmap ... each element -> Flux<T>
    - e.g. "mango" -> Flux<String>[m, a, n, g, o]
    - for Mono: Mono<String> -> Mono<List<String>>
    - when async, order is not preserved
* concatMap preserves order, even if async
* skipping as I've got the gist

* article
* https://www.baeldung.com/spring-webflux-backpressure
* server can push data as it becomes available
* backpressure: emitters overwhelm consumers
    - term references measures to mitigate this
* publisher, consumer, front-end
* assume pub is 10k/s, consumer is 7.5k/s
    - unrestrained, this is bad news
* options
    - publisher slows down
    - consumer buffers
    - drop events/messages
    - all are dicey
* 3 strategies to control the publisher
    - only send events when subscriber requests them
    - send N events on request
    - cancel stream when consumer is overwhelmed
* Spring Webflux
* uses Project Reactor
* uses TCP flow control 
    - weird section
* it seems like SWF or internals are buffering ?
* 4 impl backpressure with SWF
* dependencies
* option: requestNext(N)
* option: limitRange operator in Project Reactor  
* option: cancel
* github: https://github.com/eugenp/tutorials/tree/master/spring-5-reactive-2

* video
* Reactive Spring
* JUG.ru, Oct 2021
* https://www.youtube.com/watch?v=aM-l68NqguA 
* Josh Long, @starbuxman
* has book on same topic
* Spring Tips, Bootiful podcast, blog Tuesday
* r := Reactive
* ms := microservice
* microservices
    - faster productivity 
    - API is easy: domain model, bounded context is tough
    - ref Domain Driven Design
* use-cases
    - a: resource efficiency
        - blocking threads (gold!)
        - reference to Project Loom
        - expensive to create threads
        - traditional: thread pool
    - b: composition
        - various streams
        - different APIs
        - not Java collection: integration/glue code for hetergeneous services makes things tough
        - ms gets a bad rep due to integration complexity 
    - c: network latency/failures
* Loom helps with a but not b, c (and back pressure)
* R helps with all of the above
* Spring Boot
* https://start.spring.io
* JDK 11, Gradle, Java, Lombok
* very good code examples 
* Flux, Mono
* 4 interfaces: pub, sub, subscription, processor
* back pressure == flow control 
* Spring Reactive uses Project Reactor impls Reactive Streams spec
* won't annotate code examples... good stuff, though frenetic at 1.5x
* non blocking: no waiting as the scheduler is swapping out threads etc
* goal: keep CPU busy all the time, not waiting
    - reduce cost for data centre budget
* Spring Boot Actuator for observability
* example has customer service and now order service
* RSocket
    - binary protocol
    - better than HTTP 
    - many pub patterns
    - presumably used for ms-to-ms comm
* order service has getOrders(customerId) endpoint 
* build packs
    - used for deploying as container
    - maybe Graal VM ?
* `rsc` is like curl for RSocket
* API gateway
* skipping the demo as it is starting to lag
* Q: combine R with non-R in a project 
    - key: avoid blocking main thread
    - do it on a different scheduler
* Q: Kotlin co-routines
    - spring integrates 
* [1] -  https://en.wikipedia.org/wiki/RSocket


















