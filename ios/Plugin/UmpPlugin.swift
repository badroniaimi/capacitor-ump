import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(UmpPlugin)
public class UmpPlugin: CAPPlugin {
    private let implementation = Ump()

    @objc func initialise(_ call: CAPPluginCall) {
        call.resolve([
            "value": implementation.initialise((self.bridge?.viewController)!)
        ])
    }

    @objc func show(_ call: CAPPluginCall) {
        let value = call.getBool("value") ?? false
        call.resolve([
            "value": implementation.show((self.bridge?.viewController)!, force: value)
        ])
    }
}
