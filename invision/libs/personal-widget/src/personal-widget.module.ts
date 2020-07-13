import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { PersonalWidgetComponent } from './personal-widget.component';
import { RouterModule, Route } from '@angular/router';
import { InvisionServiceService} from '../../service/personal-service/invision-service.service'
//import component for render in personal-widget
import { PersonalDocumentComponent } from './sub-component/personal-Document/personal-document.component';
@NgModule({
  declarations: [
    PersonalWidgetComponent,
    PersonalDocumentComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    BackbaseCoreModule.withConfig({
      classMap: { PersonalWidgetComponent },
    }),
  ],
  /* add the provider */
  providers: [InvisionServiceService]
})
export class PersonalWidgetModule {}
