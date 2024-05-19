package com.example.habbitreminderapp.NewTaskView.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.Domain.AddTaskUseCase
import com.example.habbitreminderapp.Model.data.TaskModel
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class NewTaskScreenViewModel @Inject constructor(private val addTaskUseCase: AddTaskUseCase) :
    ViewModel() {

    private var _nameTask = MutableLiveData<String>()
    val nameTask: LiveData<String> = _nameTask

    private var _descriptionTask = MutableLiveData<String>()
    val descriptionTask: LiveData<String> = _descriptionTask

    private var _colorTask = MutableLiveData<String>()
    val colorTask: LiveData<String> = _colorTask

    private var _dateTask = MutableLiveData<Long>()
    val dateTask: LiveData<Long> = _dateTask

    private var _marginTask = MutableLiveData<Long>(0L)
    val marginTask: LiveData<Long> = _marginTask

    private var _doneTask = MutableLiveData<Int>()
    val doneTask: LiveData<Int> = _doneTask

    private var _categoryIdTask = MutableLiveData<Int>()
    val categoryIdTask: LiveData<Int> = _categoryIdTask

    private var _task=MutableLiveData<TaskModel>()
    val task:LiveData<TaskModel> =_task

    private var _openCalendar=MutableLiveData<Boolean>()
    val openCalendar:LiveData<Boolean> =_openCalendar



    fun showCalendar(show:Boolean){
        _openCalendar.value=show
    }

    private var _fechaUi = MutableLiveData<String>()
    val fechaUi: LiveData<String> = _fechaUi

    fun onNameChanged(text: String) {
        _nameTask.value = text
    }

    fun onDescriptionChanged(text: String) {
        _descriptionTask.value = text
    }

    fun stringToLong(fechaString: String) {
        _fechaUi.value=fechaString
        // Define el formato de fecha esperado
        val formato = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("es", "ES")) // Para español de España
        formato.timeZone = TimeZone.getDefault()
        try {
            // Parsea la cadena de fecha al formato especificado
            val date = (formato.parse(fechaString)?.time?.div(1000)) ?: 0L
            Log.i("Calendario5", date.toString())
           _dateTask.value=date
        } catch (e: Exception) {
            // En caso de error, retorna 0

            e.printStackTrace()

        }
    }



    fun minToLong(minutosPar: String): Long {
        if (minutosPar.isNotEmpty()) {
            val minutos = minutosPar.toLongOrNull()
            if (minutos != null) {
                return minutos * 60
            }
        }
        return 0
    }

    fun hourToLong(horasPar: String): Long {
        if (horasPar.isNotEmpty()) {
            val horas = horasPar.toLongOrNull()
            if (horas != null) {
                return horas * 60 * 60
            }
        }
        return 0
    }

    fun dayToLong(diasPar: String): Long {
        if (diasPar.isNotEmpty()) {
            val dias = diasPar.toLongOrNull()
            if (dias != null) {
                return dias * 24 * 60 * 60
            }
        }
        return 0
    }


    fun addTask(minutos:String,horas: String,dias: String) {
        val margen= (minToLong(minutos) + hourToLong(horas) + dayToLong(dias))
        _task.value = TaskModel(
            id = 0,
            nombre = _nameTask.value ?: "",
            color = "",
            descripcion = _descriptionTask.value ?: "",
            fecha = _dateTask.value ?: 0L,
            margen = margen,
            proximaFecha = _dateTask.value!! + margen,
            cumplida = 0,
            categoriaId = 1
        )


        viewModelScope.launch {

            _task.value?.let { addTaskUseCase(it) }

        }

        _nameTask.value=""
        _descriptionTask.value=""
        _fechaUi.value=""

    }
}