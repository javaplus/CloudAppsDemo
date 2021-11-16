# Introduction to Cloud Development Mindset by going Hands On.


# Prerequisites:
1. Admin Rights on your machine to install and configure software.
1. Git Client installed. [https://git-scm.com/](https://git-scm.com/)
1. Java development Kit (JDK 11) How to setup Java and Maven on Windows Video: 
	- [Adopt Open JDK (11 LTS)](https://adoptopenjdk.net/)
1. Maven 3.x - [Download Maven here](https://maven.apache.org/download.cgi) - Choose **Binary zip archive - apache-maven-3.6.3-bin.zip**
1. An IDE or Text Editor. We recommend [Visual Studio Code](https://code.visualstudio.com/)
      -  If using Visual Studio Code, the following extensions are recommended when developing a Spring Boot microservice(To Install, open VS Code and click on the Extensions button on the left and search for the following):
        
		* Java IDE Pack
		* Java Extension Pack
   		* Maven for Java
		* Lombok Annotations Support
		* Spring Boot Dashboard
		* Spring Boot Tools
		* Checkstyle for Java
	
      -  Alternatively, you can install these extensions using the following commands at your favorite command prompt (works on Windows and Mac):
      
	```shell
	code --install-extension vscjava.vscode-java-pack
	code --install-extension vscjava.vscode-maven
	code --install-extension pverest.java-ide-pack
	code --install-extension gabrielbb.vscode-lombok
	code --install-extension vscjava.vscode-spring-boot-dashboard
	code --install-extension pivotal.vscode-spring-boot
	code --install-extension shengchen.vscode-checkstyle
    ```


# GOAL

The goal of this training is to get an understanding of standard developer tools and development practices while highlighting patterns that improve quality and successful delivery.  The idea is not to teach you a programming language, but to get you some familiarity with the tools and techniques along with the mindset involved in software development and delivery.
We will also be mindful of what goes into creating Cloud Ready applications.  So, throughout this training we will refer to the [Twelve-Factor App](https://12factor.net/) methodology.

# The Scenario

 The core of the app is already started and some functionality exists. You will be making some minor tweaks to the configuration and code of the app. (Don't worry you don't have to know how to write the code, you will copy and paste or just uncomment blocks to make the code work.)  The key is that we will be trying to follow good development practices and make sure our app is cloud ready by adhering to the [Twelve-Factor Apps](https://12factor.net) guidelines.

## Twelve Factor Apps Methodology

The Twelve-Factor App methodology was drafted by developers at [Heroku](https://en.wikipedia.org/wiki/Heroku) which was one of the first cloud platforms.  This methodology was developed from their collective experience of deploying hundreds of apps to the cloud. The Twelve-Factor App methodology puts forth best practices designed to enable applications to be built with portability and resilience.  Let's take a look at the introduction now! [Twelve-Factor Apps](https://12factor.net)


# Setup Workspace

## Clone the git repo.

Make sure you have the prerequisites installed above.  Clone the repo containing the code (which is this repo) to get the code local.  

```
git clone https://github.com/javaplus/CloudAppsDemo.git
```

## Open Workspace

Open the code in your IDE (in our case it's VS CODE).  An easy way to open it, is from the command line where you ran the git clone, go into the directory that git created to clone the repo into and run code from there:
```
cd java-tool-training
code .
```
NOTE: That there's a period '.' after the "code" command to tell code to open the current directory in VS Code.There are other ways to open VS Code and point it to this directory as well. 
Running the "code ." command should open VS Code with the files and folders of the current directory visible like the image below.  :

![Start VSCODE](/images/StartVSCode.PNG)

Now you have a good way to view the files that make up the app.

## Understanding the App

This app has some working classes that expose APIs and then more classes that will be templates for you to work with.

You'll notice there are NOT multiple projects, but instead just one project. With the move to microservices, we are trying to move to much smaller code bases for a single deployable application.  So, there is just the single application with a much fewer number of folders and classes compared to a traditional monolith which could have 3 to 10 projects and close to a thousand classes with thousands of lines of code in total. 

The first guideline for 12 factor apps is to have one app in one repo (see [Codebase](https://12factor.net/codebase)). So there's one codebase or repo for the app.  If your app is spread across multiple repos it would greatly complicate the maintenance as well as the build and release process. But also, by having smaller sized applications, the repos are much easier to navigate, maintain, and understand which can greatly reduce the introduction of bugs and increase the speed to market for new changes.

[Spring Boot](https://spring.io/projects/spring-boot) makes it easy to create stand-alone java applications that take advantage of a popular set of Java libraries called Spring.

This simple SpringBoot app was initially created by using the [Spring Initializr](https://start.spring.io/).  Spring Initializr is an online tool to quickly help initialize your SpringBoot application by generating a starter project with the dependencies/libraries you need.

  ![Spring Initializer](/images/StartSpringIO.PNG)

## Building a Spring Boot App

One of the main things the Spring Initializr creates for you when generating a SpringBoot app is a file to describe the dependencies and how to build the app.  This helps us follow the ["Dependencies"](https://12factor.net/dependencies) directive put forth by the 12 factor methodology that indicates that an app should "never rely on implicit existence of system-wide packages".  But should instead "declare all dependencies, completely and exactly, via a dependency declaration manifest".

Our Java applications do this by using [Maven](http://maven.apache.org/).  At the heart of Maven is the **pom.xml** file.  A pom file is a "Project Object Model" definition.  It is our "dependency declaration manifest" as well as configuration for building and testing the app.

Let's take a quick look at our **pom.xml** file now.

To be honest there's not much to look at here... later on we'll dig a little deeper and see where the magic happens.

## Building and Running the App.


## Build the Application


Maven helps us also implement the Build part of the ["Build, release, run"](https://12factor.net/build-release-run) guideline of 12 factor apps.  

According to this guideline the ***"build stage is a transform which converts a code repo into an executable bundle known as a build. Using a version of the code at a commit specified by the deployment process, the build stage fetches vendors dependencies and compiles binaries and assets."***

Maven helps us by handling the configuration for our Build Phase.


The pom.xml file not only declares are library dependencies, but also tells it how to build our application.

To build and package the app, we will run the maven command from the same location as the pom.xml file which is at the root of this project.  When running maven, you use the **mvn** command plus a [life cycle phase](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

We will start by just specifying the **package** phase.  Execute the following command. (Again be sure to do this from the same location as the pom.xml)

``` 
mvn package

```

The **mvn package** command causes maven to run through the default lifecycle phases up to and including package.  This means it will run the **validate**, **compile**, & **test** phases before it runs the **package** phase.  
The **package** phase takes the compiled code and packages it in its distributable format, such as a JAR, WAR, or EAR.  It's a JAR in our case.

You should see a **"BUILD SUCCESS"** info message if everything was successful.

## Running the App

Once the maven package command was run, you should have  an [executable jar](https://docs.oracle.com/javase/tutorial/deployment/jar/run.html) in the **target** folder.

This executable jar is a special Spring Boot built [executable *fat* jar](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-executable-jar-format.html) that will contain all the jars required to run the app packaged into the single jar.  We'll look more at what all goes into this jar later.

To run the application go to the command prompt and run java passing in the jar as an argument and specify the path to the jar we just created with our mvn package command:
**NOTE:** By default, Spring Boot using port 8080 to run.  So, be sure that port is free.

```
java -jar ./target/base-app-0.0.1-SNAPSHOT.jar
```
This should start the application and you should see a message that ends with "Started BaseAppApplication in \<some time\>" 

## Testing the Application

To test the application you can either open this link [http://localhost:8080/hello](http://localhost:8080/hello) in a browser or curl the url like this:

```
curl http://localhost:8080/hello
```
Either way, you should see the output
```
Hello From Your Super Duper Spring Boot app!
```

## Deeper Look at Maven

We've built our app with maven and ran it from the command line and it just works.  If we look at the pom file it's fairly sparse.  That's because there's a reference to a parent pom which is actually hiding a lot of the configuration that is being done.

We can actually find this parent pom on your local machine.  That's because when Maven runs it will pull down all the referenced dependencies to a local repository on your local machine.  You probably noticed the first time you ran the **mvn package** command it did a lot of downloading.  That was Maven downloading all the dependencies, plugins, and parent poms that where referenced by your pom.  So, if you run **mvn package** again (go ahead, I'll wait), you should see it run a lot faster and with less downloads.  This is because Maven will check its local repository before trying to download it from the internet (or our internal Artifactory repository).

Let's look at where maven stores these files.  By default it is under your \<User Home>/.m2/repository folder.


![Maven Local Repo](/images/mvnlocalrepo.PNG)

If you look in the repository folder, you will find many nested folders that match the **groupId** and **artifactId** in your pom file.

If you look at the Parent section at the top of the pom.xml, you will find a section like this:

```
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.5.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

```
Notice the **groupId** of **org.springframework.boot** and the **artifactId** of **spring-boot-starter-parent**.
The groupID and the artifactID along with the **version** make up the [Maven coordinates](https://maven.apache.org/pom.html#Maven_Coordinates) of this parent pom.  We can use them to find our Parent pom file in the local repository.  Because according to Maven's website:

```
"When stored within a repository, the group acts much like the Java packaging structure does in an operating system. The dots are replaced by OS specific directory separators (such as '/' in Unix) which becomes a relative directory structure from the base repository. In the example given, the org.codehaus.mojo group lives within the directory $M2_REPO/org/codehaus/mojo"
```

So, we can use the parent pom's groupId to find the folder that contains parent pom.

![Parent Pom](/images/parentpomlocation.PNG)

If you open this Parent pom file, you'll see a lot more configuration and even a reference to yet another parent pom.  We don't need to understand all that it's doing.  The point is to understand that there's a local repository that Maven uses to cache artifacts (poms, jars, etc...) to make future maven commands run much quicker.  Also, you can see that pom's can inherit from other poms.  This makes it easy to share a standard set of configurations across multiple similar projects if the case warrants it.

## Where Do Maven Artifacts Come From

You may be asking where did all the artifacts that are in my local repository come from.  There is a common repository on the internet that contains a large number of commonly used libraries that is referred to as Maven Central.  You can search for artifacts in Maven Central by going to [https://search.maven.org/](https://search.maven.org/).

Let's go there and search for the **spring-boot-starter-parent** to see if we are running the latest version.  It's important to be aware of versions you are on and how to update them.

When you search for the artifact **spring-boot-starter-parent**, be sure to find the one that is under the **groupId** of **org.springframework.boot**. 

![Search Maven](/images/searchMaven.PNG)

You'll notice that there's a newer one (depending on when you are going through this it may be even newer than the one in the picture above).  In the screenshot above, you'll see a 2.3.0.RELEASE version that was updated in May 15th of 2020.  Let's upgrade ours to that version in a bit.  However, before we do that, let's learn how we can see all our current versions and then see how the change to updating the parent will affect that.

So, to see the list of libraries our current maven configuration is pulling in we will run this command:
```
mvn dependency:tree

```

This will show you all the libraries your maven configuration is pulling in.  This is valuable because you can see your direct dependencies as well as the libraries your dependencies require.  Dependencies of dependencies are called [Transitive Dependencies](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html).

After running, this command you should see a list like this:

```
...

[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:2.2.5.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.2.5.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.2.5.RELEASE:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.2.3:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.12.1:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.12.1:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.30:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:1.25:runtime
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:2.2.5.RELEASE:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.10.2:compile

<Much more after this ...>
... 

```

This is valuable for seeing all the libraries and their versions that will be bundled with your app.

Let's update our parent pom's version and then run this again.

So, update the parent pom to be like this:

```
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.0.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
```

Now save your pom.xml file and rerun the **mvn dependency:tree** command.

You should see it download the newer dependencies and a list like this:
```
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:2.3.0.RELEASE:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.3.0.RELEASE:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:2.3.0.RELEASE:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.2.3:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.13.2:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.13.2:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:1.7.30:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:1.26:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:2.3.0.RELEASE:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.11.0:compile

```
So, notice how in the rest of the pom file there are no versions of libraries specified.
We are getting all of our versions for artifacts from the parent pom.  

If we add our own custom jars we would specify the version directly in our pom.xml, but since these are SpringBoot's dependencies the parent pom controls the versions.

This makes it nice to just update the parent pom to get all the latest dependencies. Notice we got a new version of the snakeyaml transitive dependency  by updating our parent version.
Everyone needs more snakeyaml... :)

Again all this helps use to "explicitly declare and isolate dependencies" as recommended by the [Twelve-Factor App](https://12factor.net/dependencies) methodology and obviously is extremely important for consistent and repeatable builds.

### Test Our App with Updated Dependencies

Now let's rebuild our app with our new versions of dependencies.  So we will use the **mvn package** command, but since we just made a fairly significant change, we will issue a **clean** command first to make sure everything is compiled fresh.  So issue this command:

```

mvn clean package

```
This will have maven clean our target folder where the compiled classes and built jar reside and then start fresh with new compile and package.
You will see additional downloads to get the new jars.

After the build is complete, run the app again by issuing this command:

```
java -jar ./target/base-app-0.0.1-SNAPSHOT.jar

```
Test again by going to [http://localhost:8080/hello](http://localhost:8080/hello).

You shouldn't see any different behavior in the app.  It should still work exactly as it did before, but now you can rest easier knowing you are on a newer version of the libraries.



Now hopefully you have a better understanding of how maven works to help us manage dependencies.  However, these concepts exist across programming platforms.  Node, Python, and Ruby have similar tools to help manage dependencies.  Be sure to keep your dependencies explicitly declared and isolated :).


## Understanding the How the App Runs.

You may have noticed that the only requirements for building  this SpringBoot app was Maven and Java.  
Even more shocking, is that we only needed Java to run the application.  Once the SpringBoot application was built, we could simply use java to execute it.  No need for an application server like WebSphere or Tomcat even.  However, SpringBoot web applications (like this one) do actually have an [embedded web server](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/howto-embedded-web-servers.html).  For most of your servlet stack applications (traditional web apps and REST APIs), Spring Boot includes an embedded [Tomcat](http://tomcat.apache.org/) installation.  For reactive stack applications, it includes [Reactor Netty](https://github.com/reactor/reactor-netty).

## Fast Feed Back Loop

One key aspect of becoming an efficient developer is to be able to have a very fast feed back loop cycle. That is from the time I make a code change how long does it take me to determine if that code change created the desired affect.  Obviously, the faster you can determine if your code it correct, the faster you can deliver features to your customers.

So, we don't want to have to follow all the steps we've done so far to run our app every time. That is we don't want to wait for the mvn package to completely process before each run of our app.  Luckily Spring Boot has a maven plugin that makes it much easier to run our code without doing a full package every time.

The [Spring Boot Maven Plugin](https://docs.spring.io/spring-boot/docs/current/maven-plugin/usage.html) makes running your app with Maven easy.
You can just open up a command prompt at the same location as the pom and run the **mvn spring-boot:run** command to run the application "in place".

``` 
mvn spring-boot:run
```

Once the app is running we can test it again by opening a browser and going to [http://localhost:8080/hello](http://localhost:8080/hello).

Now we should still be seeing the message **Hello From Your Super Duper Spring Boot app!**.  We are going to change this now and see how to retest.

So, open up the **HelloRestController** class which is under the **src/main/java/com/barry/demo/baseapp** folder.

Then edit the **return** String to have your name in it... So, the new **helloThere()** method should look similar to this:
```

@RestController
public class HelloRestController {

    //@Value("${mymessage}")
    //private String message;
    
    @GetMapping("/hello")
    public String helloThere(){
        return "Hello From Barry!";
    }

}
```

Now save this file and stop your existing SpringBoot app and run the **mvn spring-boot:run** command again to test your new change.

After testing it now, you should see your new message.

## Speeding Up Our Feedback Loop (Skip this section)

**NOTE: This section requires some specific IDE setup that makes it not worth actually trying to get working. So, instead just understand the concept.**
While this is faster than doing the mvn package to build the jar each time, this isn't fast enough.  We'd like to be able to  test our changes even quicker.  We want the developer feedback loop cycle as short as possible.

So, luckily SpringBoot also has a plugin we can add to have it watch for changes to files and it can auto-refresh our running code with our changes without having to stop and restart each time.

So, let's go to our pom file and add this dependency which will give us these developer tools to speed up our feedback loop.

In your pom.xml, you should see a commented out dependency like this:

```
		<!-- <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency> -->
```
Remove the comment characters at the beginning and end so it looks like this:

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
```

Save your pom.xml and then stop your current running application.

Now start it again with the **mvn spring-boot:run** command and test that you still see your modified message.

Now make a change to the same line of code (in the HelloRestController) and put a new message in between the quotes like this:

```
@RestController
public class HelloRestController {
	
    //@Value("${mymessage}")
    //private String message;
    
    @GetMapping("/hello")
    public String helloThere(){
        return "My how you've changed!";
    }

}
```

When you save the changes to your HelloRestController, you should see some new logs as it reloads the application.

Now test without stopping or re-starting the application and you should see your new changes!.

You can make a few different messages and see how quickly you can test the changes.


## External Configuration


Let's say we needed our message that we return to be different per environment.  So, like it needed to return "Hello from dev" when in the Dev environment and then "Hello from PT" when deployed to the Performance Test environment.

We need to remove our hardcoded message from the app and replace it from a message we can pull from elsewhere.

There's already a **message** property on the HelloRestController class.  We will return this property instead of our hard coded string message.

Let's modify the code to use our message property by uncommenting (removing the "//" before the "@Value" and String message lines) and return the message property.  Here's what it should look like when you are done:

```
@RestController
public class HelloRestController {

    @Value("${mymessage}")
    private String message;
    
    @GetMapping("/hello")
    public String helloThere(){
        return message;
    }

}
```

The way this property is defined above with the [@Value](https://www.baeldung.com/spring-value-annotation) annotation, allows SpringBoot to auto-inject a value into that property when it loads the class.  Because of the **"${mymessage}"** argument passed to the @Value annotation, SpringBoot will look for a property named **mymessage** to find the value for our property/variable.

If you look under the **src/main/resources** folder you will see an application.properties file.  This is one of the default locations SpringBoot will look for properties.  I've already added a **mymessage** property in it with a value.

So, if you've uncommented the **@Value** and **private String message** lines we can allow Spring to inject a value into this property from our **application.properties** file.  

Go ahead and rerun your application AFTER you save your HelloRestController class.

Test your application and you should see the message displayed from the properties file.  

## Better Config Options

We've removed the hard coded message and externalized it into a properties file.  This is better than before and we could have different properties files for each environment if needed, but this is not the best practice.

According to the recommendation by the Twelve Factor App [CONFIG](https://12factor.net/config) methodology, we should pull environment specific config information from environment variables.   This allows each environment to have their own set of custom configs that can be set at deployment easily in most cloud platforms.  You can see this in practice when you use Docker or Kubernetes (but that's outside the scope of this tutorial).

In our case we are just dealing with a simple message, but the same pattern applies for database connection strings, remote API hosts, or even passwords and other sensitive data that shouldn't be in plain text in files.

Let's look at how we can have SpringBoot read our message from an environment variable.  

So, let's set an environment variable called **mymessage** with a value of **from env!**. 
 In your command prompt or terminal where you are running your app, set the environment variable.

On Windows command prompt this is done with **set mymessage=from env**

From a linux, mac, or gitbash terminal use **export mymessage='from env'**

Now what changes do we need to make to our SpringBoot app to get it to read from an environment variable verses the properties file???

Absolutely NONE!  We don't need to change the app, by default SpringBoot will look through several different locations to resolve properties and environment variables are one of them.  And environment variables are read before looking at the local applications.properties file.  Here is the official documentation on [Externalized Config For SpringBoot](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/htmlsingle/#boot-features-external-config).

But if you've set the environment property and run the app from that same terminal/command window, then you should be able to test and see your message is read from the environment variable.


## Logging 

In any application, logging provides visibility into the running of an application.  We will look at different ways to  log information out of our app now. If we look at the **KungfuController** class we will see a method that responds to the **/kungfu/punch** url pattern requests.  This method takes a **type** parameter and uses the default Java **System.out.println()** method to write it out several messages to the stdout.

Run your app (if it's not already running) and hit this URL: [http://localhost:8080/kungfu/punch?type=light](http://localhost:8080/kungfu/punch?type=light)

You should see the response: **"mild agitation"** which is exactly the response you would expect from a light punch.

More importantly if you look at your output in your terminal where you started the app, you should see several output log message...

```
...

2020-05-20 11:43:14.696  INFO 26176 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 5 ms
I'm hanlding an in coming punch!
type=light
About to return this response=mild agitation

```
Our simple logging is giving us visibility into the app, but there's an issue... In production, if we get 50 calls a minute, we will be logging 9,000 lines of log messages every hour just from this single method!  Do we need all this data in Production?  Well, as it is right now, it's hardcoded and will always log out regardless of the environment or any config.

We typically don't want to rely on "sysout" logging (System.out.println() style logging).  We instead want to use a logging framework that allow us to control the logging levels and destinations through an external configuration.

In java there is a **"Simple Logging Facade for Java"** or **SLF4J** framework that provides an API to give more granular control over logging.  As the name implies, this is simply a facade or api for logging and not an implementation... By default, SpringBoot using LogBack as its logging implementation behind the scenes.  For the most part you don't need to worry about the underlying implementation unless you want to get into some unique configurations.

We will replace our System.out.println() statements with logging statements.  To do this just replace the entire **incomingAction()** method with this block:

```
    @GetMapping("/punch")
    public String incomingAction(@RequestParam("type")String type){
        log.trace("I'm handling an in coming punch!");
        log.debug("type=" + type);
        String responseToPunch = kungFuService.throwPunch(type);
        log.debug("About to return this response=" + responseToPunch);
        return responseToPunch;
    }
```

To configure where the logs go and the appropriate level, we simply need to add two lines to our **application.properties** file.  I've already added the lines we need, but they are commented out. We will uncomment (remove the '#') two lines in our **application.properties** file so it looks like this:

```
mymessage=hello there from a properties file


logging.file.name=myapp.log
logging.level.com.barry=TRACE

```

Now when you restart the app, you will see a file created at the root of your application called "myapp.log" and you will see log statements in there.  When you hit the URL [http://localhost:8080/kungfu/punch?type=light](http://localhost:8080/kungfu/punch?type=light), you will see the log statements show up in the **myapp.log** file.

If we change the **logging.level.com.barry** to **DEBUG** and retest our app we should see that we no longer see the first log statement that said "I'm handling an in coming punch!". When changing the log level to "DEBUG" it no longer shows "TRACE" level messages because with [LogBack](http://logback.qos.ch/manual/architecture.html)(and most logging frameworks), you specify the most granular level of logging you want and everything more granular is disabled.  Logging levels from MOST granular to least are **TRACE**, **DEBUG**, **INFO**, **WARN**, **ERROR**.

Using appropriate logging statements and having different configuration per environments, you can have complete control over your logging.  

HOWEVER, there's currently a huge issue with our Logging solution!  We are not following the [Twelve-Factor Apps Logs](https://12factor.net/logs) methodology.  Imagine if our important logs are going to a file where our app is deployed.  What happens when we deploy to five different servers and we want to see all the logs?  Or more likely, when we deploy to a cloud solution like Kubernetes and our ***servers*** may spin up and down throughout the day... finding them would be super complicated...  This is why it's best to not "write to or manage logfiles".  We shouldn't be concerned about exactly where our logs our initially written, but have an automated solution for aggregating our logs in such a way that is easy to collate and browse them.  

So, to get inline with the Twelve-Factor Apps Logging recommendations we need to stop writing to a log file, but instead write to standard out (stdout).

This is easy in our case as that's how SpringBoot expects it to work by default.  If we just remove the **logging.file.name** property from our **application.properties** file and our logs will go to standard out.

Try this and test again.


You should see your log statements coming to the stdout (your console/terminal).  


## Unit Testing

One of the last things we are going to cover should be one of the first topics you consider when beginning development and that **Unit Testing**.  

[Unit Testing](http://softwaretestingfundamentals.com/unit-testing/) is writing code that tests a small unit of work implemented by other code.

The key to unit tests is that they quickly verify that a piece of code is working as expected.

As you build up your suite of Unit Tests, you should have a way to quickly and automatically check that the individual pieces of code are performing as you'd expect.

These unit tests should be run as part of each build phase typically before your app is packaged.  This is because unlike automated tests that run against a deployed application, unit tests execute against the code itself.

The advantage of Unit tests over UI tests (or End to End tests) is the ability for Unit Tests to run faster and identify exactly where an issue exists.  UI tests or End-to-End tests against the deployed app and usually test a web page or API which in turn invokes many methods across many components/classes and often time interacts with a Database or other systems.  If one of these tests fail, you know something went wrong, but from the test it's hard to know what, if any, of the components in your app have a problem or if it's a dependent system.
Whereas the unit test (if written properly) will show the exact method or class that has the issue.

Let's try it out..

Now that we've seen some of the code that makes up the app and understand more about Maven, we will try our hand at writing some Unit tests.

If we look again at the **KungFuController**, we will notice that it calls a method called **throwPunch()** which is on the **KungFuService** class.

Open the **KungFuService** class which is under the **src/main/java/com/barry/demo/base-app/services** folder.
You will notice that in the **throwPunch()** method there are 3 if statements handling the **typeOfPunch** of **light, high, and low**.  

There are already tests written to verify these three inputs return the expected output.  Open the test class **KungFuServiceTest** under the **src/***test***/java/com/barry/demo/base-app/services**.

You'll see a test method for each input parameter type.  To run our existing tests, we will use maven.

Run this command to have maven run the validate, compile, and tests phases:

```
mvn test

```
You should see block that looks like this in the output:

```
...

[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.barry.demo.baseapp.services.KungFuServiceTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.034 s - in com.barry.demo.baseapp.services.KungFuServiceTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

...
```

This shows that are 3 tests ran and they all passed with no errors or failures.

Now let's play the role of clumsy developer that accidently changes the wrong code...

Open up the **KungFuService.throwPunch()** method and change the first if statement to return **knockout** instead of "mild agitation":

```
   public String throwPunch(String typeOfPunch){

        if(typeOfPunch.equalsIgnoreCase("light")) {
            return "knockout";
        }
        if(typeOfPunch.equalsIgnoreCase("high")){
            return "nose bleed";        
        }if(typeOfPunch.equalsIgnoreCase("low")){
            return "gut ache";        
        }else{
            return "disdain";
        }
    }
```

Now run the tests again with the **mvn test** command.

You should see a failure this time and a message like this:
```
... 

[INFO] Results:
[INFO]
[ERROR] Failures:
[ERROR]   KungFuServiceTest.throwPunch_given_light_return_mild_agitation:22 expected: <mild agitation> but was: <knockout>
[INFO]
[ERROR] Tests run: 3, Failures: 1, Errors: 0, Skipped: 0

...
```
Our test caught the incorrect change.  Of course, if the change was intentional the test would have to be updated as well.

Change the **throwPunch()** method back to allow the tests to complete successfully.

Now that our tests are successful again, let's make another change to our **KungFuService.throwPunch()** method.  After the 3 **if** statements, you'll see we have an else block, change it from returning **"disdain"** to **"snicker"**.

Now run the tests again.

Did they fail???  NO?  Why not?  We don't have a test covering that scenario.

We can tell this wasn't developed using Test Driven Development.  Because in Test Driven Development, no code would have been written without having a test written first.  Test Driven Development is powerful because if followed properly help you design code that is easily testable and actually shapes the way your code is architected.  Maybe more on that later...

Let's write a test to verify our else block.  Add the following test to the **KungFuServiceTest** class.

```
    @Test
    public void throwPunch_given_unknown_input_return_disdain(){
        String response = kungFuService.throwPunch("punch of the uknown");
        assertEquals("disdain",response);
    }
```

If you run your tests again, you should see it fail (assuming you still have the modified else block in the throwPunch()) method.

If it does fail, fix the throwPunch() method to return "disdain" in the else block and then retest.

Now we have all of our paths through the code tested.  There are actually tools that you can plug into Maven that will tell you how much code coverage you have and show you what lines of code are tested and not tested.

It's essential to have good Unit Test coverage as we move to a continuous delivery model.


## Wrap Up

We didn't cover all 12 of the [Twelve-Factor App](https://12factor.net/) methodologies, but we got to look at a few... I'd suggest you look at the rest and have a good understanding of them.

Also, hopefully you understand a little bit more of the makeup of a Java application and how they can be tested, packaged, and published via Maven.

There's always more to learn so keep at it and practice what was taught here in your favorite language and look at the resources below!


## RESOURCES:


Test Driven development:

https://martinfowler.com/bliki/TestDrivenDevelopment.html

https://www.agilealliance.org/glossary/tdd













