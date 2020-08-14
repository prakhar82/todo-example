import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InjectionToken, ModuleWithProviders } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { createServiceDataHttpConfig, DATA_HTTP_CONFIG, DataHttpModule, ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { DOCUMENT_DATA_CONFIG, DocumentDataService } from "./document-data.service";
export const CONFIG_VALUE = new InjectionToken("document-data Data Service :: Custom Http Config");
@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        DataHttpModule,
        HttpClientModule
    ],
    providers: [DocumentDataService, { provide: CONFIG_VALUE, useValue: {
            servicePath: '',
        }}, {
           provide: DOCUMENT_DATA_CONFIG,
           useFactory: createServiceDataHttpConfig,
           deps: [DATA_HTTP_CONFIG, CONFIG_VALUE],
        }]
})
export class DocumentDataModule {
    static forRoot(config: Partial<ServiceDataHttpConfig>): ModuleWithProviders {
        return {
            ngModule: DocumentDataModule,
            providers: [
                {
                    provide: CONFIG_VALUE,
                    useValue: config,
                },
            ],
        };
    }
}
