import { Injectable } from '@angular/core';
import { PersonalDocumentDataService, PersonalDocumentList, Categories } from '@backbase/personal-document-data';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PersonalServiceService {

  constructor(private personalDataService: PersonalDocumentDataService) { }

  getPersonalDocumentList(url: string): Observable<PersonalDocumentList> {
    const getPersonalDocList: Observable<PersonalDocumentList> = this.personalDataService.getDocumentsPersonal(url).pipe(
      map(
        (response: HttpResponse<PersonalDocumentList>): PersonalDocumentList => {
          return response.body ? response.body : <PersonalDocumentList>{};
        },
      ),
    );
    return getPersonalDocList;
  }

  getDocumentsCategoriesList(): Observable<Categories> {
    const getDocumentCategoriesLists: Observable<Categories> = this.personalDataService.getDocumentsCategories().pipe(
      map(
        (response: HttpResponse<Categories>): Categories => {
          return response.body ? response.body : <Categories>{};
        },
      ),
    );
    return getDocumentCategoriesLists;
  }
}
