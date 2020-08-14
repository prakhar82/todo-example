import { createMocks } from '@backbase/foundation-ang/data-http';
import { Provider } from '@angular/core';
import { PERSONAL_DOCUMENT_DATA_CONFIG } from './personal-document-data.service';
const examples = [
    {
        "urlPattern": "/document-direct-integration-service/v1/documentslib",
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
        "urlPattern": "/document-direct-integration-service/v1/documents/personal",
        "method": "GET",
        "responses": [
            {
                "status": 200,
                "body": null,
                "type": "PersonalDocumentList"
            }
        ]
    },
    {
        "urlPattern": "/document-direct-integration-service/v1/documents/categories",
        "method": "GET",
        "responses": [
            {
                "status": 200,
                "body": null,
                "type": "Categories"
            }
        ]
    }
];
export const PersonalDocumentDataMocksProvider: Provider = createMocks(examples, PERSONAL_DOCUMENT_DATA_CONFIG);
