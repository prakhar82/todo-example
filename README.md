# todo-example


## Install & configure your local environment

# Set the environment variables

JAVA_HOME=<jdk-home-directory>
M2_HOME=<maven-home-directory>
M2=<maven-bin-directory>

# You also need to add the following values to your PATH variable:
# •	On OSX or Linux:
	PATH=$PATH:$M2
	PATH=$PATH:$JAVA_HOME/bin
#•	On windows:
	%M2%
	%JAVA_HOME%\bin

Verify that Maven, Docker, Docker-Compose and Java are installed correctly by executing the following commands:

javac -version
docker-compose --version
docker --version
mvn –version




