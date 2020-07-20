import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { DocumentLibraryWidgetComponent } from './document-library-widget.component';
import { DocumentServiceService } from '../../service/document-service.service';
import { DocumentLibraryComponent } from './sub-component/document-library/document-library.component';
import { RouterModule, Route } from '@angular/router';

@NgModule({
  declarations: [DocumentLibraryWidgetComponent, DocumentLibraryComponent],
  imports: [
    CommonModule,
    RouterModule,
    BackbaseCoreModule.withConfig({
      classMap: { DocumentLibraryWidgetComponent },
    }),
  ],
  /* add the provider */
  providers: [DocumentServiceService]
})
export class DocumentLibraryWidgetModule { }
