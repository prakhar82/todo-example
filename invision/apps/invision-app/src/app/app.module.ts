import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { environment } from '../environments/environment';
import { PersonalWidgetModule } from '@backbase/personal-widget';
import { DocumentWidgetModule } from '@backbase/document-widget';
import { NavigationWidgetModule } from '@backbase/navigation-widget';
import { InvisionHeaderWidgetModule } from '@backbase/invision-header-widget';

import { NavigationSpaWidgetModule } from '@backbase/universal-ang/navigation';
import { LayoutContainerModule } from '@backbase/universal-ang/layouts';

import {
  PanelContainerModule,
  ColumnContainerModule,
  DeckContainerModule,
  ContainersModule,
} from '@backbase/universal-ang/containers';
import { InvisionDocumentsLayoutContainerModule } from '@backbase/invision-documents-layout-container';
import { DocumentModule } from '@backbase/document';



@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BackbaseCoreModule,
    RouterModule.forRoot([], { initialNavigation: false, useHash: true }),
    PersonalWidgetModule,
    DocumentModule,
    DocumentWidgetModule,
    NavigationWidgetModule,
    InvisionHeaderWidgetModule,
    ContainersModule,
    NavigationSpaWidgetModule,
    LayoutContainerModule,
    PanelContainerModule,
    ColumnContainerModule,
    DeckContainerModule,
    InvisionDocumentsLayoutContainerModule,
    DocumentModule

  ],
  providers: [...(environment.mockProviders || [])],
  bootstrap: [AppComponent],
})
export class AppModule {}
