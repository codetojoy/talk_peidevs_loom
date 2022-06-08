
### Post-Scripts

Here are some bonus notes that are not included in the talk:

* as of JUN 2022, virtual threads are *not* swapped out in code blocks protected by `synchronized`
    - see 11m18s [here](https://www.youtube.com/watch?v=lKSSBvRDmTg)
    - such code could be changed to use `ReentrantLock`

