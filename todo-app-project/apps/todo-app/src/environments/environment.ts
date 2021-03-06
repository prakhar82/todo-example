import { createMocksInterceptor } from '@backbase/foundation-ang/data-http';
import { TodoDataMocksProvider } from '../../../../libs/todo-data/src/todo-data-mocks.service';
import { Item } from '@backbase/foundation-ang/core';
import { ExternalServices } from '@backbase/foundation-ang/start';

import { Environment } from './type';
import { Provider } from '@angular/core';

const services: ExternalServices = {};

const pageModel: Item = {
  name: 'app-container',
  properties: {},
  children: [{
      name: 'todo-widget',
      properties: {
        classId: 'TodoWidgetComponent'
      }
    }],
};

export const environment: Environment = {
  production: false,
  mockProviders: [createMocksInterceptor(),TodoDataMocksProvider,] as Array<Provider>,
  bootstrap: {
    pageModel,
    services,
  },
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
