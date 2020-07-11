import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BackbaseCoreModule } from '@backbase/foundation-ang/core';
import { TodoWidgetComponent } from './todo-widget.component';
import { TodoDataModule } from '../../todo-data/src/todo-data.module';
import { TodoService } from './todo.service';

@NgModule({
  declarations: [TodoWidgetComponent],
  imports: [
    CommonModule,
    TodoDataModule,
    BackbaseCoreModule.withConfig({
      classMap: { TodoWidgetComponent }
    })
  ],
  providers: [TodoService]
})
export class TodoWidgetModule { }