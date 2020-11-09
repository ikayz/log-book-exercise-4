package com.ikayz.irateapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText restaurantName = findViewById(R.id.restaurant_name_txt);
        EditText dateTime = findViewById(R.id.date_time);
        EditText mealPrice = findViewById(R.id.price);
        RatingBar serviceRating = findViewById(R.id.rating_bar_service);
        RatingBar cleanlinessRating = findViewById(R.id.rating_bar_cleanliness);
        RatingBar foodQualityRating = findViewById(R.id.rating_bar);
        EditText reporterName = findViewById(R.id.edit_reporter);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.restaurant_spinner);
        Button submitReview = findViewById(R.id.btn_submit);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.restaurant_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Init Validation
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // Field validations
        awesomeValidation.addValidation(this, R.id.restaurant_name_txt,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.date_time,
                RegexTemplate.NOT_EMPTY, R.string.invalid_date);
        awesomeValidation.addValidation(this, R.id.price,
                RegexTemplate.NOT_EMPTY, R.string.invalid_price);
        awesomeValidation.addValidation(this, R.id.rating_bar_service,
                RegexTemplate.NOT_EMPTY, R.string.invalid_rating_service);
        awesomeValidation.addValidation(this, R.id.rating_bar_cleanliness,
                RegexTemplate.NOT_EMPTY, R.string.invalid_rating_cleanliness);
        awesomeValidation.addValidation(this, R.id.rating_bar,
                RegexTemplate.NOT_EMPTY, R.string.invalid_rating_food);
        awesomeValidation.addValidation(this, R.id.edit_reporter,
                RegexTemplate.NOT_EMPTY, R.string.invalid_reporter_name);

        submitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(awesomeValidation.validate()) {
                    Toast.makeText(MainActivity.this, "Review added successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Validation failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}