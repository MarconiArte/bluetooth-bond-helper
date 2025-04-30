package com.marconi.bluetoothbondhelper;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;

import com.getcapacitor.JSObject;

import com.marconi.bluetoothbondhelper.BluetoothBondHelper;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "BluetoothBondHelper")
public class BluetoothBondHelperPlugin extends Plugin {

    private BluetoothBondHelper bondHelper;

    @Override
    public void load() {
        super.load();
        bondHelper = new BluetoothBondHelper(getContext(), getActivity());
    }

    @PluginMethod
    public void isBonded(PluginCall call) {
        String deviceId = call.getString("deviceId");

        if (deviceId == null || deviceId.isEmpty()) {
            call.reject("deviceId is required");
            return;
        }

        boolean isBonded = bondHelper.isBonded(deviceId);
        JSObject ret = new JSObject();
        ret.put("isBonded", isBonded);
        call.resolve(ret);
    }

    @PluginMethod
    public void startBonding(PluginCall call) {
        String deviceId = call.getString("deviceId");

        if (deviceId == null || deviceId.isEmpty()) {
            call.reject("deviceId is required");
            return;
        }

        bondHelper.startBonding(deviceId, call);
    }
}
