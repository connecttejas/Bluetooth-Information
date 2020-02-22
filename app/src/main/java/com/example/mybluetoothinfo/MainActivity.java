package com.example.mybluetoothinfo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    BluetoothAdapter bluetoothAdapter;


    String[] nameList;
    String[] addrList;
    String[] typeList;
    String[] bluetoothClassList;
    String[] bondStateList;
    String[] uuidList;
   Integer[] imgIdList;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bluetoothAdapter    =   BluetoothAdapter.getDefaultAdapter();


            permissionCheck();
            enableBluetooth();
            setPairedDeviceList();

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                  Intent intent = new Intent(MainActivity.this, DeviceInfoActivity.class);
                    intent.putExtra("Position", String.valueOf(position));
                    intent.putExtra("DeviceName",nameList[position]);
                    intent.putExtra("DeviceAddr",addrList[position]);
                    intent.putExtra("DeviceTypeList",typeList[position]);
                    intent.putExtra("DevicebluetoothClassList",bluetoothClassList[position]);
                    intent.putExtra("DevicebondStateList",bondStateList[position]);
                    intent.putExtra("DeviceuuidList",uuidList[position]);

                    startActivity(intent);
                }
            });



    }

    private void setPairedDeviceList() {
        Set<BluetoothDevice> pairedDevices =bluetoothAdapter.getBondedDevices();
        nameList    =   new String[pairedDevices.size()];
        addrList    =   new String[pairedDevices.size()];
        typeList    =   new String[pairedDevices.size()];
        bluetoothClassList    =   new String[pairedDevices.size()];
        bondStateList    =   new String[pairedDevices.size()];
        uuidList    =   new String[pairedDevices.size()];

        imgIdList    =   new Integer[pairedDevices.size()];
        int index = 0;

        for (BluetoothDevice bt : pairedDevices){
            nameList[index]=bt.getName();
            addrList[index]=bt.getAddress();
            typeList[index]= String.valueOf(bt.getType());
            bluetoothClassList[index]= String.valueOf(bt.getBluetoothClass());
            bondStateList[index]=String.valueOf(bt.getBondState());
            uuidList[index]=String.valueOf(bt.getUuids()[0].getUuid());

            /*
            String info = bt.getName()+bt.getAddress()+"   "+bt.getBluetoothClass()+"   "+bt.getBondState();
            Log.i("happy",info);
            Log.i("happy", String.valueOf(bt.getUuids()[0].getUuid()));
            Log.i("happy",String.valueOf(bt.getType()));
            Log.i("happy","***************************");*/

            index++;
        }

        MyListAdapter adapter=new MyListAdapter(this, nameList,addrList);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void permissionCheck() {
        // Quick permission check
        int permissionCheck = this.checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
        permissionCheck += this.checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
        if (permissionCheck != 0) {

            this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
        }
    }

    private void enableBluetooth() {
        if (bluetoothAdapter==null){
            Toast.makeText(getApplicationContext(),"Does not support this device",Toast.LENGTH_LONG).show();
        }else {
            if (!bluetoothAdapter.isEnabled()){
                Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(bluetoothIntent,1);
            }
        }
    }




}



/*




    Integer[] imgid={
            R.drawable.infocap,R.drawable.infocap,
            R.drawable.infocap,R.drawable.infocap,
            R.drawable.infocap,
    };



*/