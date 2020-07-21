## 3. Generate Data Modules

Run below command on root-dir
````
ng generate @bb-cli/schematics:data-module --name document-data --ramlPath ./raml/api.raml
````
###### Open ./libs/document-data/src/document-data.service.ts and modify getDocuments method pass uri parameter and remove generated uri `const uri= ${this.config.apiRoot}${this.config.servicePath}/document-service/v1/documents`;
```angular2
    getDocuments(uri: string,params?: undefined, headers: HttpHeaders = new HttpHeaders({})): Observable<HttpResponse<DocumentResponse>> {
        //const uri = `${this.config.apiRoot}${this.config.servicePath}/document-service/v1/documents`;
        return this.http.request<DocumentResponse>('get', uri, {
            params: normalizeHttpParameters(params),
            headers,
            observe: 'response',
            responseType: 'json',
            withCredentials: false,
        });
    }
```
###### Modify getDocumentsLib method pass uri parameter and remove generated uri `const uri= ${this.config.apiRoot}${this.config.servicePath}/document-service/v1/documentslib`;
```angular2
    getDocumentslib(uri: string,params?: undefined, headers: HttpHeaders = new HttpHeaders({})): Observable<HttpResponse<DocumentResponse>> {
        //const uri = `${this.config.apiRoot}${this.config.servicePath}/document-service/v1/documentslib`;
        return this.http.request<DocumentResponse>('get', uri, {
            params: normalizeHttpParameters(params),
            headers,
            observe: 'response',
            responseType: 'json',
            withCredentials: false,
        });
    }
```
###### Open ./libs/document-data/src/document-data-mocks.service.ts
Copy Provider name `DocumentDataMocksProvider`
###### Open ./apps/cs-manage-doc-collection-app/src/environments/environment.ts
import below components
```angular2
import { DocumentDataMocksProvider } from '../../../../libs/document-data/src/document-data-mocks.service'
import {Provider} from "@angular/core";
```
Add into mockProviders array
```angular2
export const environment: Environment = {
  production: false,
  mockProviders: [createMocksInterceptor(),DocumentDataMocksProvider] as Array<Provider>,
  bootstrap: {
    pageModel,
    services,
  },
};
```
[<<Back](../../README.md)
