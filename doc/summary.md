
Project Loom is a major initiative in Java, intended to introduce easy-to-use, high-throughput concurrency support and even new programming models. In this talk, we'll examine two aspects of Project Loom, which will be available (as preview) in JDK 19 (due Sept 2022). 

First, we'll examine "virtual threads". Virtual threads will have an enormous impact on the way we scale code. They are cheap, simple, and they "code like sync, but scale like async". Advocates claim that "imperative code is back!". We will soon see dozens of blog posts with impressive demos. But is the hype overblown? Is it true, as Brian Goetz (language architect) predicts, that "Loom will kill reactive programming"?   

Second, as time allows, we'll look at "structured concurrency": a relatively new way to handle multiple tasks, running in separate threads, as a single unit of work. Structured concurrency illustrates the benefits of virtual threads, and gives us a new, sexy term to "casually drop into conversation" with pretentious academics.    

These are advanced topics, but we'll try and explain them for both intermediate and senior developers. This talk will be successful if _no_ one level of developer is entirely happy with the material. (Bonus: this is indistinguishable from abject failure!). It is recommended that attendees are familiar with threads, and the difference between stack-space and memory heap.

Michael Easter is a software developer and co-organizer of PEI Devs.
