import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Observable, ReplaySubject } from 'rxjs';
import { map, switchMap } from 'rxjs/operators';
 
import {TodoDataService} from '../../todo-data/src/todo-data.service';
import {TodoItemsResponse} from '../../todo-data/src/todo-data.interfaces'
 
export interface TodoItem {
  id: string;
  title: string;
  dueDate: Date;
}
 
@Injectable()
export class TodoService {
  private readonly id = new ReplaySubject<string>();
 
  constructor(private readonly data: TodoDataService) {}
 
  readonly items: Observable<Array<TodoItem>> = this.data.getTodos()
    .pipe(map((response: HttpResponse<TodoItemsResponse>): Array<TodoItem> => {
      return response.body
        ? response.body.TodoItems.map(itemFromData)
        : [];
    }));
   
}
 
function itemFromData(record: any): TodoItem {
  return {
    id: record.id,
    title: record.value.title,
    dueDate: new Date(record.value.dueDate),
  };
}