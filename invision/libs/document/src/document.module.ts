import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InjectionToken, ModuleWithProviders } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { createServiceDataHttpConfig, DATA_HTTP_CONFIG, DataHttpModule, ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { DOCUMENT_CONFIG, DocumentService } from "./document.service";
export const CONFIG_VALUE = new InjectionToken("Document Data Service :: Custom Http Config");
@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        DataHttpModule,
        HttpClientModule
    ],
    providers: [DocumentService, { provide: CONFIG_VALUE, useValue: {
            servicePath: '',
        }}, {
           provide: DOCUMENT_CONFIG,
           useFactory: createServiceDataHttpConfig,
           deps: [DATA_HTTP_CONFIG, CONFIG_VALUE],
        }]
})
export class DocumentModule {
    static forRoot(config: Partial<ServiceDataHttpConfig>): ModuleWithProviders {
        return {
            ngModule: DocumentModule,
            providers: [
                {
                    provide: CONFIG_VALUE,
                    useValue: config,
                },
            ],
        };
    }
}
