package com.example.habbitreminderapp.Core.Features

import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@Composable
fun MyTimePicker(onTimeSelected: (String) -> Unit, onYesClicked: () -> Unit, onNoClicked:()->Unit) {
    val context = LocalContext.current
    val mCalendar = Calendar.getInstance()
    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    TimePickerDialog(
        context,
        { _, mHour: Int, mMinute: Int ->
            // Formatear la hora seleccionada como cadena de texto (HH:mm)
            val formattedHour = mHour.toString().padStart(2, '0')
            val formattedMinute = mMinute.toString().padStart(2, '0')
            val selectedTime = "$formattedHour:$formattedMinute"
            // Llamar al callback con la hora seleccionada
            onTimeSelected(selectedTime)
        },
        mHour,
        mMinute,
        false
    ).apply {
        setButton(TimePickerDialog.BUTTON_POSITIVE, "Confirmar") { _, _ ->
            // Llamar a la función de devolución de llamada cuando se hace clic en "Yes"
            onYesClicked()
        }
        setButton(TimePickerDialog.BUTTON_NEGATIVE, "Volver") { _, _ ->
            // Llamar a la función de devolución de llamada cuando se hace clic en "No"
            onNoClicked()
        }
        setOnDismissListener { onNoClicked() }
    }.show()
}
