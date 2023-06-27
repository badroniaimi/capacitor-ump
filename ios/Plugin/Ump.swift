import Foundation
import UserMessagingPlatform

@objc public class Ump: NSObject {
    
    //var umpForm: UMPConsentForm
    
    @objc public func initialise(_ viewController: UIViewController) -> Bool {
        print("Ump | initialise")
        
        // Create a UMPRequestParameters object.
        let parameters = UMPRequestParameters()
        // Set tag for under age of consent. Here false means users are not under age.
        parameters.tagForUnderAgeOfConsent = false
        
        // Request an update to the consent information.
        // Request an update to the consent information.
        UMPConsentInformation.sharedInstance.requestConsentInfoUpdate(
            with: parameters,
            completionHandler: { error in
              if error != nil {
                // Handle the error.
                print("Ump | initialise | Error: \(String(describing: error?.localizedDescription))")
              } else {
                // The consent information state was updated.
                print("Ump | initialise | Ready")
                
                // You are now ready to see if a form is available.
                let formStatus = UMPConsentInformation.sharedInstance.formStatus
                if formStatus == UMPFormStatus.available {
                    self.loadForm(viewController)
                }
              }
            })
        
        return true
    }
    @objc public func show(_ viewController: UIViewController, force: Bool) -> Bool {
        print("Ump | show | Force: \(force)")
        /*
        if UMPConsentInformation.sharedInstance.consentStatus == UMPConsentStatus.required || force {
        
            self.umpForm.present(
                    from: viewController,
                    completionHandler: { dismissError in
                      if UMPConsentInformation.sharedInstance.consentStatus == UMPConsentStatus.obtained {
                        // App can start requesting ads.
                      }

                    })
              } else {
                // Keep the form available for changes to user consent.
              }
 */
        
        return force
    }
    
    func loadForm(_ viewController: UIViewController) {
        print("Ump | loadForm")
        UMPConsentForm.load(
              completionHandler: { form, loadError in
                if loadError != nil {
                  // Handle the error
                    print("Ump | loadForm | Error: \(String(describing: loadError?.localizedDescription))")
                } else {
                  // Present the form
                    print("Ump | loadForm | Ready")
                    //self.umpForm = form!;
                    
                    if UMPConsentInformation.sharedInstance.consentStatus == UMPConsentStatus.required {
                    
                        form?.present(
                                from: viewController,
                                completionHandler: { dismissError in
                                  if UMPConsentInformation.sharedInstance.consentStatus == UMPConsentStatus.obtained {
                                    // App can start requesting ads.
                                    
                                    // Fire a notification
                                    NotificationCenter.default.post(name: Notification.Name("UMPConsentStatusObtained"), object: nil)
                                    
                                  }

                                })
                        
                    }
                    
                }
              })
    }
    
}
