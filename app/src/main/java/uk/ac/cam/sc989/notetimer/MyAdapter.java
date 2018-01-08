package uk.ac.cam.sc989.notetimer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Simon on 08/01/2018.
 */

public class MyAdapter extends ArrayAdapter<File> {
    private Context mContext;
    private ArrayList<File> files;

    public MyAdapter(Context context, ArrayList arr){
        super(context, R.layout.list_item_note, arr);
        mContext = context;
        files = arr;
    }

    public View getView(final int position, View view, ViewGroup parent){
        if (view == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item_note, null);
        }

        TextView displayName = (TextView) view.findViewById(R.id.fileName);
        String fileName = files.get(position).getName();
        displayName.setText(fileName.substring(0, fileName.length() - 4));

        ImageView bin = (ImageView) view.findViewById(R.id.bin);
        bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = files.get(position);
                file.delete();
                files.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

}
