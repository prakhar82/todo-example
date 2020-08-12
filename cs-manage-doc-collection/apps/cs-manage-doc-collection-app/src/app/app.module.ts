import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { environment } from '../environments/environment';
import { DocumentLayoutContainerModule } from '@backbase/document-layout-container';
import { PersonalDocumentWidgetModule } from '@backbase/personal-document-widget';
import { DocumentDataModule } from '@backbase/document-data';
import { DocumentLibraryWidgetModule } from '@backbase/document-library-widget';
import { UiComponentsLibModule } from '@backbase/ui-components-lib';
import { from } from 'rxjs';
import { PersonalDocumentDataModule } from '@backbase/personal-document-data';
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BackbaseCoreModule,
    RouterModule.forRoot([], { initialNavigation: false, useHash: true }),
    DocumentLayoutContainerModule,
    PersonalDocumentWidgetModule,
    DocumentDataModule,
    DocumentLibraryWidgetModule,
    UiComponentsLibModule,
    PersonalDocumentDataModule
    ],
  providers: [...environment.mockProviders || []],
  bootstrap: [AppComponent]
})
export class AppModule { }
