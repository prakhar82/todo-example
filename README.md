## todo-example

## Important Link
### Prerequisites:
https://community.backbase.com/documentation/foundation_angular/4-26-0/prerequisites#prerequisites
### Trails:
https://community.backbase.com/trails/build_your_first_wa3_experience
### UI-Component:
https://wa3.backbase.com/foundation/4.18.0/howto/create-ui-components#shared-throughout-developers
### Develop a new widget:
https://community.backbase.com/documentation/foundation_angular/4-26-0/develop_a_new_widget#develop_a_new_widget
### Out of box widget:
https://community.backbase.com/trails/build_your_first_wa3_experience

### Collapsible elements example
https://designsystem.backbase.com/v2/components/collapsible-accordion/web

### Datepicker (Range selection)
https://designsystem.backbase.com/v2/components/input-datepicker/web

### RichTextEditor
https://designsystem.backbase.com/v2/components/rich-text-editor/web

### Loading component
https://designsystem.backbase.com/v2/components/loading-indicator/web

### Progressbar
https://designsystem.backbase.com/v2/components/progressbar/web

### Prerequisites for Widget Collection 3

Download 10.x version of NodeJS from https://nodejs.org/en/download and follow the installation instructions for latest LTS versiBy default npm (NodeJS Package Manager) is installed at the same time. However please install npm v6.0.1 separately to avoid problems with breaking changes that can happen later.on.
```
npm install -g npm@6.0.1
```
### Configure NPM Access
Set up the npm registry
Run below command to register npm

	npm adduser --registry=https://repo.backbase.com/api/npm/npm-backbase/ --always-auth --scope=@backbase    
	Username:  *******
	Password:
	Email: (this IS public) xx@abc.com
	Logged in as  ****  to scope @backbase on https://repo.backbase.com/api/npm/npm-backbase/
	
