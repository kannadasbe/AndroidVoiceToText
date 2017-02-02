package com.crazygeeksblog.androidspeechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechToTextActivity extends AppCompatActivity {
    private static final int REQUEST_VOICE_INPUT = 101;
    private TextView tvConvertedText;
    private ImageButton btnVoiceInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        tvConvertedText = (TextView) findViewById(R.id.tvConvertedText);
        btnVoiceInput = (ImageButton) findViewById(R.id.btnSpeak);
        btnVoiceInput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent speechIntent =
                        new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                        "How can I help you?");
                try {
                    startActivityForResult(speechIntent, REQUEST_VOICE_INPUT);
                } catch (ActivityNotFoundException a) {

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_VOICE_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> resultData =
                            data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvConvertedText.setText(resultData.get(0));
                }
                break;
            }
        }
    }
}
