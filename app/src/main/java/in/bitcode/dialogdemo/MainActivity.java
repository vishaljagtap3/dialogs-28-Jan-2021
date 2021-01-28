package in.bitcode.dialogdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtnAlert, mBtnTime, mBtnDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mBtnAlert = findViewById(R.id.btnAlert);
        mBtnDate = findViewById(R.id.btnDate);
        mBtnTime = findViewById(R.id.btnTime);

        mBtnAlert.setOnClickListener(new BtnAlertClickListener());
        mBtnDate.setOnClickListener(new BtnDateClickListener());
        mBtnTime.setOnClickListener(new BtnTimeClickListener());
    }

    private class BtnTimeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            TimePickerDialog timePickerDialog =
                    new TimePickerDialog(
                            MainActivity.this,
                            new MyOnTimeSetListener(),
                            22,
                            44,
                            true
                    );
            timePickerDialog.show();

        }
    }

    private class MyOnTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker timePicker, int hrs, int mins) {
            mBtnTime.setText(hrs + " : " + mins);
        }
    }

    private class BtnDateClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            DatePickerDialog datePickerDialog =
                    new DatePickerDialog(
                            MainActivity.this,
                            new MyOnDateSetListener(),
                            2021,
                            0,
                            28
                    );
            datePickerDialog.show();

        }
    }

    private class MyOnDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mBtnDate.setText(year + " - " + (month+1) + " - " + day);
        }
    }

    private class BtnAlertClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("BitCode");
            builder.setMessage("Are you sure, to exit?");
            builder.setIcon(R.mipmap.ic_launcher);

            DialogBtnClickListener listener = new DialogBtnClickListener();

            builder.setPositiveButton("Yes", listener);
            builder.setNegativeButton("No", listener);
            builder.setNeutralButton("Cancel", listener);

            builder.setCancelable(true);

            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mt("Dialog is dismissed...");
                }
            });


            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    mt("Dialog is cancelled....");
                }
            });


            builder.create().show();

            /*AlertDialog alertDialog = builder.create();
            alertDialog.show();*/
        }
    }

    private class DialogBtnClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            if(which == DialogInterface.BUTTON_POSITIVE) {
                mt("You said yes");
            }
            if(which == DialogInterface.BUTTON_NEGATIVE) {
                mt("You said NO");
            }
            if(which == DialogInterface.BUTTON_NEUTRAL) {
                mt("You said Cancel");
            }
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}