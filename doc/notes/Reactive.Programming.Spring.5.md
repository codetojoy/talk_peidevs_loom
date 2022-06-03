
### summary

* on O'Reilly
* Hands-On Reactive Programming in Spring 5
* Oleh Dokuka, Igor L
* Packt
* generally, very good, esp. at early motivation

### symbols

* r := Reactive
* comm := communication
* ms := microservice

### ch 1 

* why r spring
* define r
* small business analogy
* traditional arch
    - basic server in Tomcat 
    - 1000 users per hour
    - thread pool of 500
    - avg response time: 250ms
    - req/sec: ~2000
* black friday
    - huge spike
    - fail in prod, as arch had a low ceiling for capacity
* goal: reactive to changes in demand/load, and external services' availablity
    - be like water re: rock/pond
* aspect: elasticity
    - scalability
* aspect: resilience
* Spring 4 REST template as a flawed approach
    - req-resp comm
* timeline
    - thread is blocked on IO
    - majority of the time is blocking the thread
* note about C#, Go, Kotlin re: green threads
* message comm is more efficient: async, non-blocking
* each service awaits arrival of messages, reacts to them
    - can be dormant, or do other work
    - location transparency
    - impl transparency
* message broker 
* diagram
* r manifesto
* return to simple web app
* ms
* api gateway
* service registry
* message comm: apache kafka
* streaming arch
* processing stages
* low latency, high throughput
* back pressure: mechanism to prevent overwhelming a service 

### ch 2

* why r Spring? (in particular)
* motivation for Spring 5
* options: akka, vert.x
* akka w/ java has not gained traction (as scala did)
* vert.x is analogous to Node JS for JVM 
* spring cloud
* order service, shopping card service
* imperative approach
* callback approach
* CompletionStage, CompletableFuture similar to Promise api
    - composition
    - Spring 4 didn't really support this, for compatibility reasons
    - ultimately used Servlet API which is thread-per-request
* multi-threading
    - conceptually, practically difficult
    - context-switching is expensive
        - as we know, huge, generic stack size
        - OS level
* Spring 4 is not r ; doesn't leverage, say, Netty
* chaining transforms in pipeline
* r programming is a good fit (!) for r systems
* Spring 4 was losing mindshare, as r soared
* so Spring 5 is a major tack in the direction of r

### ch 3

* basic concepts
* RxJava concepts for r programming
* Spring 4.x had ListenableFuture which is not the same as Java 8's CompletableFuture   
* Observer from GoF is a huge foundation
* subject publishes changes to observers
* MVC is based on this as well
* e.g. mailing list for newsletter
* one-to-many dependencies, usually one-way traffic
* code example
    - doesn't seem to match RxJS's nomenclature ?
* junit example
* beating the drum: thread is 1 MB, and can exhaust the JVM with a few thousand 
* Pub-Sub pattern is variation on Observer
    - intermediate entity: the channel/topic
    - channel can do filtering, routing, etc
* r design excludes pulling data (or polling)
* web sockets and server-sent events (SSE)
    - HTML5 JS api has EventSource: stream that auto reconnects
    - both used in the book
* Spring Web, MVC
* Gradle!
* start.spring.io
* no Spring 5 yet   
* temp sensor -> event publisher -> REST controller -> front-end 
* Servlet 3.0 async support
    - process HTTP request in non-container thread
    - useful for long-running tasks
    - controller can return Callable<T>
    - DeferredResult<T> is async, Spring's version is ResponseBodyEmitter, SseEmitter
    - SseEmitter sends multiple results for one request
* code example
* TemperatureController is a revelation
* the whole example is amazing
* motivation concerns
    - Spring 4.x mechanism not intended for high load
    - internal Spring mechanism being re-purposed for key business logic
    - errors, ending streams is not elegant
    - manual thread pool
* RxJava
* peers: Akka, Project Reactor   
* RxJava has 1.x and 2.x and now 3.x 
* RxJava is version of Reactive Extensions (Reactive X)
    - observer, iterator, functional programming
    - this aligns the types to closer to RxJS, I think
