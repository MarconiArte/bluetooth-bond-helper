import { WebPlugin } from '@capacitor/core';

import type { BluetoothBondHelperPlugin } from './definitions';

export class BluetoothBondHelperWeb extends WebPlugin implements BluetoothBondHelperPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
