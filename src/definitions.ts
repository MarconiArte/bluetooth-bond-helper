export interface BluetoothBondHelperPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
