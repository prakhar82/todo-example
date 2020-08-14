import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InjectionToken, ModuleWithProviders } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { createServiceDataHttpConfig, DATA_HTTP_CONFIG, DataHttpModule, ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { PERSONAL_DOCUMENT_DATA_CONFIG, PersonalDocumentDataService } from "./personal-document-data.service";
export const CONFIG_VALUE = new InjectionToken("PersonalDocumentData Data Service :: Custom Http Config");
@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        DataHttpModule,
        HttpClientModule
    ],
    providers: [PersonalDocumentDataService, { provide: CONFIG_VALUE, useValue: {
            servicePath: '',
        }}, {
           provide: PERSONAL_DOCUMENT_DATA_CONFIG,
           useFactory: createServiceDataHttpConfig,
           deps: [DATA_HTTP_CONFIG, CONFIG_VALUE],
        }]
})
export class PersonalDocumentDataModule {
    static forRoot(config: Partial<ServiceDataHttpConfig>): ModuleWithProviders {
        return {
            ngModule: PersonalDocumentDataModule,
            providers: [
                {
                    provide: CONFIG_VALUE,
                    useValue: config,
                },
            ],
        };
    }
}
