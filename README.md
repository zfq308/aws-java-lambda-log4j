#aws-java-lambda-log4j
Add additional information for Logging
Format is as below:
ApiID:ResourceId:Stage:SessionId:FunctionName:FunctionVersion:LoggingLineNumber:LoggingPriority: Meaningful Message.

## Build
`gradle build`

## Test
`gradle test`

## How to use:
* Set your log4j.appender.LAMBDA.layout.conversionPattern as 
```
%X{ApiId}:%X{ResourceId}:%X{Stage}:%X{SessionId}:%X{FunctionName}:%X{FunctionVersion}:%c:%L: %-5p %m%n
```
* Add the JitPack repository to your build file
```groovy
repositories {
	...
	maven { url "https://jitpack.io" }
}
```

* Add the dependency(current version is 1.0)
```groovy
 compile 'com.github.VodafoneAustralia:aws-java-lambda-log4j:1.0
```

## How to release new version
After you changed the code, you can create a new tag and create a release for this repository. Then update the dependency with 
```groovy
com.github.VodafoneAustralia:aws-java-lambda-log4j:+ VersionNumber
```