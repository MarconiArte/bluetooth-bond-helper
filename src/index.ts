import { registerPlugin } from '@capacitor/core';

import type { BluetoothBondHelperPlugin } from './definitions';

const BluetoothBondHelper = registerPlugin<BluetoothBondHelperPlugin>('BluetoothBondHelper', {
  web: () => import('./web').then((m) => new m.BluetoothBondHelperWeb()),
});

export * from './definitions';
export { BluetoothBondHelper };