* subject == observable in RxJava 
* subscriber is observer
* Observables can be combined (with operators, of course) 
* Subscription (the channel ?) 
* operators
    - marble diagrams
    - included in doc, as they are so effective
* map 
* filter
* count
* zip
* example: search query with limit 
    - motivates reactive
    - time to first byte
* subscribeOn operator
    - uses scheduler
    - similar to giving thread to executor
* rebuild temp sensor with RxJava
* TempSensor
    - returns Observable<Temperature>
    - interesting pipeline, including concatMap
    - with inner observables, major factor is when subscribe happens
    - e.g. concatMap doesn't subscribe until previous value completes
    - e.g. concatMap vs mergeMap
    - publish operator is used so that new subscriptions aren't created
        - i.e. publish to all clients with connected observable
    - refCount creates subscription only if anyone is listening
* RxSeeEmitter
    - mostly error handling wrapper
* TempController
* example is really nice, well motivated  
    - several points of improvement over Spring 4.x pub-sub method
* r programming: active subscription
* history of r libs
* Rx came from Microsoft
    - Rx.NET in 2009 
    - open source in 2012
* RxJava in 2013
* Netflix was huge user of Rx, RxJava
    - RxNetty, r adapter for Netty
* RxAndroid, RxSwift
* NodeJS considered a major step in r design/impl
* others
    - Ratpack
    - Retrofit (android)
    - Vert.x

* r streams
* various motivating topics
* API consistency: Java Core vs RxJava leads to adapters, boilerplate
* multiple libs might use different 1.x of RxJava: jar hell 
* push vs pull
    - possibly N+1 issue?
    - various diagrams
    - key is to have an ongoing stream that can be cancelled 
* flow control problem
    - if producer slams the consumer, not good
    - motivating back pressure
* queue options 
    - unbounded queue
        - hurts resiliency because nothing is unbounded
    - bounded drop queue
        - drop messages
    - bounded blocking queue
        - breaks async, violates the Big 3 principles (resilience, elasticity, resp.)
* pure push model has undesired situations
* RxJava 1.x has no facility for back pressure
* r manifesto is explicit about back pressure
* ? added in RxJava 2.x ?
* solution: r Streams spec
    - 2013
    - Lightbend, Netflix, Pivotal
* r Streams spec
* 4 interfaces: Pub, Sub, Subscription, Processor
* analogies to Observable, etc
* key point is `request(int n)` on Subscription
    - this is back pressure
    - hybrid push-pull model
    - max-value for `n` is push model
    - pull model can be achieved with `n` as 1
* extended example re: news service
* Processor
    - both a start/end point of stream (Pub, Sub)
    - allows transforms, caching, custom operators, etc 
* r streams tech compatiblity kit (TCK)
* apart from 4 interfaces, there is much doc/spec/rules on behaviour
* TCK by Konrad M: a way to test an impl for the rules 
    - not just for libs
    - if we write a Publisher, we can test/verify it
* JDK 9
    - Flow API
* java.util.concurrent.Flow classes has interfaces for r streams spec
    - bummer: already classes in `org.reactivestreams.*` package
    - adapters
* rules: non-blocking 
    - rules prohibit parallel publishers
    - external co-ordiation?
* pass async messages between stages
* RxJava v 2.x
* David Karnok
* Observable becomes Flowable 
* Vert.x and others adopted the r streams spec
* example with multiple tech combined: composability
    - Ratpack: web server
    - ds 1: mongo
    - ds 2: external news service, using RxNetty as client
    - key is composing independent frameworks, libraries
    - end user 'sees' one stream/processing flow

### ch 4

* Project Reactor (aka Reactor)
* most famous lib in r ecosystem 
* initiated by Spring team (!?)
* Reactor 1.x was good, but issues:
    - no back pressure
    - error handling
    - used in Grails  
* Reactor 2.x in 2015
* Stephane Maldini
* fully compliant with r streams spec
* major update
* Reactor 3.x very similar to RxJava 2.x
    - huge impact on Spring 5
* theme: no callback hell, avoid nested code
* assembly line for data processing:
    - Reactor is both the belt and the work-stations (operators)
    - efficient, even with faulty IO
    - back pressure
        - data: pub -> sub
        - demand control info: sub -> pub
        - push, pull, hybrid models
    - also old style back pressure options