### Install Command Line Tools	
1. Install the angular-cli.(https://cli.angular.io/)
   The Angular CLI is a command-line interface tool that you use to initialize, develop, scaffold, and maintain Angular applications.
	```
	npm install -g @angular/cli@8.0.0
	```
2. Install the Backbase schematics.
   @bb-cli/schematics is package used for scaffolding Backbase Widget Architecture 3 files, you will need access to Backbase private npm repo, before running.
   
   ```
     npm install -g @bb-cli/schematics@2.x
   ```
3. Install the Backbase bb-import tool
  ```
  npm install -g @bb-cli/bb-import@2.x
  ```

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

### Download the Project

https://start-training.backbase.com/

### Start MySQL and Active MQ

	1.	Make sure the following ports are not being used:
		o	Active MQ: 61616, 8161, 5672, 61613, 1883, and 61614.
		o	MySQL: 3306
	2.	Open a Terminal or Command Prompt
	3.	Navigate to the platform folder of the project
	4.	Execute the following command
	
		docker-compose up
		
### Start Infrastructure and Platform Services

	1.	Open a Terminal or Command Prompt
	2.	Navigate into the platform folder
	3.	Execute the following command:

		mvn blade:run

### Start Customer Experience Services

	1.	Open a Terminal or Command Prompt
	2.	Navigate into the cx6-targeting folder
	3.	Execute the following command:
	
		mvn clean install -Pclean-database
		
		In the same terminal, execute the following command:
		
		mvn blade:run

### Import your experiences and collections and access the Experience Manager

To import all of the experiences and collections available in the statics folder, there are three commands as outlined below. Once these have been successfully imported, you will then be able to access the Experience Manager.

### mvn bb:provision
This command imports collections and widgets

### mvn bb:import-experiences
This command imports experiences and demo experience without its pages.

### mvn bb:import-packages
This command imports page (link) artifacts to a portal.

### Access Experience Manager

	1.	Open a Browser
	2.	Navigate to the following URL: http://localhost:8080/gateway/cxp-manager.
	3.	Log in to Experience Manager by using the below credentials:
		o	Username: admin
		o	Password: admin

### Install Node.js
Run the following commands to install and use Node.js 10.18.1:

	nvm install 10.18.1
	nvm use 10.18.1

### Install Angular CLI
	
	npm install -g @angular/cli@8.0.0

### Install the Backbase schematics

	npm install -g @bb-cli/schematics@2.12.1
	
### foundation-ang is the core library of WA3

	npm install @backbase/foundation-ang@4.25.0
	npm i -g @bb-cli/bb-import

# CREATE TODO WIDGET

The following examples are based on a scaffolded application named todo-app. The minimal project and app can be scaffolded using the following commands:

	ng new todo-collection --collection=@bb-cli/schematics
	cd todo-collection
	npm run ng -- generate app --name=todo-app

### Scaffold ToDo Widget
In the root directory of your project execute
	
	npm run ng -- generate widget --name=todo-widget

### Generate Data Modules:

	ng generate @bb-cli/schematics:data-module --name TodoData --ramlPath ./raml/api.raml

### Enable data module mocks in application

Every data-module contains auto-generated mock data as well. With following changes we will configure our app to use mocked data in development environment:

#### todo-app-project\apps\todo-app\src\environments

	import { TodoDataMocksProvider } from '../../../../libs/todo-data/src/todo-data-mocks.service'
	import { Provider } from '@angular/core';
	
	export const environment = {
		production: false,
		mockProviders: [createMocksInterceptor(),TodoDataMocksProvider,] as Array<Provider>,
	};

### Render data in the widget

Let’s first create Injectable service which is normalizing data retrieved from data modules:

#### libs/todo-widget/src/todo.service.ts

			import { Injectable } from '@angular/core';
			import { HttpResponse } from '@angular/common/http';
			import { Observable, ReplaySubject } from 'rxjs';
			import { map, switchMap } from 'rxjs/operators';
			 
			import {TodoDataService} from '../../todo-data/src/todo-data.service';
			import {TodoItemsResponse} from '../../todo-data/src/todo-data.interfaces'
			 
			export interface TodoItem {
			  id: string;
			  title: string;
			  dueDate: Date;
			}
			 
			@Injectable()
			export class TodoService {
			  private readonly id = new ReplaySubject<string>();
			 
			  constructor(private readonly data: TodoDataService) {}
			 
			  readonly items: Observable<Array<TodoItem>> = this.data.getTodos()
				.pipe(map((response: HttpResponse<TodoItemsResponse>): Array<TodoItem> => {
				  return response.body
					? response.body.TodoItems.map(itemFromData)
					: [];
				}));
			   
			}
			 
			function itemFromData(record: any): TodoItem {
			  return {
				id: record.id,
				title: record.value.title,
				dueDate: new Date(record.value.dueDate),
			  };
			}

### Next, we will create a component that simply renders todo items as html list:

#### libs/todo-widget/src/todo-list.component.ts

			import { Component } from '@angular/core';
			import { Observable } from 'rxjs';
			 
			import { TodoService, TodoItem } from './todo.service';
			 
			@Component({
			  selector: 'bb-todo-widget',
			  template: `
			  <ul>
				<li *ngFor="let todo of todos | async">
				  {{ todo.title }}
				</li>
			  </ul>
			  `,
			})
			export class TodoWidgetComponent {
			  todos: Observable<Array<TodoItem>>;
			  constructor(private todoService: TodoService) {
				this.todos = todoService.items;
			  }
			}
			
### Then import service into widget’s module and add it to the list of module providers and import list component and add to declarations:

#### libs/todo-widget/src/todo-widget.module.ts

			import { NgModule } from '@angular/core';
			import { CommonModule } from '@angular/common';
			import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
			import { TodoWidgetComponent } from './todo-widget.component';
			import { TodoDataModule } from '../../todo-data/src/todo-data.module';
			import { TodoService } from './todo.service';

			@NgModule({
			  declarations: [TodoWidgetComponent],
			  imports: [
				CommonModule,
				TodoDataModule,
				BackbaseCoreModule.withConfig({
				  classMap: { TodoWidgetComponent }
				})
			  ],
			  providers: [TodoService]
			})
			export class TodoWidgetModule { }
			
#### By configuring application as we did above:

when compiled for production, application will use data from real backend services 

	npm run package:apps
	
#### Import as BB widget:
  
    bb-import package dist/provisioning-packages/cx6/todo-app.zip  --portal-port=8080 --portal-host=localhost --portal-username admin --portal-password admin --portal-context gateway/api --portal-auth-path gateway/api/auth/login --portal-version 6.1  

when compiled for development, application will use mocked data

	npm start




	
	
	



	









