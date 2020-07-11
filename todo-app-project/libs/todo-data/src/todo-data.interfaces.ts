export interface Additions {
    [key: string]: any;
}
export interface Customizable {
    additions?: Additions;
}
export interface TodoItemsResponse extends Customizable {
    status: string;
    TodoItems: TodoItems;
}
export type TodoItems = Array<TodoItem>;
export interface TodoItem extends Customizable {
    id: number;
    value: Value;
}
export interface Value extends Customizable {
    title: string;
    done: boolean;
    tags: string;
    dueDate: string;
}
