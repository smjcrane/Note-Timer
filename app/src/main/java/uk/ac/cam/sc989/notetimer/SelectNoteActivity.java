package uk.ac.cam.sc989.notetimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectNoteActivity extends AppCompatActivity {
    public static Map<Integer, File> fileMap;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_note);

        listView = (ListView) findViewById(R.id.listOfNotes);

        File[] files = getFilesDir().listFiles();

        ArrayList<File> names = new ArrayList<>();

        fileMap = new HashMap<>();


        for (int i = 0; i < files.length; i++){
            File f = files[i];
            String name = f.getName();
            if (name.substring(name.length() - 4).equals(".txt")){
                names.add(f);
                fileMap.put(i, f);
            }
        }


        MyAdapter adapter = new MyAdapter(this, names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showNote = new Intent(SelectNoteActivity.this, ViewNoteActivity.class);
                showNote.putExtra("POSITION", i);
                startActivity(showNote);
                //finish();
            }
        });

    }
}
