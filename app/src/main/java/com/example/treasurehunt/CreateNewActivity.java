package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateNewActivity extends AppCompatActivity {
    private boolean is_hotel = false;
    private boolean is_roadtrip = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        Intent intent = getIntent();

        Button button = (Button) findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = CreateNewActivity.this;
                String[] files = context.fileList();
                String filename = "vacation" + files.length;
                String fileContents = "";
                if (is_hotel) fileContents += "Hotel\n";
                if (is_roadtrip) fileContents += "Road trip\n";
                try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
                    fos.write(fileContents.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_hotel:
                is_hotel = checked;
                break;
            case R.id.checkbox_roadtrip:
                is_roadtrip = checked;
                break;
        }
    }

}