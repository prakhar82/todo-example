## 4. Create Service
######Create service folder inside ./libs

######Goto inside service folder ./libs/service

######Run following command
````
ng g service document-service
````
######Open document-service.service.ts file and add below code
```angular2
import { Injectable } from '@angular/core';
import { DocumentDataService, DocumentResponse } from '@backbase/document-data';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DocumentServiceService {

constructor(private personalService: DocumentDataService) {}

  /**
   * Gets personal document
   * @param url
   *
   * @returns personal document
   */
  getPersonalDocument(url: string): Observable<DocumentResponse> {
    const getPersonalDocList: Observable<DocumentResponse> = this.personalService.getDocuments(url).pipe(
      map(
        (response: HttpResponse<DocumentResponse>): DocumentResponse => {
          return response.body ? response.body : <DocumentResponse>{};
        },
      ),
    );
    return getPersonalDocList;
  }

  getPersonalDocumentLib(url: string): Observable<DocumentResponse> {
    const getPersonalDocList: Observable<DocumentResponse> = this.personalService.getDocumentslib(url).pipe(
      map(
        (response: HttpResponse<DocumentResponse>): DocumentResponse => {
          return response.body ? response.body : <DocumentResponse>{};
        },
      ),
    );
    return getPersonalDocList;
  }
}
```
[<<Back](../../README.md)



