Imagine an old system expects a charger with this method:

chargeWithRoundPin()

But the new charger has a different method:

chargeWithUsbC()

The adapter converts the new charger interface into the old expected interface.

// Target interface
// This is what the old system expects
interface RoundPinCharger {
    void chargeWithRoundPin();
}
// Adaptee
// This is the new class with a different interface
class UsbCCharger {

    public void chargeWithUsbC() {
        System.out.println("Charging using USB-C charger");
    }
}
// Adapter
// Converts UsbCCharger into RoundPinCharger
class ChargerAdapter implements RoundPinCharger {

    private UsbCCharger usbCCharger;

    public ChargerAdapter(UsbCCharger usbCCharger) {
        this.usbCCharger = usbCCharger;
    }

    @Override
    public void chargeWithRoundPin() {
        System.out.println("Adapter converts round-pin request to USB-C request");
        usbCCharger.chargeWithUsbC();
    }
}
// Client
class MobilePhone {

    private RoundPinCharger charger;

    public MobilePhone(RoundPinCharger charger) {
        this.charger = charger;
    }

    public void chargePhone() {
        charger.chargeWithRoundPin();
    }
}
// Main class
public class AdapterPatternDemo {

    public static void main(String[] args) {

        UsbCCharger usbCCharger = new UsbCCharger();

        RoundPinCharger adapter = new ChargerAdapter(usbCCharger);

        MobilePhone phone = new MobilePhone(adapter);

        phone.chargePhone();
    }
}

Output:

Adapter converts round-pin request to USB-C request
Charging using USB-C charger

In this example:

RoundPinCharger = target interface expected by client
UsbCCharger = existing incompatible class
ChargerAdapter = adapter that makes UsbCCharger compatible
MobilePhone = client

The MobilePhone only knows about RoundPinCharger, but because of the adapter, it can still use UsbCCharger.
