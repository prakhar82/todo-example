import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CsManagedMultiSelectComponent } from './cs-managed-multi-select.component';

@NgModule({
  imports: [ CommonModule,FormsModule,NgbModule ],
  declarations: [ CsManagedMultiSelectComponent ],
  exports: [ CsManagedMultiSelectComponent ]
})
export class CsManagedMultiSelectModule { }
