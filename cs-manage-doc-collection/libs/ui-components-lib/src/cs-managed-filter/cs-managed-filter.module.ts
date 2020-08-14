import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CsManagedFilterComponent } from './cs-managed-filter.component';
import { CsManagedMultiSelectModule } from '../cs-managed-multiSelect/cs-managed-multi-select.module';

@NgModule({
  imports: [CommonModule, CsManagedMultiSelectModule],
  declarations: [CsManagedFilterComponent],
  exports: [CsManagedFilterComponent]
})
export class CsManagedFilterModule { }
