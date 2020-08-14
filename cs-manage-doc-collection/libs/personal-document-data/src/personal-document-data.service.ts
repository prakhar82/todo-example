import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpEvent } from "@angular/common/http";
import { Observable } from "rxjs";
import { Inject, InjectionToken } from "@angular/core";
import { ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { PersonalDocumentList, PersonalDocumentListItem, EsignTransactionDetails, Categories, TypeItem, DocumentResponse, Documents, Document } from "./personal-document-data.interfaces";
const version = 'v1', normalizeHttpParameter = (accum: NormalizedHttpParameters, [key, value]: [string, string | string[] | undefined | number]) => {
    if (value === undefined) {
        return accum;
    }
    if (typeof value === 'number') {
        return { ...accum, [key]: String(value) };
    }
    return { ...accum, [key]: value };
}, normalizeHttpParameters = (params: HttpParameters = {}): NormalizedHttpParameters => Object.entries(params).reduce(normalizeHttpParameter, {});
export const PERSONAL_DOCUMENT_DATA_CONFIG = new InjectionToken("PersonalDocumentData Data Service :: Default HTTP Config");
@Injectable({
    providedIn: 'root'
})
export class PersonalDocumentDataService {
    constructor(private readonly http: HttpClient, 
    @Inject(PERSONAL_DOCUMENT_DATA_CONFIG)
    private readonly config: ServiceDataHttpConfig) { }
    getDocumentslib(params?: undefined, headers: HttpHeaders = new HttpHeaders({})): Observable<HttpResponse<DocumentResponse>> {
        const uri = `${this.config.apiRoot}${this.config.servicePath}/document-direct-integration-service/v1/documentslib`;
        return this.http.request<DocumentResponse>('get', uri, {
            params: normalizeHttpParameters(params),
            headers,
            observe: 'response',
            responseType: 'json',
            withCredentials: false,
        });
    }
    getDocumentsPersonal(uri: string, params?: undefined, headers: HttpHeaders = new HttpHeaders({})): Observable<HttpResponse<PersonalDocumentList>> {
        //const uri = `${this.config.apiRoot}${this.config.servicePath}/document-direct-integration-service/v1/documents/personal`;
        return this.http.request<PersonalDocumentList>('get', uri, {
            params: normalizeHttpParameters(params),
            headers,
            observe: 'response',
            responseType: 'json',
            withCredentials: false,
        });
    }
    getDocumentsCategories( params?: undefined, headers: HttpHeaders = new HttpHeaders({})): Observable<HttpResponse<Categories>> {
        const uri = `${this.config.apiRoot}${this.config.servicePath}/document-direct-integration-service/v1/documents/categories`;
        return this.http.request<Categories>('get', uri, {
            params: normalizeHttpParameters(params),
            headers,
            observe: 'response',
            responseType: 'json',
            withCredentials: false,
        });
    }
}
interface NormalizedHttpParameters {
    [k: string]: string | string[];
}
interface HttpParameters {
    [k: string]: string | string[] | undefined | number;
}
