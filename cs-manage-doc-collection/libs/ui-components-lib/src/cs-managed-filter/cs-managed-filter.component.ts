import { Component, OnInit, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';
import { ICategories, ISelectedCategories } from 'libs/personal-document-data/src/personal-document-data.interfaces';
import { PersonalServiceService } from 'libs/service/personal-service.service';
import * as _ from 'lodash';
import { Observable, ReplaySubject } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { IDropdownSettings } from 'ng-multiselect-dropdown';

@Component({
  selector: 'bb-cs-managed-filter-ui',
  templateUrl: './cs-managed-filter.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CsManagedFilterComponent implements OnInit {
  @Output() sendSelectedCategorytoPersonal = new EventEmitter<ISelectedCategories[]>();
  dropdownList: any = {};
  dropdownSettings: IDropdownSettings = {};
  selectedItems = [];

  data: ISelectedCategories[] = [];

  constructor(private personalServiceService: PersonalServiceService) { }


  ngOnInit() {
    this.dropdownList = this.personalServiceService.getDocumentsCategoriesList();
  }
  selectedCategories(select: ISelectedCategories[]) {
    this.data = select;
  }
  applyFilter() {
    console.log("apply click");
    this.sendSelectedCategorytoPersonal.emit(this.data);
  }
  resetdatafilter: any[] = [];
  resetFilter() {
    this.resetdatafilter = [];
    console.log("filter click");
  }

}