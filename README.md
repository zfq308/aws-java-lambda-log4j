#aws-java-lambda-log4j
Add additional information for Logging
Format is as below:
ApiID:ResourceId:Stage:SessionId:FunctionName:FunctionVersion:LoggingLineNumber:LoggingPriority: Meaningful Message.

## Build Jar package
`gradle build`

## How to use:
* Set your log4j.appender.LAMBDA.layout.conversionPattern as %X{ApiId}:%X{ResourceId}:%X{Stage}:%X{SessionId}:%X{FunctionName}:%X{FunctionVersion}:%c:%L: %-5p %m%n
* Add Jar package to the libs folder
