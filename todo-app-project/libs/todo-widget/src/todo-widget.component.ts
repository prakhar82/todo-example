import { Component } from '@angular/core';
import { Observable } from 'rxjs';
 
import { TodoService, TodoItem } from './todo.service';
 
@Component({
  selector: 'bb-todo-widget',
  template: `
  <ul>
    <li *ngFor="let todo of todos | async">
      {{ todo.title }}
    </li>
  </ul>
  `,
})
export class TodoWidgetComponent {
  todos: Observable<Array<TodoItem>>;
  constructor(private todoService: TodoService) {
    this.todos = todoService.items;
  }
}