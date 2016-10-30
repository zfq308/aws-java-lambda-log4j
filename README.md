# aws-java-lambda-log4j
Add additional information for Logging
Format is as below:
```
ApiID:ResourceId:Stage:SessionId:FunctionName:FunctionVersion:LoggingLineNumber:LoggingPriority: Meaningful Message.
```

Pass session information between API's

## Build
`gradle clean build`

## Test
`gradle test`

## How to use:
### Logging
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
### Session and Cookie
`APIRequest Class` can be used to add Session Id and Cookie in request header and pass these
information to lambda function. Lambda function can also use `APIResponse Class` for setting cookie.

#### Gotchas
* Valid Session Id is a 8 upcase alphabets String.
* Cookie should be format as `name=value`. Support multiple cookies, and the format should be `name1=value1;name2=value2...`

#### Example:
GetPets is a GET API to get json response of a list of pet
* In `Method Request`, add `SessionId` and `Cookie` to `HTTP Request Headers`;
* In `Integration Request`, add below `Body Mapping Templates`:
```groovy
	{
    	"apiId" : "$context.apiId",
    	"resourceId" : "$context.resourceId",
    	"stage" : "$context.stage",
    	"sessionId" : "$input.params('SessionId')",
    	"rawCookie" : "$input.params().header.Cookie"
	}
```
* In `Method Response`, add `Set-Cookie` to `Response Headers`
* In `Integration Response`, add `Set-Cookie` to `Header Mappings` and set the value as `integration.response.body.apiResponse.rawCookie`.
And add below `Body Mapping Templates`:
```groovy
	{
    	"pets" : $input.json('$.pets')
	}
```

## How to release new version
After you changed the code, you can create a new tag and create a release for this repository. Then update the dependency with 
```groovy
com.github.VodafoneAustralia:aws-java-lambda-log4j:+ VersionNumber
```