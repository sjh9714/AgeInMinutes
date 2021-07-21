package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_date.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val selectedDate = "${year}/${month + 1}/${dayOfMonth}"
                tvSelected_date.setText(selectedDate)

                val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREAN)

                val theDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = theDate.time!! / 1000 / 60

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToMinutes = currentDate!!.time / 1000 / 60

                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes

                result.setText(differenceInMinutes.toString())
            },
            year,
            month,
            day
        )

        dpd.datePicker.setMaxDate(Date().time - (1000 * 60 * 60 *24))
        dpd.show()
    }
}