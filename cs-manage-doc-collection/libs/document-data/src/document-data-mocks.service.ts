import { createMocks } from '@backbase/foundation-ang/data-http';
import { Provider } from '@angular/core';
import { DOCUMENT_DATA_CONFIG } from './document-data.service';
const examples = [
    {
        "urlPattern": "/document-service/v1/documents",
        "method": "GET",
        "responses": [
            {
                "status": 200,
                "body": null,
                "type": "DocumentResponse"
            }
        ]
    },
    {
        "urlPattern": "/document-service/v1/documentslib",
        "method": "GET",
        "responses": [
            {
                "status": 200,
                "body": null,
                "type": "DocumentResponse"
            }
        ]
    }
];
export const DocumentDataMocksProvider: Provider = createMocks(examples, DOCUMENT_DATA_CONFIG);
