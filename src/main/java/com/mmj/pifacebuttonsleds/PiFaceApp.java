package com.mmj.pifacebuttonsleds;

import java.io.IOException;

import com.pi4j.component.switches.SwitchListener;
import com.pi4j.component.switches.SwitchState;
import com.pi4j.component.switches.SwitchStateChangeEvent;
import com.pi4j.device.piface.PiFace;
import com.pi4j.device.piface.PiFaceLed;
import com.pi4j.device.piface.PiFaceSwitch;
import com.pi4j.device.piface.impl.PiFaceDevice;
import com.pi4j.wiringpi.Spi;

/**
 * @author mamejri
 */
public class PiFaceApp {

    public static PiFace piFace;

    public static void main(String[] args) {

        try {
            piFace = new PiFaceDevice(PiFace.ADDRESS_0, Spi.CHANNEL_0);
        } catch (IOException e) {

            piFace = null;
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (piFace != null) {

            piFace.getSwitch(PiFaceSwitch.S1).addListener(new SwitchListener() {
                @Override
                public void onStateChange(SwitchStateChangeEvent switchStateChangeEvent) {
                    if (switchStateChangeEvent.getNewState() == SwitchState.ON) {
                        piFace.getLed(PiFaceLed.LED0).on();
                    } else {
                        piFace.getLed(PiFaceLed.LED0).off();
                    }
                }
            });

            piFace.getSwitch(PiFaceSwitch.S2).addListener(new SwitchListener() {
                @Override
                public void onStateChange(SwitchStateChangeEvent switchStateChangeEvent) {
                    if (switchStateChangeEvent.getNewState() == SwitchState.ON) {
                        piFace.getLed(PiFaceLed.LED1).blink(200L);
                    } else {
                        piFace.getLed(PiFaceLed.LED1).blink(0);
                        piFace.getLed(PiFaceLed.LED1).off();
                    }
                }
            });
        }
    }
}
