import { Component, OnInit } from '@angular/core';
import { PersonalServiceService } from '../../../../service/personal-service.service';
import { Observable } from 'rxjs';
import { ItemModel } from '@backbase/foundation-ang/core';
import { PersonalDocumentList, PersonalDocumentListItem, Categories, ISelectedCategories } from 'libs/personal-document-data/src/personal-document-data.interfaces';
import { ThrowStmt } from '@angular/compiler';
import { map } from 'rxjs/operators';
import * as _ from 'lodash';

@Component({
  selector: 'bb-personal-document',
  templateUrl: './personal-document.component.html',
})
export class PersonalDocumentComponent implements OnInit {
  /**
   * Get document listd url of personal document component
   */
  getDocumentListdUrl: Observable<string> = this.model.property('getDocumentsPersonal_endpoint', 'error');
  /**
   * Response documents of personal document component
   */
  responseDocuments: PersonalDocumentList = <PersonalDocumentList>{};
  getCategeoriesListdUrl: Observable<string> = this.model.property('getDocumentsCategories_endpoint', 'error');
  personalDocumentListArray: PersonalDocumentListItem[] = [];
  showFilterUIComponent = false;
  constructor(private personalServiceService: PersonalServiceService, private model: ItemModel) { }

  ngOnInit() {
    this.getDocumentListdUrl.subscribe(url => {
      this.personalServiceService.getPersonalDocumentList(url).subscribe(data => {
        this.responseDocuments = data;
        this.personalDocumentListArray = this.responseDocuments.DocumentDataSummery!;
        console.log(this.responseDocuments);
      });
    });
  }
  responseCategeoryList: Categories = <Categories>{};
  onFilterClick() {
    this.showFilterUIComponent = true;
  }
  selectedFilteredCategory: ISelectedCategories[] = [];
  
  filterByCategorySelectList(data: ISelectedCategories[]) {
   
    let tempSelectedFilter = [];
    let tempSelectedFilterOriginalData = this.personalDocumentListArray;
    this.selectedFilteredCategory = data;

    for (let i = 0; i < data.length; i++) {
      tempSelectedFilter = this.personalDocumentListArray.filter(items => items.TemplateType.includes(data[i].CategoryName));
      tempSelectedFilterOriginalData = tempSelectedFilter.concat(tempSelectedFilterOriginalData);
    }
    this.personalDocumentListArray = tempSelectedFilterOriginalData;
    if(this.personalDocumentListArray.length > 0){
      this.personalDocumentListArray = this.findDuplicates(this.personalDocumentListArray);
      this.personalDocumentListArray = this.personalDocumentListArray.filter((x, i, a) => a.indexOf(x) === i);
    }
    
  }
  findDuplicates(a: Array<any>): Array<any> {
    const k = [];
    for (const i in a) {
      for (const j in a) {
        if (i != j && JSON.stringify(a[i]) == JSON.stringify(a[j])) {
          k.push(a[i]);
        }
      }
    }
    return k;
  }
}
