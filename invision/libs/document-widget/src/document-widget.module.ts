import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { DocumentWidgetComponent } from './document-widget.component';
import { DocumentLibraryComponent } from './sub-component/document-library.component';
import { RouterModule, Route } from '@angular/router';

@NgModule({
  declarations: [
    DocumentWidgetComponent,
    DocumentLibraryComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    BackbaseCoreModule.withConfig({
      classMap: { DocumentWidgetComponent },
    }),
  ],
})
export class DocumentWidgetModule {}
