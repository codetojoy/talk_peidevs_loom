
Summary:
---------

* illustrates virtual executor service with semaphore-protected "database"
* Java 19 (preview) virtual threads

To Build:
---------

* tested with JDK 19.ea.26-open via [SDKMan!](https://sdkman.io/)
* Gradle does not yet support JDK 19 preview (as of 01-JUN-2022). Check [here](https://docs.gradle.org/current/userguide/compatibility.html)
* unknown if Maven can be used ¯\_(ツ)_/¯

useful commands:

* `sdk env`
    - SDKMan! will set JDK to value in `.sdkmanrc`
* `./clean.sh`
* `./compile.sh`
* `./run.sh`
* note `./go.sh` is handy for clean-compile-run
