package uk.ac.cam.sc989.notetimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonNewNote, buttonViewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonNewNote = (Button) findViewById(R.id.buttonNewNote);
        buttonViewNote = (Button) findViewById(R.id.buttonViewNote);

        buttonNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newNote = new Intent(MainActivity.this, TakeNoteActivity.class);
                startActivity(newNote);
            }
        });

        buttonViewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickNote = new Intent(MainActivity.this, SelectNoteActivity.class);
                startActivity(pickNote);
            }
        });
    }
}
