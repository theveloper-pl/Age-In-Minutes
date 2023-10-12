package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var selectedCate : TextView? = null
    var selectedDateInMinutesTextView : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById<Button>(R.id.datePicker)
        datePicker.setOnClickListener { clickDatePicker() }

        selectedCate = findViewById(R.id.selected_date)
        selectedDateInMinutesTextView = findViewById(R.id.in_minutes_till_date)
    }


    fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd =         DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
//                Toast.makeText(this, "Hello world", Toast.LENGTH_LONG).show()
                val selectedDateText = "${dayOfMonth}/${month+1}/${year}"
                selectedCate?.text = selectedDateText

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDateText)

                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        selectedDateInMinutesTextView?.text = "${differenceInMinutes}"
                    }


                }

            },
            year,
            month,
            day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }


}