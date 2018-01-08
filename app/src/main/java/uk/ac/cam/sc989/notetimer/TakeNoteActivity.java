package uk.ac.cam.sc989.notetimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;

public class TakeNoteActivity extends AppCompatActivity {
    private Button buttonStart, buttonSave;
    private EditText inputSeconds, textNote, inputFileName;
    private View secondGetter;
    private TextView displaySeconds;

    private CountDownTimer timer;
    private int secondsAllowed;
    private String content;

    private boolean isEditable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);

        isEditable = false;

        buttonStart = (Button) findViewById(R.id.start);
        inputSeconds = (EditText) findViewById(R.id.inputSeconds);
        displaySeconds = (TextView) findViewById(R.id.displaySeconds);
        textNote = (EditText) findViewById(R.id.editNote);
        secondGetter = findViewById(R.id.getSeconds);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        inputFileName = (EditText) findViewById(R.id.inputFileName);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondsAllowed = Integer.parseInt(inputSeconds.getText().toString());

                View secondGetter = findViewById(R.id.getSeconds);
                secondGetter.setVisibility(View.INVISIBLE);
                buttonStart.setVisibility(View.INVISIBLE);

                displaySeconds.setVisibility(View.VISIBLE);
                textNote.setVisibility(View.VISIBLE);

                timer = new CountDownTimer((long) secondsAllowed*1000, 1000) {
                    @Override
                    public void onTick(long milliSecondsRemaining) {
                        displaySeconds.setText(Long.toString(milliSecondsRemaining / 1000));
                        if (milliSecondsRemaining < 6000){
                            displaySeconds.setTextColor(getResources().getColor(R.color.colorRed));
                        }
                    }

                    @Override
                    public void onFinish() {
                        displaySeconds.setText("TIME UP");
                        isEditable = false;
                        content = textNote.getText().toString();
                        textNote.setVisibility(View.INVISIBLE);
                        buttonSave.setVisibility(View.VISIBLE);
                        inputFileName.setVisibility(View.VISIBLE);
                    }
                };
                timer.start();

                isEditable = true;
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile(inputFileName.getText().toString());
                finish();
            }
        });
    }

    private void saveFile(String name){
        String filename = name+".txt";

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause(){
        super.onPause();

        //saveFile();
        textNote.setText("");

        if (timer != null){
            timer.cancel();
        }

        finish();
    }
}
