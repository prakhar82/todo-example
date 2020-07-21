## 7. Package and import

######Run below command to package
````
npm run package:apps
````

######Run below command to import on enterprise catalog
````
bb-import package dist/provisioning-packages/cx6/cs-manage-doc-collection-app.zip  --portal-port=8080 --portal-host=lo
calhost --portal-username admin --portal-password admin --portal-context gateway/api --portal-auth-path gateway/api/auth/login --portal-version 6.1
````
######Open Exeprience manager on browser using below URL
````
http://localhost:8080/gateway/cxp-manager
```` 
````
Click on `Create` button
````
![Alt text](./images/create-experience-button.jpg?raw=true "Create button")
![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/create-experience-button.jpg)
````
Provide Portal name on create new experience popup
````
![Alt text](./images/create-new-experience-popup.jpg?raw=true "Create new experience")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/create-new-experience-popup.jpg)

````
Open Portal and create your lean master page by clecking on `+` symbol on `MASTER PAGES` section
````
![Alt text](./images/pages.jpg?raw=true "pages")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/pages.jpg)

````
Select `Based on` Lean Page and provide page Title like master-lean-page and finaly click save button.
````
![Alt text](./images/master-page.jpg?raw=true "master page")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/master-page.jpg)

````
Master page looks like as below 
````
![Alt text](./images/master-page-created.jpg?raw=true "master pages")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/master-page-created.jpg)

````
Drag Manageable Area from catalog to create master page (lean page)
````
![ScreenShot](./images/drag-manageable-area.jpg?raw=true "manageable area")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/drag-manageable-area.jpg)

````
Open Exprience Catalog to add your imported widgets and containers
````

![ScreenShot](./images/openExperienceCatalog.JPG?raw=true "open exprience catalog")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/openExperienceCatalog.JPG)


Add all widgets by searching and add by clicking on + symbol on each widget

![ScreenShot](./images/add-widget-into-portal-catalog.JPG?raw=true "add widget into portal catalog")

![picture](https://github.com/prakhar82/todo-example/blob/master/cs-manage-doc-collection/images/add-widget-into-portal-catalog.JPG) 

 

 
 [<<Back](./README.md)
