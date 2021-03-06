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

http://localhost:8080/gateway/cxp-manager

Click on `Create` button

![Alt text](./images/create-experience-button.JPG?raw=true "Create button")

Provide Portal name on create new experience popup

![Alt text](./images/create-new-experience-popup.JPG?raw=true "Create new experience")

Open Portal and create your lean master page by clecking on `+` symbol on `MASTER PAGES` section

![Alt text](./images/pages.JPG?raw=true "pages")

Select `Based on` Lean Page and provide page Title like master-lean-page and finaly click save button.

![Alt text](./images/master-page.JPG?raw=true "master page")

Master page looks like as below 

![Alt text](./images/master-page-created.JPG?raw=true "master pages")

Drag Manageable Area from catalog to create master page (lean page)

![ScreenShot](./images/drag-manageable-area.JPG?raw=true "manageable area")


Open Exprience Catalog to add your imported widgets and containers


![ScreenShot](./images/openExperienceCatalog.JPG?raw=true "open exprience catalog")


Add all widgets by searching and add by clicking on + symbol on each widget

![ScreenShot](./images/add-widget-into-portal-catalog.JPG?raw=true "add widget into portal catalog")


 
 [<<Back](./README.md)
