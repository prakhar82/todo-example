import { Component, OnInit } from '@angular/core';
import { DocumentServiceService } from '../../../../service/document-service.service';
import { Documents, Document } from 'libs/document-data/src/document-data.interfaces';
import { Observable } from 'rxjs';
import { ItemModel } from '@backbase/foundation-ang/core';

@Component({
  selector: 'bb-personal-document',
  templateUrl: './personal-document.component.html',
})
export class PersonalDocumentComponent implements OnInit {
  /**
   * Get document listd url of personal document component
   */
  getDocumentListdUrl: Observable<string> = this.model.property('getDocuments_endpoint', 'error');
  /**
   * Document response array of personal document component
   */
  documentResponseArray: Document[] = [];// <DocumentResponse>{}; //[];

  /**
   * Response documents of personal document component
   */
  responseDocuments: Documents = <Documents>{};

  constructor(private documentServiceService: DocumentServiceService, private model: ItemModel) {}

  ngOnInit() {
    this.getDocumentListdUrl.subscribe(url => {
      this.documentServiceService.getPersonalDocument(url).subscribe(data => {
         //this.documentResponseArray = data.Documents;
         this.responseDocuments = data.Documents;
        console.log(this.responseDocuments);
      });
    });
  }
}
