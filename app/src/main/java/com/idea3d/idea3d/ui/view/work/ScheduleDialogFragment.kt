package com.idea3d.idea3d.ui.view.work

import android.annotation.SuppressLint
import android.os.Build
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentScheduleDialogBinding
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarView
import ru.cleverpumpkin.calendar.extension.getColorInt
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ScheduleDialogFragment(private val onClick:OnDateClick) :
    BottomSheetDialogFragment() {
    private var _binding: FragmentScheduleDialogBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

    private val calendar: Calendar = Calendar.getInstance()
    var preselectedDates: MutableList<CalendarDate> = mutableListOf<CalendarDate>()
    var worksDate: MutableList<CalendarDate> = mutableListOf<CalendarDate>()
    var eventsDate: MutableList<String> = mutableListOf()

    private val today = CalendarDate(calendar.time)
    private lateinit var c: Calendar
    private lateinit var df: SimpleDateFormat
    private lateinit var formattedDate: String
    private var list: MutableList<Task> = mutableListOf()
    private var entireMonth = true
    private var isToday = true
    var search = ""
    val firstDayOfWeek = Calendar.MONDAY


    @RequiresApi(VERSION_CODES.O)
    var minDate = getFirstDayOfMonth(calendar)

    //obtener ultimo dia del mes
    var maxDate = getLastDayOfMonth(calendar)


    interface OnDateClick {
        fun onDateClick(date:String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentScheduleDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    @RequiresApi(VERSION_CODES.O)
    private fun setUp() {
        preselectedDates.add(today)

        c = Calendar.getInstance()
        list = java.util.ArrayList()

        setIndicators()
        setCalendar()
        setListeners()

    }

    @RequiresApi(VERSION_CODES.O)
    private fun setIndicators() {
        tasksViewModel.getAllTask().observe(viewLifecycleOwner, androidx.lifecycle.Observer{ result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val tasks = result.data
                    for (i in 0 until tasks.size){
                        val taskDate = getCalendarDate(tasks[i].date_begin!!)
                        Log.d("TASK_DATE", taskDate.toString())
                        worksDate.add(taskDate)
                        eventsDate.add(tasks[i].name)
                    }
                    val indicators: List<CalendarView.DateIndicator> = generateCalendarDateIndicators()
                    val additionalTexts: List<CalendarView.AdditionalText> = generateAdditionalTexts()
                    binding.calendarView.datesIndicators = indicators

                    Log.d("LISTA_DE_DATES", worksDate.toString())
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG)
                        .show()
                }

            }

        })
    }

    private fun setListeners() {
        when(arguments?.getInt(TYPE)){

            0 -> {
                binding.calendarView.onDateClickListener = { date ->
                    onClick.onDateClick(date.toString())
                    dismiss()
                }
            }
            1 -> {
                binding.calendarView.onDateClickListener = { date ->
                    onClick.onDateClick(date.toString())
                    dismiss()
                }
            }

        }
    }

    @RequiresApi(VERSION_CODES.O)
    private fun setCalendar() {

        val calendarView = binding.calendarView

        //obtener primer dia del mes
        minDate = getFirstDayOfMonth(calendar)

        //obtener ultimo dia del mes
        maxDate = getLastDayOfMonth(calendar)


        val monthActual = calendar.get(Calendar.MONTH)
        setTextMonth(monthActual)

        //documentacion calendarView: https://github.com/CleverPumpkin/CrunchyCalendar
        calendarView.setupCalendar(
            initialDate = today,
            minDate = minDate, //apartir de que fecha se va a mostrar
            maxDate = maxDate, //fecha maxima que se va a mostrar
            selectionMode = CalendarView.SelectionMode.SINGLE,
            firstDayOfWeek = firstDayOfWeek,
            showYearSelectionView = false,
            //selectedDates = preselectedDates,
            )


        binding.ibMonthNext.setOnClickListener {
            //simar un mes
            calendar.add(Calendar.MONTH, 1)
            entireMonth = true
            isToday = false
            minDate = getFirstDayOfMonth(calendar)
            //obtener ultimo dia del mes
            maxDate = getLastDayOfMonth(calendar)

            //GET ROOM FILTOR MES


            calendarView.setupCalendar(
                initialDate = minDate,
                minDate = minDate, //apartir de que fecha se va a mostrar
                maxDate = maxDate, //fecha maxima que se va a mostrar
                selectionMode = CalendarView.SelectionMode.SINGLE,
                firstDayOfWeek = firstDayOfWeek,
                showYearSelectionView = false,
                //selectedDates = preselectedDates
            )

            val monthActual = calendar.get(Calendar.MONTH)
            setTextMonth(monthActual)
        }

        binding.ibMonthBack.setOnClickListener {
            //simar un mes
            calendar.add(Calendar.MONTH, -1)

            //obtener primer dia del mes
            minDate = getFirstDayOfMonth(calendar)

            //obtener ultimo dia del mes
            maxDate = getLastDayOfMonth(calendar)

            //GETROOM FILTRO MES

            calendarView.setupCalendar(
                initialDate = minDate,
                minDate = minDate, //apartir de que fecha se va a mostrar
                maxDate = maxDate, //fecha maxima que se va a mostrar
                selectionMode = CalendarView.SelectionMode.SINGLE,
                firstDayOfWeek = firstDayOfWeek,
                showYearSelectionView = false,
                //selectedDates = preselectedDates
            )

            val monthActual = calendar.get(Calendar.MONTH)
            setTextMonth(monthActual)
        }
    }

    companion object {
        const val TYPE = "TYPE"
    }

    fun newInstance(type: Int): ScheduleDialogFragment {
        val frag = ScheduleDialogFragment(onClick)
        val args = Bundle()
        args.putInt(TYPE, type)
        frag.arguments = args
        return frag
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(VERSION_CODES.O)
    private fun getFirstDayOfMonth(calendar: Calendar): CalendarDate {
        val firstDayNumber = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
        calendar.set(Calendar.DAY_OF_MONTH, firstDayNumber)
        return CalendarDate(calendar.time)
    }

    private fun getLastDayOfMonth(calendar: Calendar): CalendarDate {
        val lastDayNumber = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar.set(Calendar.DAY_OF_MONTH, lastDayNumber)
        return CalendarDate(calendar.time)
    }

    private fun setTextMonth(monthActual: Int) {
        var monthString = ""
        when (monthActual) {
            0 -> monthString = "Enero"
            1 -> monthString = "Febrero"
            2 -> monthString = "Marzo"
            3 -> monthString = "Abril"
            4 -> monthString = "Mayo"
            5 -> monthString = "Junio"
            6 -> monthString = "Julio"
            7 -> monthString = "Agosto"
            8 -> monthString = "Septiembre"
            9 -> monthString = "Octubre"
            10 -> monthString = "Noviembre"
            11 -> monthString = "Diciembre"
        }
        binding.tvMonth.text = monthString

    }

    @RequiresApi(VERSION_CODES.O)
    fun getCalendarDate(date: String): CalendarDate {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val dateCurrent = formatter.parse(date)
        val dateFinal = CalendarDate(dateCurrent)
        return dateFinal
    }

    private fun generateAdditionalTexts(): List<CalendarView.AdditionalText> {
        val context = requireContext()
        val events = mutableListOf<CalendarView.AdditionalText>()

        for (i in 0 until worksDate.size) {
            events += CalendarEventsIndicator(
                date = worksDate[i],
                color = context.getColorInt(R.color.teal_idea),
                text = eventsDate[i]
            )

        }

        return events
    }


    private fun generateCalendarDateIndicators(): List<CalendarView.DateIndicator> {
        val context = requireContext()
        val indicators = mutableListOf<CalendarView.DateIndicator>()

        for (i in 0 until worksDate.size) {
            indicators += CalendarDateIndicator(
                date = worksDate[i],
                color = context.getColorInt(R.color.teal_idea),
            )

        }

        return indicators
    }

    class CalendarDateIndicator(
        override val date: CalendarDate,
        override val color: Int,

    ) : CalendarView.DateIndicator

    class CalendarEventsIndicator(
        override val color: Int,
        override val date: CalendarDate,
        override val text: String

    ): CalendarView.AdditionalText
}