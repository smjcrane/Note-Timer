package uk.ac.cam.sc989.notetimer;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ViewNoteActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);

        textView = (TextView) findViewById(R.id.textViewNote);

        Intent caller = getIntent();
        int position = caller.getIntExtra("POSITION", -1);

        String line = "Could not get file contents";

        try {
            line = getFileContents(SelectNoteActivity.fileMap.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }

        textView.setText(line);
    }


    public String getFileContents(File file) throws IOException {
        final InputStream inputStream = new FileInputStream(file);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        final StringBuilder stringBuilder = new StringBuilder();

        boolean done = false;

        while (!done) {
            final String line = reader.readLine();
            done = (line == null);

            if (line != null) {
                stringBuilder.append(line+"\n");
            }
        }

        reader.close();
        inputStream.close();

        return stringBuilder.toString();
    }
}
