package com.marconi.bluetoothbondhelper;

import java.util.Set;
import com.getcapacitor.JSObject; // Importa JSObject
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.getcapacitor.PluginCall;

public class BluetoothBondHelper {

    private final Context context;
    private final Activity activity;

    private PluginCall bondingCall;

    public BluetoothBondHelper(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

     // Método para verificar si el dispositivo ya está vinculado
     public boolean isBonded(String deviceId) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

        for (BluetoothDevice device : pairedDevices) {
            if (device.getAddress().equals(deviceId)) {
                return true; // El dispositivo ya está vinculado
            }
        }

        return false; // El dispositivo no está vinculado
    }

    public void startBonding(String macAddress, PluginCall call) {
        this.bondingCall = call;

        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = adapter.getRemoteDevice(macAddress);

        // Registrar receptor para el evento de bonding
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        context.registerReceiver(bondReceiver, filter);

        // Iniciar bonding
        device.createBond();
    }

    private final BroadcastReceiver bondReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
    
            if (BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);
    
                if (bondingCall == null || device == null) return;
    
                // Verificar el estado de vinculación
                switch (bondState) {
                    case BluetoothDevice.BOND_BONDED:
                        // Vinculación exitosa
                        JSObject retSuccess = new JSObject();
                        retSuccess.put("success", true);
                        bondingCall.resolve(retSuccess); // Responder con éxito
                        context.unregisterReceiver(this); // Desregistrar el receptor
                        break;
    
                    case BluetoothDevice.BOND_NONE:
                        // La vinculación falló o fue cancelada
                        JSObject retFail = new JSObject();
                        retFail.put("success", false);
                        bondingCall.resolve(retFail); // Responder con fallo
                        context.unregisterReceiver(this); // Desregistrar el receptor
                        break;
    
                    case BluetoothDevice.BOND_BONDING:
                        // Estado intermedio: Vinculación en progreso
                        // No es necesario hacer nada aquí, solo esperar el resultado final
                        break;
    
                    default:
                        // Para cualquier otro estado que no cubrimos
                        break;
                }
            }
        }
    };
}
