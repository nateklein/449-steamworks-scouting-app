package robo.scoutingappmockup;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.UUID;

public class submit extends Activity {

    private BluetoothDevice device;
    private BluetoothSocket socket;
    private UUID uuid = UUID.fromString("0C1ABDD7-436F-79E0-7152-4D91528DA3D1");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!MainActivity.db.checkData().equals("")) {
            Intent viewErrors = new Intent(this, fixErrors.class);
            startActivity(viewErrors);
        }
        else {
            try {
                // Writes data to files
                File root = Environment.getExternalStorageDirectory();
                File dir = new File (root.getAbsolutePath() + "/download");
                dir.mkdirs();
                // One line file - rewritten every match
                File data = new File(dir, "data.csv");
                PrintWriter writer = new PrintWriter(data);
                // File with all matches from the current Kindle - appended to every match
                BufferedWriter allWriter = new BufferedWriter(new FileWriter(root.getAbsolutePath() + "/download/" + "alldata.csv", true));
                String csvString = MainActivity.db.toString();
                writer.println(csvString);
                allWriter.append(csvString + "\n");
                writer.close();
                allWriter.close();

                if (MainActivity.bluetooth) {
                    // Start discovering devices
                    MainActivity.adapter.startDiscovery();
                    // Find the device we want to connect to
                    socket = device.createRfcommSocketToServiceRecord(uuid);
                    Log.i("onCreate", device.toString());
                    try {
                        socket.connect();
                        Log.i("onCreate", "you finally did it");
                        OutputStream ops = socket.getOutputStream();
                        ops.write(csvString.getBytes());
                    } catch (Exception e) {
                        Log.i("onCreate", Log.getStackTraceString(e));
                    }
                }

                // Go to confirmation page
                Intent toSubmitted = new Intent(this, submitted.class);
                startActivity(toSubmitted);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}