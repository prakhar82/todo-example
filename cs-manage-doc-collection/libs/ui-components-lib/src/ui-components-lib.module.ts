import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CsManagedFilterModule } from './cs-managed-filter/cs-managed-filter.module';
import { NgMultiSelectDropDownModule  } from 'ng-multiselect-dropdown';
import { DropdownMultiSelectModule } from '@backbase/foundation-ang/ui';
import { CsManagedMultiSelectModule } from './cs-managed-multiSelect/cs-managed-multi-select.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CsManagedFilterModule,
    DropdownMultiSelectModule,
    CsManagedMultiSelectModule,
    NgMultiSelectDropDownModule
  ],
  exports: [CsManagedFilterModule, CsManagedMultiSelectModule]
})
export class UiComponentsLibModule { }