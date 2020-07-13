import { Provider } from '@angular/core';
import { Item } from '@backbase/foundation-ang/core';
import { ExternalServices } from '@backbase/foundation-ang/start';

export interface Environment {
  readonly production: boolean;
  readonly mockProviders?: Array<Provider>;
  readonly bootstrap?: {
    readonly pageModel: Item;
    readonly services: ExternalServices;
  };
}
