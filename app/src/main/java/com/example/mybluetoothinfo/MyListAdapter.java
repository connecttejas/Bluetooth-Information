package com.example.mybluetoothinfo;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] name;
    private final String[] address;
  //  private final Integer[] imgid;

  public MyListAdapter(Activity context, String[] name,String[] address) {
      super(context, R.layout.row_layout, name);

      this.context=context;
      this.name=name;
      this.address=address;


  }


/*
    public MyListAdapter(Activity context, String[] name,String[] address, Integer[] imgid) {
        super(context, R.layout.row_layout, name);

        this.context=context;
        this.name=name;
        this.address=address;
       // this.imgid=imgid;

    }
*/

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.row_layout, null,true);

        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView addressText = (TextView) rowView.findViewById(R.id.address);

        nameText.setText(name[position]);
        //imageView.setImageResource(imgid[position]);
        addressText.setText(address[position]);

        return rowView;

    };
}