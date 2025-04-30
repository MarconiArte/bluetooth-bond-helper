export interface BluetoothBondHelperPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  startBonding(options: { deviceId: string }): Promise<{ success: boolean | null }>;
  isBonded(options:{ deviceId: string }): Promise<{isBonded: boolean}>;
}
