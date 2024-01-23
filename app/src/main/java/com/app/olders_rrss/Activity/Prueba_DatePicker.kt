package com.app.olders_rrss.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.olders_rrss.databinding.ActivityPruebaDatePickerBinding
import java.util.Calendar

class Prueba_DatePicker : AppCompatActivity() {

    lateinit var binding: ActivityPruebaDatePickerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPruebaDatePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datePicker = binding.datePicker
        val today = Calendar.getInstance()
        today.set(Calendar.YEAR, 1950)
        today.set(
            Calendar.MONTH,
            7
        ) // En Calendar, el mes comienza desde 0 (enero) hasta 11 (diciembre)
        today.set(Calendar.DAY_OF_MONTH, 15)

        datePicker.init(
            today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)

        ) { view, year, month, day ->
            val mes = month + 1
            val msg = "You Selected: $day/$mes/$year"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }
}
