import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { environment } from '../environments/environment';
import { TodoWidgetModule } from '@backbase/todo-widget';
import { TodoDataModule } from '@backbase/todo-data';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BackbaseCoreModule,
    RouterModule.forRoot([], { initialNavigation: false, useHash: true }),
    TodoWidgetModule,
    TodoDataModule
  ],
  providers: [...environment.mockProviders || []],
  bootstrap: [AppComponent]
})
export class AppModule { }
