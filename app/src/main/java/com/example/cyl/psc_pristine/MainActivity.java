package com.example.cyl.psc_pristine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     *    Use this to bypass some app forcing a crop request that blurs your image.
     *
     */

    TextView mainText, instructionsText;
    Button useButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);
        instructionsText = findViewById(R.id.instructionsText);
        String mainDisplayThis = "Welcome to PSc-Pristine.\n" +
                "Place a square JPG named screenshot.jpg here:\n" +
                "  file:///storage/emulated/0/screenshot.jpg\n\n" +
                "This is just your top-level internal storage\n" +
                "where you should also see your Download, DCIM, and Pictures folders.\n" +
                "Don't put screenshot.jpg inside your Pictures folder!\n" +
                "This app only works after choosing it from the crop-app list in ZG.";
        mainText.setText(mainDisplayThis);
        String instructionsDisplayThis = "Instructions:\n"+
                "1. Place screenshot.jpg at the directory mentioned above.\n" +
                "2. When ZG asks you to choose an image, pick anything.\n" +
                "   It's ignored.\n" +
                "3. Next, when asked to choose a cropping app, pick PSc-Pristine.\n" +
                "4. Press the green check button. Done!" +
                "   Restart ZG to update your profile image immediately.";
        instructionsText.setText(instructionsDisplayThis);

        useButton = findViewById(R.id.useButton);
        cancelButton = findViewById(R.id.cancelButton);

        final String output = "file:///storage/emulated/0/screenshot.jpg";
        final Uri outputUri = Uri.parse(output);

        final Bundle extras = new Bundle();
        final Intent intent = new Intent(output);

        useButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent.putExtras(extras);
                intent.putExtra("return-data", false);
                intent.putExtra("outputX", 90);
                intent.putExtra("outputY", 90);
                intent.putExtra("output", outputUri);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputFormat", "JPEG");
                intent.putExtra("crop", true);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
