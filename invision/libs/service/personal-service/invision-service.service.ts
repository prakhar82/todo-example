import { Injectable } from '@angular/core';
import { DocumentService, DocumentResponse } from '@backbase/document';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class InvisionServiceService {

  constructor(private personalService: DocumentService) {}

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
