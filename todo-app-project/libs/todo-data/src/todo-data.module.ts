import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InjectionToken, ModuleWithProviders } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { createServiceDataHttpConfig, DATA_HTTP_CONFIG, DataHttpModule, ServiceDataHttpConfig } from "@backbase/foundation-ang/data-http";
import { TODO_DATA_CONFIG, TodoDataService } from "./todo-data.service";
export const CONFIG_VALUE = new InjectionToken("TodoData Data Service :: Custom Http Config");
@NgModule({
    declarations: [],
    imports: [
        CommonModule,
        DataHttpModule,
        HttpClientModule
    ],
    providers: [TodoDataService, { provide: CONFIG_VALUE, useValue: {
            servicePath: '',
        }}, {
           provide: TODO_DATA_CONFIG,
           useFactory: createServiceDataHttpConfig,
           deps: [DATA_HTTP_CONFIG, CONFIG_VALUE],
        }]
})
export class TodoDataModule {
    static forRoot(config: Partial<ServiceDataHttpConfig>): ModuleWithProviders {
        return {
            ngModule: TodoDataModule,
            providers: [
                {
                    provide: CONFIG_VALUE,
                    useValue: config,
                },
            ],
        };
    }
}
