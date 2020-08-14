import { Component, OnInit, OnChanges, ChangeDetectionStrategy, Input, Output, EventEmitter } from '@angular/core';
import { PersonalServiceService } from 'libs/service/personal-service.service';
import { ICategories, ISelectedCategories } from 'libs/personal-document-data/src/personal-document-data.interfaces';
import { map } from 'rxjs/operators';
@Component({
  selector: 'bb-cs-managed-multiSelect-ui',
  templateUrl: './cs-managed-multi-select.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CsManagedMultiSelectComponent implements OnChanges {
  @Input() optionsList: ICategories [] = [];
  @Input() resetDataForSelect: any;
  @Output() selectCategoriesForFilter = new EventEmitter<ISelectedCategories[]>();
  categeoryDataSelected: ISelectedCategories [] = [] ;
  
  selectCategoryList(selectCatedata: ICategories) {
    selectCatedata.checked = !selectCatedata.checked;
    console.log(selectCatedata);
    this.categeoryDataSelected  = this.optionsList
      .filter((item: ICategories) => item.checked)
      .map((item: ICategories) => {
        return { CategoryCode: item.CategoryCode, CategoryName: item.CategoryName };
      });
    this.selectCategoriesForFilter.emit(this.categeoryDataSelected);
  }
  ngOnChanges(changes:any) {
    this.optionsList = changes.optionsList.currentValue;
    this.clearAllcheck();
  }
  selectAllcheck() {
    for (var i = 0; i < this.optionsList.length; i++) {
      this.selectCategoryList(this.optionsList[i]);
      this.optionsList[i].checked = true;
    }
    this.selectCategoriesForFilter.emit(this.categeoryDataSelected);
  }
  clearAllcheck() {
    for (var i = 0; i < this.optionsList.length; i++) {
      this.optionsList[i].checked = false;
      this.categeoryDataSelected = [];
      this.selectCategoriesForFilter.emit(this.categeoryDataSelected);
    }
  }

}
//{{categeoryDataSelected.length?categeoryDataSelected.length+' Selected':'Select Category'}} 




