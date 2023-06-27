package com.poptacular.plugins.ump;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "Ump")
public class UmpPlugin extends Plugin {

    private Ump implementation = new Ump();

    @PluginMethod
    public void initialise(PluginCall call) {
        implementation.initialise(this.bridge.getActivity());
        call.resolve();
    }
}
