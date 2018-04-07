package com.example.cyl.psc_pristine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
    Use this to bypass the forced crop that blurs your image.

    How to use:
    -Any square JPG with a resolution under 2400x2400px and 3.4MB should work.
    -Rename your JPG to screenshot.jpg and place it here:
        file:///storage/emulated/0/screenshot.jpg
    -In ZG, go to upload an image. Tap "Upload Avatar", then "Check Albums".
    -Look for your screenshot.jpg and choose it.
    -ZG will now ask you pick a crop app from a list. Choose psc-pristine.
    -It should auto-close and your jpg is sent to ZG without cropping.
    -Ta-dah! I hope your profile image is now crystal clear.

    Need:
    -ask for external_write permission on install
    */

    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);
        mainText.setText("Hi.");

        /*
        Intent myIntent = getIntent();

        Bundle bundle = myIntent.getExtras();
        if (bundle != null) {
            for (String key : bundle.keySet()) {
                Object value = bundle.get(key);
                String appendThis = "\n\n" + key + "..." +
                        value.toString() + "..." + value.getClass().getName();
                mainText.setText(mainText.getText() + appendThis);
            }
        }
        */

        String output = "file:///storage/emulated/0/screenshot.jpg";
        Uri outputUri = Uri.parse(output);

        Bundle extras = new Bundle();
        Intent intent = new Intent(output);

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
}
