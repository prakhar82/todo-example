## todo-example


### Install & configure your local environment

### Set the environment variables

JAVA_HOME=<jdk-home-directory>
M2_HOME=<maven-home-directory>
M2=<maven-bin-directory>

### You also need to add the following values to your PATH variable:
#### •	On OSX or Linux:
	PATH=$PATH:$M2
	PATH=$PATH:$JAVA_HOME/bin
#### •	On windows:
	%M2%
	%JAVA_HOME%\bin

Verify that Maven, Docker, Docker-Compose and Java are installed correctly by executing the following commands:

javac -version
docker-compose --version
docker --version
mvn –version

# Download the Project

https://start-training.backbase.com/

# Start MySQL and Active MQ

	1.	Make sure the following ports are not being used:
		o	Active MQ: 61616, 8161, 5672, 61613, 1883, and 61614.
		o	MySQL: 3306
	2.	Open a Terminal or Command Prompt
	3.	Navigate to the platform folder of the project
	4.	Execute the following command
	
		docker-compose up
		
# Start Infrastructure and Platform Services

	1.	Open a Terminal or Command Prompt
	2.	Navigate into the platform folder
	3.	Execute the following command:

		mvn blade:run

# Start Customer Experience Services

	1.	Open a Terminal or Command Prompt
	2.	Navigate into the cx6-targeting folder
	3.	Execute the following command:
	
		mvn clean install -Pclean-database
		
		In the same terminal, execute the following command:
		
		mvn blade:run

# Import your experiences and collections and access the Experience Manager

To import all of the experiences and collections available in the statics folder, there are three commands as outlined below. Once these have been successfully imported, you will then be able to access the Experience Manager.

# mvn bb:provision
This command imports collections and widgets

# mvn bb:import-experiences
This command imports experiences and demo experience without its pages.

# mvn bb:import-packages
This command imports page (link) artifacts to a portal.

# Access Experience Manager

	1.	Open a Browser
	2.	Navigate to the following URL: http://localhost:8080/gateway/cxp-manager.
	3.	Log in to Experience Manager by using the below credentials:
		o	Username: admin
		o	Password: admin

# Install Node.js
Run the following commands to install and use Node.js 10.18.1:

	nvm install 10.18.1
	nvm use 10.18.1

# Set up the npm registry
Run below command to register npm

	npm adduser --registry=https://repo.backbase.com/api/npm/npm-backbase/ --always-auth --scope=@backbase    
	Username:  *******
	Password:
	Email: (this IS public) xx@abc.com
	Logged in as  ****  to scope @backbase on https://repo.backbase.com/api/npm/npm-backbase/

# Install Angular CLI
	
	npm install -g @angular/cli@8.0.0

# Install the Backbase schematics

	npm install -g @bb-cli/schematics@2.12.1
	
# foundation-ang is the core library of WA3

	npm install @backbase/foundation-ang@4.25.0
	npm i -g @bb-cli/bb-import

## CREATE TODO WIDGET

	









