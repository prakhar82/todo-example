import { createMocks } from '@backbase/foundation-ang/data-http';
import { Provider } from '@angular/core';
import { TODO_DATA_CONFIG } from './todo-data.service';
const examples = [
    {
        "urlPattern": "/todo-service/v1/todos",
        "method": "GET",
        "responses": [
            {
                "status": 200,
                "body": null,
                "type": "TodoItemsResponse"
            }
        ]
    }
];
export const TodoDataMocksProvider: Provider = createMocks(examples, TODO_DATA_CONFIG);
