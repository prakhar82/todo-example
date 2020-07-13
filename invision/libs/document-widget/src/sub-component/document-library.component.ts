import { Component, OnInit } from '@angular/core';
import { InvisionServiceService } from '../../../service/personal-service/invision-service.service';
import { Documents, Document } from 'libs/document/src/document.interfaces';
import { Observable } from 'rxjs';
import { ItemModel } from '@backbase/foundation-ang/core';

@Component({
  selector: 'bb-document-library-widget',
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

  constructor(private personalInvisionService: InvisionServiceService, private model: ItemModel) {}

  ngOnInit() {
    this.getDocumentListdUrl.subscribe(url => {
      this.personalInvisionService.getPersonalDocumentLib(url).subscribe(data => {
        //this.documentResponseArray = data;
        this.responseDocuments = data.Documents;
        console.log(this.responseDocuments);
      });
    });
  }

}
