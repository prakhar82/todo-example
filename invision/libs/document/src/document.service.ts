import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpEvent } from "@angular/common/http";
import { Observable } from "rxjs";
import { Inject, InjectionToken } from "@angular/core";
import { ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { DocumentResponse, Documents, Document } from "./document.interfaces";
const version = 'v1', normalizeHttpParameter = (accum: NormalizedHttpParameters, [key, value]: [string, string | string[] | undefined | number]) => {
    if (value === undefined) {
        return accum;
    }
    if (typeof value === 'number') {
        return { ...accum, [key]: String(value) };
    }
    return { ...accum, [key]: value };
}, normalizeHttpParameters = (params: HttpParameters = {}): NormalizedHttpParameters => Object.entries(params).reduce(normalizeHttpParameter, {});
export const DOCUMENT_CONFIG = new InjectionToken("Document Data Service :: Default HTTP Config");
@Injectable({
    providedIn: 'root'
})
export class DocumentService {
    constructor(private readonly http: HttpClient, 
    @Inject(DOCUMENT_CONFIG)
    private readonly config: ServiceDataHttpConfig) { }
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
}
interface NormalizedHttpParameters {
    [k: string]: string | string[];
}
interface HttpParameters {
    [k: string]: string | string[] | undefined | number;
}
