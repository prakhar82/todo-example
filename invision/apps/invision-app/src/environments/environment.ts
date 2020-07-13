import { createMocksInterceptor } from '@backbase/foundation-ang/data-http';

import { Item } from '@backbase/foundation-ang/core';
import { ExternalServices } from '@backbase/foundation-ang/start';
import { PersonalDocDataMocksProvider } from '../../../../libs/personal-doc-data/src/personal-doc-data-mocks.service';

import { Environment } from './type';

const services: ExternalServices = {};

const pageModel: Item = {
  name: 'app-container',
  properties: {},
  children: [{
      name: 'invision-documents-layout-container',
      properties: {
        classId: 'InvisionDocumentsLayoutContainerComponent'
      }
    }, {
      name: 'invision-header-widget',
      properties: {
        classId: 'InvisionHeaderWidgetComponent'
      }
    }, {
      name: 'navigation-widget',
      properties: {
        classId: 'NavigationWidgetComponent'
      }
    }, {
      name: 'document-widget',
      properties: {
        classId: 'DocumentWidgetComponent'
      }
    }, 
    {
      name: 'personal-widget',
      properties: {
        classId: 'PersonalWidgetComponent',
      },
    },
  ],
};

export const environment: Environment = {
  production: false,
  mockProviders: [createMocksInterceptor(),PersonalDocDataMocksProvider],
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
