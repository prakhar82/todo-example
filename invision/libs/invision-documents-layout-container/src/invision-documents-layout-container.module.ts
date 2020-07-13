import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { InvisionDocumentsLayoutContainerComponent } from './invision-documents-layout-container.component';
import { DocumentsSideMenuComponent } from './documents-side-menu/documents-side-menu.component';

@NgModule({
  declarations: [
    InvisionDocumentsLayoutContainerComponent,
    DocumentsSideMenuComponent
  ],
  imports: [
    CommonModule,
    BackbaseCoreModule.withConfig({
      classMap: { InvisionDocumentsLayoutContainerComponent },
    }),
  ],
})
export class InvisionDocumentsLayoutContainerModule {}
