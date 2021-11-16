# start with a jdk image 



# Copy the jar file into the image
# if you have a JDK and Maven setup, you can run the maven build to produce the jar locally into the target folder
# if you have the maven built jar locally, copy the jar from the target folder into the image as /usr/app/app.jar
# if you do not have the jar locally, you can RUN a curl command to get it from Maven Central here:
# TBD
# or you could potentially use the ADD command to get file via the URL.
# NOTE: if using curl or ADD you most likely will have to create the folder before copying the jar into it.


# Run/Start the application
# Issue the right command to start the app here.
