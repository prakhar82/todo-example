import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpEvent } from "@angular/common/http";
import { Observable } from "rxjs";
import { Inject, InjectionToken } from "@angular/core";
import { ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { TodoItemsResponse, TodoItems, TodoItem, Value } from "./todo-data.interfaces";
const version = 'v1', normalizeHttpParameter = (accum: NormalizedHttpParameters, [key, value]: [string, string | string[] | undefined | number]) => {
    if (value === undefined) {
        return accum;
    }
    if (typeof value === 'number') {
        return { ...accum, [key]: String(value) };
    }
    return { ...accum, [key]: value };
}, normalizeHttpParameters = (params: HttpParameters = {}): NormalizedHttpParameters => Object.entries(params).reduce(normalizeHttpParameter, {});
export const TODO_DATA_CONFIG = new InjectionToken("TodoData Data Service :: Default HTTP Config");
@Injectable({
    providedIn: 'root'
})
export class TodoDataService {
    constructor(private readonly http: HttpClient, 
    @Inject(TODO_DATA_CONFIG)
    private readonly config: ServiceDataHttpConfig) { }
    getTodos(params?: undefined, headers: HttpHeaders = new HttpHeaders({})): Observable<HttpResponse<TodoItemsResponse>> {
        const uri = `${this.config.apiRoot}${this.config.servicePath}/todo-service/v1/todos`;
        return this.http.request<TodoItemsResponse>('get', uri, {
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
