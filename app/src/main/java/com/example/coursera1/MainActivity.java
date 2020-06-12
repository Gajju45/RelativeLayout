package com.example.coursera1;




//Projects in Series 1:
//1. Build a Simple App in Android Studio with Java
//2. Build a Persistent Storage App in Android Studio
//3. Build a Linear Layout App in Android Studio
//4. Build a Relative Layout App in Android Studio
//5. Build a Table Layout App in Android Studio

//androidx.appcompat.app.AppCompatActivity & android.os.Bundle are put in by default when basic
//activity selected when new project is created in Android Studio. All of the other imports where
//put in manually later during the making of the project.
//Start
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
//End

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //These strings and ints will be used in our shared preferences.  You do not have to do anything with them.
    //Start
    public static final String MYPREFS = "mySharedPreferences";
    private String savedName;

    private String savedDateOfBirth;
    private String savedTimeOfBirth;
    private int dobYear;
    private int dobMonth;
    private int dobDay;
    private int dobHour;
    private int dobMinute;

    //End

    private TextView txtFirstQuestionText;
    private EditText edtName;
    protected EditText edtDateOfBirth;
    protected EditText edtTimeOfBirth;
    protected Button btnExit;
    protected Button btnSubmit;


    //**TASK**
    //Create two EditText called edtDateOfBirth and edtTimeOfBirth and two Button call btnExit and
    //btnSubmit. Make them private like txtFirstQuestionText and edtName.
    //Start: 4 lines




    //End


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtFirstQuestionText = findViewById(R.id.txtFirstQuestionText);
        edtName = findViewById(R.id.edtName);
        edtDateOfBirth=findViewById(R.id.edtDateOfBirth);
        edtTimeOfBirth=findViewById(R.id.edtTimeOfBirth);
        btnExit=findViewById(R.id.btnExit);
        btnSubmit=findViewById(R.id.btnSubmit);


        //**TASK**
        //Connect etdDateOfBirth, edtTimeOfBirth, btnExit, and btnSubmit to the correct widgets in
        //the xml the same way as txtFirstQuestionText and edtName
        //Start: 4 lines




        //End

        edtDateOfBirth.setOnClickListener(this);
        edtTimeOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating an instance of a Calendar.
                final Calendar calendar = Calendar.getInstance();
                //Taking the values in the calendar and putting them in the appropriate strings.
                dobHour = calendar.get(Calendar.HOUR_OF_DAY);
                dobMinute = calendar.get(Calendar.MINUTE);
                //Opening a Time Picker window when the user clicks inside the EditText.
                //the words in grey (context: , is24HourView:) are put in by Android Studio
                //and are not typed by a person.
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //Take the information that the user selects on the calendar and showing
                        //that information on the user interface next to Time of Birth and saving
                        //the information in the savedTimeOfBirth string in the correct format.

                        edtTimeOfBirth.setText(String.format("%02d:%02d", hourOfDay, minute));
                        savedTimeOfBirth = String.format("%02d:%02d", hourOfDay, minute);
                    }
                }, dobHour, dobMinute, true);
                timePickerDialog.show();
            }
        });
        //**TASK WITH INSTRUCTOR**
        //Make setOnClickListener for btnExit. The button will just call the finish() method when pressed.
        //Start: ~5 lines
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });






        //End

        //**TASK**
        //Make setOnClickListener for btnSubmit. The button will call savePreferences() and
        // loadPreferences() when pressed.
        //Start: ~6 lines
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
                loadPreferences();
            }
        });






        //End

        loadPreferences();
    }

    @Override
    public void onClick(View v) {
        //Creating an instance of a Calendar.
        final Calendar calendar = Calendar.getInstance();
        //Taking the values in the calendar and putting them in the appropriate strings.
        dobYear = calendar.get(Calendar.YEAR);
        dobMonth = calendar.get(Calendar.MONTH);
        dobDay = calendar.get(Calendar.DAY_OF_MONTH);

        //Opening a Date Picker window when the user clicks inside the EditText.
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        //Take the information that the user selects on the calendar and showing
                        //that information on the user interface next to Date Of Birth and saving
                        //the information in the savedDateOfBirth string.
                        //DatePickerDialog uses index 0 to 11 for month so you must add one to get
                        //month of year to have January being 1 and December being 12.
                        edtDateOfBirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        savedDateOfBirth = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                    }
                }, dobYear, dobMonth, dobDay);
        datePickerDialog.show();
    }

    //You do not have to change any code below here.  The code below here is related to Persistent
    //Storage (Shared Preferences) and Menus. We have looked at persistent storage in the Build a
    //Persistent Storage App in Android Studio project on Coursera. If you would like to know more
    //about this you can go  that course.  We have adjusted the values of the Strings beginning
    //with saved (savedName, savedDateOfBirth, and savedTimeOfBirth) to match what the user has
    //input for Name, Date Of Birth and Time of Birth when they press the Submit button or the Exit
    //button. We are using key value pairs below to remember what each value is and to update the
    //String values based on what is in the key value pairs. In the loadPreferences() we are also
    //setting what is showing on our app next to the words Name, Date of Birth and Time of Birth
    //to match what we have now put in savedName, savedDateOfBirth and savedTimeOfBirth.
    public void loadPreferences() {
        // Get the stored preferences
        int mode = Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferences = getSharedPreferences(MYPREFS, mode);
        //When you see words in grey like key: and defValue: below they are tags put in by android
        //studio.  These are not typed in by a person.
        //Retrieve the saved values and put them in savedName, savedDateOfBirth, & savedTimeOfBirth.
        savedName = mySharedPreferences.getString("name", "");
        savedDateOfBirth = mySharedPreferences.getString("date of birth", "");
        savedTimeOfBirth = mySharedPreferences.getString("time of birth", "");
        //Get the values put in the strings above and put them in the EditTexts so that they are on
        //the user interface next to Name, Date Of Birth, and Time of Birth.
        edtName.setText(savedName);
        edtDateOfBirth.setText(savedDateOfBirth);
        edtTimeOfBirth.setText(savedTimeOfBirth);
        //If the person leaves any of the fields Name, Date Of Birth, or Time Of Birth empty then
        //the text at the top of the user interface will say "Tell me more about yourself".  If the
        //user puts information in all three of the fields then the text at the top of the user
        //interface will say "NAME was born on DATE OF BIRTH at TIME OF BIRTH"
        if (savedName.equals("") || savedDateOfBirth.equals("") || savedTimeOfBirth.equals("")){
            txtFirstQuestionText.setText("Tell me more about yourself");
        }
        else {
            txtFirstQuestionText.setText(savedName + " was born on " + savedDateOfBirth + " at " + savedTimeOfBirth + ".");
        }
    }

    protected void savePreferences() {
        // Create or retrieve the shared preference object.
        int mode = Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferences = getSharedPreferences(MYPREFS, mode);
        // Retrieve an editor to modify the shared preferences.
        android.content.SharedPreferences.Editor editor = mySharedPreferences.edit();
        // Store data in the shared preferences object.
        editor.putString("name", edtName.getText().toString());
        editor.putString("date of birth", edtDateOfBirth.getText().toString());
        editor.putString("time of birth", edtTimeOfBirth.getText().toString());

        // Commit the changes.
        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.savePreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_shared_preferences_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
