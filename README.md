### Summary

* Notes/examples for a talk on Java's Project Loom, for [PEI Developers](http://peidevs.github.io/), June 2022.
* slides [here](./doc)
    - Note that I don't create slide-decks; I aim to tell a story during the talk. So the slides may not be useful, post-hoc.

### Attribution

* see [Attribution.md](./Attribution.md)

### Post-scripts

* see [PostScripts.md](./PostScripts.md)

### Examples from presentation

* see [src](./src) in this project 

### Modest projects (as more code examples)

* [easter_eggs_for_java_loom](https://github.com/codetojoy/easter_eggs_for_java_loom) 
* [WarO](https://github.com/codetojoy/WarO_Java_19) 

### Resources

* JEP 425 [Virtual Threads](https://openjdk.java.net/jeps/425)
* JEP 428 [Structured Concurrency](https://openjdk.java.net/jeps/428)
* major [PR on GitHub](https://github.com/openjdk/jdk/pull/8166/files)
    - 1100 files!
* [Project Loom C5M](https://github.com/ebarlas/project-loom-c5m)
    - 5 million concurrent connections
* [Loom can't come fast enough](https://www.reddit.com/r/java/comments/kxie9p/loom_cant_come_fast_enough/)
    - reddit thread

### Resources / Videos

* [Java 19 Virtual Threads - JEP Café](https://www.youtube.com/watch?v=lKSSBvRDmTg)
* [Project Loom: Modern Scalable Concurrency for the Java Platform](https://www.youtube.com/watch?v=EO9oMiL1fFo) by Ron Pressler
    - 12m00s : "codes like sync, scales like async"
    - 12m34s : excellent discussion on Thread vs async/await in various languages
    - 30m44s : great slides illustrating 1:1 versus M:N
    - 37m34s : Little's Law
* [Project Loom: Revolution in concurrency or obscure implementation detail?](https://www.youtube.com/watch?v=n_XRUljffu0) by Tomasz Nurkiewicz 
* [Project Loom - A Friend or Foe of Reactive?](https://www.youtube.com/watch?v=YwG04UZP2a0) by Oleh Dokuka and Andrii Rodionov
    - esp. near 19m20s
* [AMA About the Java Language](https://www.youtube.com/watch?v=9si7gK94gLo) by Brian Goetz and Nicolai Parlog 
    - near 19m10s, "Loom will kill Reactive programming"
    - reddit thread [here](https://www.reddit.com/r/programming/comments/oxsnqg/brian_goetz_i_think_project_loom_is_going_to_kill/)
* [Project Loom Q&A with Ron Pressler](https://www.youtube.com/watch?v=cAHW96omBAc) 
    - esp. near 55m25s 