* Publisher<T> interface
    - two types: Flux<T>, Mono<T>
* Flux is infinite, but can be cancelled
* Mono<T> is similar to CompletableFuture<T>
* Mono<Void> is useful for `onComplete()` and `onError()` signals
* compares Flux, Mono to types in RxJava 2
    - Flowable is Flux
    - Single sorta Mono but more CompletableFuture
    - Maybe is Mono
    - Completable is Mono<Void>
    - ugh
* subscription options for Flux/Mono
* custom subscriber
* see resources for doc on operators
* various: map, filter, collect
* combine
    - concat: send stream 1, then stream 2
    - merge: subs to stream 1 and stream 2 eagerly
    - zip: subs to s1, s2 and when both emit, send e1-e2 combo
    - combineLatest: emits when any stream emits (?) 
* batching operators
* flatMap
    - eager sub to inner stream?
    - preserves order?
    - interleaving
    - flatMapSequential, concatMap are variants
* sample operator
* blocking operators (uncommon)
* peeking
* materialize/de-m
* creating streams
* push
    - async, single-thread, multi-val API
    - no back-p, no cancel 
* generate
    - internal state for generation
* wrapping disposable resources
* `using` operator
* `usingWhen` 
* handling errors
* back pressure options
* hot vs cold streams
    - hot starts publishing right away, regardless of subs
    - can transform cold into hot
    - ConnectableFlux
* caching
* transform, compose operators
* processors are both pub, sub
    - considered hard to use
    - other methods are more appropriate
    - let's skip
* reactive "not so easy to debug"
* various add-ons
* Advanced Project Reactor
    - skipping

### ch 5 

* r with Spring Boot 2
* Project Reactor is necessary but not sufficient
* Spring has DI, and all the goodies not related to r
* Spring has too many config options
* Spring Roo, circa 2009, but never took off
* Spring Boot, circa 2013
* two central modules
    - spring-boot
    - spring-boot-autoconfigure
    - *-starter-abc, which doesn't have code but curated set of jars
* spring core 
    - supports RxJava, Project Reactor
* r I/O
    - abstraction over byte buffer: DataBuffer
    - codecs
* WebFlux
    - uses Reactor 3 as first-class
    - good diagram to illustrate vs Spring MVC
* Spring Data
    - abstracts data access with Repository pattern
    - new: ReactiveCrudRepository
    - "soon" : reactive JDBC
* Spring Session
    - e.g. distributed web session with Redis 
* Spring Security
    - formerly used ThreadLocal
    - now uses Flux/Mono
* Spring Cloud
    - formerly, only option for gateway: Spring Cloud Netflix Zuul
    - but now r-enabled
    - support for FaaS
* test, monitoring support

### ch 6

* WebFlux async, non-blocking
* Spring was originally tightly-coupled to the servlet spec
* sync, blocking
* WebMVC does not 100% support non-blocking Servlet API 3.1  
* example interfaces for r
* various diagrams for servlet stack: very good
* ServerWebExchange: session info
* routing example
    - more r-oriented
    - various benefits, including start-up times
    - feature match for competitors
    - key for FaaS, microservice, etc
* WebClient replaces venerable RestTemplate
* web sockets
    - HTML 5 sockets
    - WebFlux sockets vs Spring sockets
* template engine
    - FreeMarker is last one standing, by default
* r security
    - HALT 11-MAY-2022

### brainstorm

* mentions FFANG scale... will they concede that this is overkill in some cases?
* they do concede that it seems difficult compared to imperative
* it is clear that RxJS and RxJava are related, re: spec
* re: Loom
    - good stuff to understand "there's more to r systems than thread pools"
    - composability
    - back pressure

### resources

* Tomasz Nurkiewicz is reviewer ! From Project Loom contrarian video 
* https://github.com/PacktPublishing/Hands-On-Reactive-Programming-in-Spring-5
* !! -- https://rxmarbles.com/
    - very subtle design ... e.g. implicit interruption for switchMap
* https://reactivex.io/languages.html
* Project Reactor operators - https://projectreactor.io/docs/core/release/reference/#which-operator
