import { Component, OnInit } from '@angular/core';
import { DocumentServiceService } from '../../../../service/document-service.service';
import { Documents, Document } from 'libs/document-data/src/document-data.interfaces';
import { Observable } from 'rxjs';
import { ItemModel } from '@backbase/foundation-ang/core';

@Component({
  selector: 'bb-document-lib-widget',
  templateUrl: './document-library.component.html',
  styles: []
})
export class DocumentLibraryComponent implements OnInit {
 /**
   * Get document listd url of personal document component
   */
  getDocumentListdUrl: Observable<string> = this.model.property('getDocumentsLib_endpoint', 'error');
  /**
   * Document response array of personal document component
   */
  documentResponseArray: Document[] = [];// <DocumentResponse>{}; //[];

  /**
   * Response documents of personal document component
   */
  responseDocuments: Documents = <Documents>{};
  showFilterUIComponent = false;

  constructor(private documentServiceService: DocumentServiceService, private model: ItemModel) {}

  ngOnInit() {
    this.getDocumentListdUrl.subscribe(url => {
      this.documentServiceService.getPersonalDocumentLib(url).subscribe(data => {
        //this.documentResponseArray = data;
        this.responseDocuments = data.Documents;
        console.log(this.responseDocuments);
      });
    });
  }
  onFilterClick(){
    this.showFilterUIComponent = true;
    console.log("image click");
  }

}
