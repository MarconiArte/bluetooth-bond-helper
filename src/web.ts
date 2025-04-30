import { WebPlugin } from '@capacitor/core';

import type { BluetoothBondHelperPlugin } from './definitions';

export class BluetoothBondHelperWeb extends WebPlugin implements BluetoothBondHelperPlugin {

  async isBonded(): Promise<{isBonded: boolean}> {
    console.log('Simulando la verificación de vinculación en Web:');
    return {isBonded: false}; // En la web no se puede verificar la vinculación
  }

    // Implementamos el método startBonding con una simulación en la web
  async startBonding(): Promise<{ success: boolean | null }> {
    console.log('Bluetooth bonding is not supported in the web environment.');
    return {success: null};  // Simulamos que el emparejamiento siempre falla en la web
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
