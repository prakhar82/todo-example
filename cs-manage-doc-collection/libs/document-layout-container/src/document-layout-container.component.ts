import { Component, OnInit } from '@angular/core';
import { ItemModelTree, RoutableContainer, mapObservableArray } from '@backbase/core-ang';
import { combineLatest } from 'rxjs';
import { map } from 'rxjs/operators';

@RoutableContainer()
@Component({
  selector: 'bb-document-layout-container',
  templateUrl: './document-layout-container.component.html'
})
export class DocumentLayoutContainerComponent implements OnInit {

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

ngOnInit() {}

}
