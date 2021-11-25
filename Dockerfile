# start with a jdk image 
# Recommended openjdk:11



# Copy the jar file into the image
# To do this, you either need the jar file locally and a COPY command or, you can use RUN with a curl command to get it.
# Either way the jar file can be found here: https://github.com/javaplus/CloudAppsDemo/raw/master/target/cloud-app-1.jar
# You could potentially use the ADD command to get file via the URL.
# NOTE: if using curl or ADD you most likely will have to create the folder before copying the jar into it.


# Run/Start the application
# Issue the right command to start the app here.
