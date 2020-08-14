import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { DocumentLayoutContainerComponent } from './document-layout-container.component';
import { DocumentSideMenuComponent } from './document-side-menu/document-side-menu.component';


@NgModule({
  declarations: [DocumentLayoutContainerComponent, DocumentSideMenuComponent],
  imports: [
    CommonModule,
    BackbaseCoreModule.withConfig({
      classMap: { DocumentLayoutContainerComponent }
    })
  ]
})
export class DocumentLayoutContainerModule { }
