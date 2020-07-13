import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { InvisionHeaderWidgetComponent } from './invision-header-widget.component';

@NgModule({
  declarations: [InvisionHeaderWidgetComponent],
  imports: [
    CommonModule,
    BackbaseCoreModule.withConfig({
      classMap: { InvisionHeaderWidgetComponent }
    })
  ]
})
export class InvisionHeaderWidgetModule { }