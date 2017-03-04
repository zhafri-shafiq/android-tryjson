package com.example.zoro175.tryjson;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadGrades(View view) {
        Resources res = getResources();
        InputStream is = res.openRawResource(R.raw.student_grades);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        parseJson(builder.toString());
    }

    private void parseJson(String s) {
        TextView txtDisplay = (TextView) findViewById(R.id.text_display);
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(s);

            JSONObject recipetype = root.getJSONObject("student-grades");

//            for (int i = 0; i < recipetype.length(); i++) {
//                JSONObject id = recipetype.getJSONObject(i);
//                builder.append(id.getInt("recipetype id"));
//            }
//
            builder.append("Name: ")
                    .append(recipetype.getString("name")).append("\n");

            builder.append("Fulltime: ")
                    .append(recipetype.getBoolean("full-time")).append("\n\n");

            JSONArray course = recipetype.getJSONArray("courses");

            for (int i = 0; i < course.length(); i++) {
                JSONObject cours = course.getJSONObject(i);
                builder.append(cours.getString("name"))
                        .append(": ")
                        .append(cours.getString("grade"))
                        .append("\n");
            }



        } catch (JSONException e) {
            e.printStackTrace();

        }


        txtDisplay.setText(builder.toString());
    }
}
