package com.example.medical.ui.kehoach

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.medical.R
import com.example.medical.databinding.FragmentAddStatusBinding
import com.example.medical.entity.Status
import com.example.medical.viewmodel.KeHoachViewModel
import com.example.medical.viewmodel.StatusViewModel
import java.util.Calendar


class AddStatusFragment : Fragment() {
    private lateinit var mStatusViewModel: StatusViewModel
    private var _binding: FragmentAddStatusBinding?= null
    private val binding get() = _binding!!

    private lateinit var calendar: Calendar

    lateinit var EditDate: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddStatusBinding.inflate(inflater, container, false)
        val view = binding.root

        mStatusViewModel = ViewModelProvider(this).get(StatusViewModel::class.java)

        calendar = Calendar.getInstance()
        var datePicker = binding.editDate
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        datePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    datePicker.setText(selectedDate)
                    EditDate = selectedDate
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }
        var selectedTime1 = ""
        var selectedTime2 = ""
        binding.timePicker1.setOnTimeChangedListener{ _, hourOfDay, minute ->
            // Hiển thị thời gian đã chọn
            selectedTime1 = String.format("%02d:%02d", hourOfDay, minute)
            Log.d("time",selectedTime1)
        }
        binding.timePicker2.setOnTimeChangedListener{ _, hourOfDay, minute ->
            // Hiển thị thời gian đã chọn
            selectedTime2 = String.format("%02d:%02d", hourOfDay, minute)
            Log.d("time",selectedTime2)
        }
        binding.add.setOnClickListener{
//            Log.d("time",EditDate)
            mStatusViewModel.addStatus(Status(0,EditDate,selectedTime1+" - "+ selectedTime2))
            findNavController().navigate(R.id.QLyKeHoachFragment)
        }

        return view
    }

}