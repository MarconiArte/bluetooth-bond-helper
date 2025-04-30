import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(BluetoothBondHelperPlugin)
public class BluetoothBondHelperPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "BluetoothBondHelperPlugin"
    public let jsName = "BluetoothBondHelper"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = BluetoothBondHelper()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }
}
