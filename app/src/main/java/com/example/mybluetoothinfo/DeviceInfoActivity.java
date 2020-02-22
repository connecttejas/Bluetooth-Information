package com.example.mybluetoothinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class DeviceInfoActivity extends AppCompatActivity {

    TextView name,addr,type,btclass,state,uuid,textViewvalue,textViewcodHex,textViewcodBinary,textViewcodBits;

    String value,codHex,codBinary,codBits,devicetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        name    =   findViewById(R.id.name);
        addr    =   findViewById(R.id.address);
        type    =   findViewById(R.id.type);
        btclass    =   findViewById(R.id.btclass);
        state    =   findViewById(R.id.state);
        uuid    =   findViewById(R.id.uuid);
        textViewvalue    =   findViewById(R.id.textViewvalue);
        textViewcodHex    =   findViewById(R.id.textViewcodHex);
        textViewcodBinary    =   findViewById(R.id.textViewcodBinary);
        textViewcodBits    =   findViewById(R.id.textViewcodBits);




        getBondStateDetails();
        getClassDetails();
        getrealType();

       // position.setText(getIntent().getExtras().getString("Position"));
        name.setText(getIntent().getExtras().getString("DeviceName"));
        addr.setText(getIntent().getExtras().getString("DeviceAddr"));
        uuid.setText(getIntent().getExtras().getString("DeviceuuidList"));
        btclass.setText(codHex);
        type.setText(devicetype);
        state.setText(value);
        textViewcodBinary.setText(codBinary);
        textViewcodBits.setText(codBits);
        textViewcodHex.setText(codHex);







    }

    private void getBondStateDetails() {
        value    =   getIntent().getExtras().getString("DevicebondStateList");
        switch (value){
            case "12" :
                value = "12 (0x0000000c)~bonded (paired)";
                break;
            case "11" :
                value = "11 (0x0000000b)~(pairing)";
                break ;
            case "10" :
                value = "10 (0x0000000a)~not bonded (paired).";
                break ;
            default:
               value    =   "NA";
                break;

        }
    }

    private void getClassDetails() {
        codHex = getIntent().getExtras().getString("DevicebluetoothClassList");
        codBinary=null;
        codBits = null;
        switch (codHex){
            case "240408":
                codHex = "0x240408";
                codBinary= "001001000000010000001000";
                codBits =   "\t21,18 | 10 | 3Major Service Class\n" +
                        "CoD Bit 21: Audio (Speaker, Microphone, Headset service, …)\n" +
                        "CoD Bit 18: Rendering (Printing, Speaker, …)\n" +
                        "Major Device Class\n" +
                        "CoD Bits 10: Audio/Video (headset,speaker,stereo, video display, vcr…)\n" +
                        "Minor Device Class\n" +
                        "CoD Bit 3: Hands-free Device";
                break;
            case "340408":
                codHex = "0x340408";
                codBinary= "001101000000010000001000";
                codBits =   "21,20,18 | 10 | 3Major Service Class\n" +
                        "CoD Bit 21: Audio (Speaker, Microphone, Headset service, …)\n" +
                        "CoD Bit 20: Object Transfer (v-Inbox, v-Folder, …)\n" +
                        "CoD Bit 18: Rendering (Printing, Speaker, …)\n" +
                        "Major Device Class\n" +
                        "CoD Bits 10: Audio/Video (headset,speaker,stereo, video display, vcr…)\n" +
                        "Minor Device Class\n" +
                        "CoD Bits 3: Hands-free Device";
                break;
            case "760408":
                codHex = "0x760408";
                codBinary= "011101100000010000001000";
                codBits =   "13 | none | 7,6,5,4,3,2Major Service Class\n" +
                        "CoD Bit 22: Telephony (Cordless telephony, Modem, Headset service, …)\n" +
                        "CoD Bit 21: Audio (Speaker, Microphone, Headset service, …)\n" +
                        "CoD Bit 20: Object Transfer (v-Inbox, v-Folder, …)\n" +
                        "CoD Bit 19: Capturing (Scanner, Microphone, …)\n" +
                        "CoD Bit 18: Rendering (Printing, Speaker, …)\n" +
                        "CoD Bit 17: Networking (LAN, Ad hoc, …)\n" +
                        "Major Device Class\n" +
                        "CoD Bits 10: Audio/Video (headset,speaker,stereo, video display, vcr…..)\n" +
                        "Minor Device Class\n" +
                        "CoD Bits 3: Hands-free Device";
                break;
            case "5a020c":
                codHex= "0x5a020c";
                codBinary= "010110100000001000001100";
                codBits =   "22,20,19,17 | 9 | 3Major Service Class\n" +
                        "CoD Bit 22: Telephony (Cordless telephony, Modem, Headset service, …)\n" +
                        "CoD Bit 20: Object Transfer (v-Inbox, v-Folder, …)\n" +
                        "CoD Bit 19: Capturing (Scanner, Microphone, …)\n" +
                        "CoD Bit 17: Networking (LAN, Ad hoc, …)\n" +
                        "Major Device Class\n" +
                        "CoD Bits 9: Phone (cellular, cordless, payphone, modem, …)\n" +
                        "Minor Device Class\n" +
                        "CoD Bits 2,3: Smart phone";
                break;
            case "240404":
                 codHex="0x240404";
                 codBinary="001001000000010000000100";
                 codBits    =   "\t21,18 | 10 | 2Major Service Class\n" +
                         "CoD Bit 21: Audio (Speaker, Microphone, Headset service, …)\n" +
                         "CoD Bit 18: Rendering (Printing, Speaker, …)\n" +
                         "Major Device Class\n" +
                         "CoD Bits 10: Audio/Video (headset,speaker,stereo, video display, vcr…)\n" +
                         "Minor Device Class\n" +
                         "CoD Bits 2: Wearable Headset Device";
            default:
                codBinary = "NA";
                codBits = "NA";

                break;
        }

    }

    private void getrealType() {

        devicetype =getIntent().getExtras().getString("DeviceTypeList");
        switch (devicetype){
            case "1":
                devicetype = "Classic - BR/EDR devices";
                break;
            case "2":
                devicetype = "Low Energy - LE-only";
                break;
            case "3":
                devicetype = "Dual Mode - BR/EDR/LE";
                break;
            default:
                devicetype = "Unknown";
                break;
        }


    }
}
/*
intent.putExtra("Position", String.valueOf(position));
        intent.putExtra("DeviceName",nameList[position]);
        intent.putExtra("DeviceAddr",addrList[position]);
        intent.putExtra("DeviceTypeList",typeList[position]);
        intent.putExtra("DevicebluetoothClassList",bluetoothClassList[position]);
        intent.putExtra("DevicebondStateList",bondStateList[position]);
        intent.putExtra("DeviceuuidList",uuidList[position]);*/
