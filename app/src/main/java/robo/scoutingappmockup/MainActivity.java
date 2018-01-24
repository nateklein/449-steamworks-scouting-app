package robo.scoutingappmockup;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.Set;

public class MainActivity extends Activity {

    public static SteamworksDatabase db;
    public static BluetoothAdapter adapter;
    public static Set<BluetoothDevice> pairedDevices;
    public static boolean bluetooth;

    // Calls activity to go to prematch pages
    // and creates a SteamworksDatabase object
    // This activity is automatically called when the app is opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SteamworksDatabase();
        adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            bluetooth = false;
            Log.i("onCreate", "no bluetooth");
        }
        else {
            bluetooth = true;
            if (!adapter.isEnabled()) {
                adapter.enable();
            }
            if (adapter.isDiscovering()) {
                adapter.cancelDiscovery();
            }
        }
        for (BluetoothDevice d : adapter.getBondedDevices()) {
            unpairDevice(d);
        }
        Intent initPrematch = new Intent(this, goToPrematch.class);
        startActivity(initPrematch);
    }

    public static void list() {
        pairedDevices = adapter.getBondedDevices();
        String pairedDevicesString = "Devices: ";
        for (BluetoothDevice d : pairedDevices) {
            pairedDevicesString += d.toString();
        }
        Log.i("onCreate", pairedDevicesString);
    }

    private void unpairDevice(BluetoothDevice device) {
        try {
            Method m = device.getClass()
                    .getMethod("removeBond", (Class[]) null);
            m.invoke(device, (Object[]) null);
        } catch (Exception e) {
            Log.i("onCreate", "unpair failed");
        }
    }
}