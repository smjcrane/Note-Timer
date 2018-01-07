package uk.ac.cam.sc989.notetimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TakeNoteActivity extends AppCompatActivity {
    private Button buttonStart;
    private EditText inputSeconds;
    private View secondGetter;
    private TextView displaySeconds;
    private EditText textNote;

    private CountDownTimer timer;
    private int secondsAllowed;

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


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secondsAllowed = Integer.parseInt(inputSeconds.getText().toString());

                View secondGetter = findViewById(R.id.getSeconds);
                secondGetter.setVisibility(View.INVISIBLE);
                buttonStart.setVisibility(View.INVISIBLE);

                displaySeconds.setVisibility(View.VISIBLE);
                textNote.setVisibility(View.VISIBLE);

                new CountDownTimer((long) secondsAllowed*1000, 1000) {
                    @Override
                    public void onTick(long milliSecondsRemaining) {
                        displaySeconds.setText(Long.toString(milliSecondsRemaining / 1000));
                    }

                    @Override
                    public void onFinish() {
                        isEditable = false;
                        textNote.setVisibility(View.INVISIBLE);
                        displaySeconds.setText("TIME UP");
                    }
                }.start();

                isEditable = true;
            }
        });
    }
}
