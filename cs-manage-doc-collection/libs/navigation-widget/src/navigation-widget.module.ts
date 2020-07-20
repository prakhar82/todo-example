import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { NavigationWidgetComponent } from './navigation-widget.component';
import { RouterModule, Route } from '@angular/router';
@NgModule({
declarations: [NavigationWidgetComponent],
imports: [
CommonModule,
RouterModule,
BackbaseCoreModule.withConfig({
      classMap: { NavigationWidgetComponent }
    })
  ]
})
export class NavigationWidgetModule { }
