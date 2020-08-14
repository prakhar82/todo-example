## 1. Create Layout container

npm run ng -- generate widget --name=document-layout-container

Open ./lib/document-layout-container/model.xml
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<catalog>
  <widget>
    
    --
    
  </widget>
</catalog>
```

##### Modify like below 
```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<catalog>
  <container>
    
    --
    
  </container>
</catalog>
```
#### ./lib/document-layout-container/src/document-side-menu.component.html
```angular2
<ng-container>
  <div class="row m-auto  max-width min-height-container">
    <div class="col-sm-12 col-md-3 ">
      <document-side-menu [documentTabs]="documentTabs | async" >
      </document-side-menu>
    </div>

    <div class="col-sm-12 col-md-9 ">
      <bb-area></bb-area>
      <bb-router-outlet></bb-router-outlet>
    </div>
  </div>
</ng-container>
```

#### ./lib/document-layout-container/src/document-layout-container.component.ts

import following components
```angular2
import { ItemModelTree, RoutableContainer, mapObservableArray } from '@backbase/core-ang';
import { combineLatest } from 'rxjs';
import { map } from 'rxjs/operators';
```
Make component as Routable component by adding @RoutableContainer()
```angular2
@RoutableContainer()
@Component({
  selector: 'bb-document-layout-container',
  templateUrl: './document-layout-container.component.html'
})
```
Append below code inside <b>DocumentLayoutContainerComponent</b> class to create tree structure side menu

```angular2
constructor(private modelTree: ItemModelTree) { }

readonly documentTabs = this.modelTree.children.pipe(
  mapObservableArray(child =>
    combineLatest([
      child.value.property<string>('route'),
      child.value.property<string>('navTitle'),
      child.value.property<string>('createSideMenu')
    ])
  ),
  map(children =>
    children.map(([route, navTitle, createSideMenu], index) =>
      this.createTransferSideMenu(route, navTitle, createSideMenu, index)
)
)
);

/**
* Creates transfer side menu
* @param route
* @param title
* @param index
* @returns
*/
private createTransferSideMenu(
  route: string | undefined,
  navTitle: string | undefined,
  createSideMenu: string | undefined,
  index: number
) {
  return {
    route: route || (index + 1).toString(),
    navTitle: navTitle || 'Tab',
    createSideMenu: createSideMenu || 'false'
  };
}
```

###### create folder <u>document-side-menu</u> inside ./lib/document-layout-container/src/
###### create file <u><b>document-side-menu.component.html</b></u> inside <u>document-side-menu</u> folder and add following code
```angular2
 <div class="bp-g-model">
    <div class="panel-launcher">
      <!-- Side menu panel -->
      <div class="panel-heading panel-round clearfix cursor-pointer">

      </div>
      <!-- Side menu list -->
      <ul class="p-none" *ngIf="documentTabs">
        <ng-template ngFor let-tab [ngForOf]="documentTab">
          <li  class="list-group-item  cursor-pointer"

            (click)="navigateTo(tab.route)">
            <span class="cen">{{ tab?.navTitle }}</span>

          </li>
        </ng-template>
      </ul>
    </div>
  </div>
</div>

```
###### create file <u><b>document-side-menu.component.ts</b></u> inside <u>document-side-menu</u> folder and add following code
```angular2  
import { Component, OnInit, Input, SimpleChanges, OnChanges } from '@angular/core';
import { RouterService } from '@backbase/foundation-ang/core';


@Component({
  // tslint:disable-next-line: component-selector
  selector: 'document-side-menu',
  templateUrl: './document-side-menu.component.html'
})
export class DocumentSideMenuComponent implements OnInit, OnChanges {
  documentTab: any;
  clicked: any;
  @Input() documentTabs: any;

  constructor(private router: RouterService) { }

  ngOnInit() {}


  ngOnChanges(changes: SimpleChanges) {
    if (changes.documentTabs) {
      this.documentTab = this.documentTabs.filter((tab:any) => {
        return tab.createSideMenu === 'true';
      });

    }
  }
  /**
   * Navigates to
   * @param id
   */
  navigateTo(route: string) {
    localStorage.setItem("Route", route);
   // this.responseFrmSubMenu = route;

    this.router.navigate([route]);
  //  this.beneficiaryService.setStartPosition();
  }
}
                    
```
#### ./lib/document-layout-container/src/document-layout-container.module.ts
import DocumentSideMenuComponent into module
```angular2
import { DocumentSideMenuComponent } from './document-side-menu/document-side-menu.component';
````
Declare into 
```ruby
@NgModule({
  declarations: [
        DocumentLayoutContainerComponent,
        DocumentSideMenuComponent
  ],
  imports: [
    CommonModule,
    BackbaseCoreModule.withConfig({
      classMap: { DocumentLayoutContainerComponent }
    })
  ]
})
export class DocumentLayoutContainerModule { }
```

[<<Back](./README.md)
