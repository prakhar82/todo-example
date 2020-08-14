import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { PersonalDocumentWidgetComponent } from './personal-document-widget.component';
import { RouterModule, Route } from '@angular/router';
import { DocumentServiceService } from '../../service/document-service.service';
//import component for render in personal-widget
import { PersonalDocumentComponent } from './sub-component/personal-document/personal-document.component';
import { CsManagedFilterModule } from '@backbase/ui-components-lib';

@NgModule({
  declarations: [PersonalDocumentWidgetComponent, PersonalDocumentComponent],
  imports: [
    CommonModule,
    RouterModule,
    BackbaseCoreModule.withConfig({
      classMap: { PersonalDocumentWidgetComponent },
    }),
    CsManagedFilterModule,
  ],
  /* add the provider */
  providers: [DocumentServiceService]
})
export class PersonalDocumentWidgetModule { }
