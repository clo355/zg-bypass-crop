package com.example.cyl.psc_pristine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
     *    Use this to bypass some app forcing a crop request that blurs your image.
     *
     *    -Any square JPG with a resolution under 2400x2400px and 3.4MB should work.
     *    -Rename your JPG to screenshot.jpg and place it here:
     *       file:///storage/emulated/0/screenshot.jpg
     *    -Choose this app when android displays the "choose a crop app" list
     *    -This crop app should auto-close and your jpg is sent to the servers
     *     without cropping.
     *    -Ta-dah! The uploaded image is crystal clear.
     *
     *    Need:
     *    -ask for external_write permission on install to access storage/emulated/0/
     *
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

	//I just took the incoming extras and sent them back
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
