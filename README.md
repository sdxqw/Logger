# Logger
Simple Logger

# Use this
add jitpack
```
repositories {
	maven { url 'https://jitpack.io' }
}
```
then add the dependencie on dependencies
```
dependencies {
	implementation 'io.github.sdxqw:Logger:master-SNAPSHOT'
}
```
# Usage
```java
Logger.info("My log here");
Logger.info("Info log %s", "with format");
```