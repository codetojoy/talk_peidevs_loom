
Summary:
---------

* this egg illustrates structured concurrency
* task "foo" and task "bar" take too long and exceed a deadline
    - both are interrupted

To Build:
---------

* tested with JDK 19.ea.26-open via [SDKMan!](https://sdkman.io/)
    - this is a special build for Project Loom, *not* the JDK 19 preview
* Gradle does not yet support JDK 19 preview (as of MAY 2022). Check [here](https://docs.gradle.org/current/userguide/compatibility.html)
* unknown if Maven can be used ¯\_(ツ)_/¯

useful commands:

* `sdk env`
    - SDKMan! will set JDK to value in `.sdkmanrc`
* `./clean.sh`
* `./compile.sh`
* `./run.sh`
* note `./go.sh` does: clean, compile, run 
