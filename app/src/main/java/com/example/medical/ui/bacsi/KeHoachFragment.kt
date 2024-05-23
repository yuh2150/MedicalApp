package com.example.medical.ui.bacsi

import ApiClient.apiService
import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs

import com.example.medical.databinding.FragmentKeHoachBinding
import com.example.medical.viewmodel.BacSiViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.Calendar


class KeHoachFragment : Fragment() {

    private val args by navArgs<KeHoachFragmentArgs>()
    private var _binding: FragmentKeHoachBinding?= null
    private val binding get() = _binding!!
    private lateinit var calendar: Calendar


    private lateinit var editName: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var editSDT: EditText
    private lateinit var editDate: EditText
    private lateinit var spinnerProvince: Spinner
    private lateinit var spinnerDistrict: Spinner
    private lateinit var spinnerWard: Spinner
    private lateinit var editDiachi: EditText
    private lateinit var editLydo: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var mBacSiViewModel: BacSiViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKeHoachBinding.inflate(inflater, container, false)
        val view = binding.root
        mBacSiViewModel = ViewModelProvider(this).get(BacSiViewModel::class.java)
//        binding.txtTitle.setText(mBacSiViewModel.getBacSiByIdKh(1).)
        mBacSiViewModel.getBacSiByIdKh(args.currentKeHoach.id_kh).observe(viewLifecycleOwner, Observer {item->
            binding.txtTitle.setText(item.name)
            binding.textGia.setText(item.giakham.toString())
        })
        mBacSiViewModel.getStatusByIdKh(args.currentKeHoach.id_kh).observe(viewLifecycleOwner, Observer {item->
            binding.txtGio.setText(item.gio)
            binding.txtNgay.setText(item.ngay)
        })
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
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        }
        spinnerProvince = binding.spinnerProvince
        spinnerDistrict = binding.spinnerDistrict
        spinnerWard = binding.spinnerWard
        editName = binding.editName
        radioGroup = binding.radioGroup
        editSDT = binding.editSDT
        editDate = binding.editDate
        editDiachi = binding.editDiachi
        editLydo = binding.editLydo
        buttonSubmit = binding.buttonSubmit


        GlobalScope.launch(Dispatchers.Main) {
            val provinces = apiService.getProvinces().results
            Log.d("1",provinces.toString())
            if (provinces != null) {
                val provinceNames = provinces.map { it.province_name }
                Log.d("2",provinceNames.toString())
                val provinceAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, provinceNames)
                provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerProvince.adapter = provinceAdapter

                // Sự kiện khi chọn tỉnh/thành phố
                spinnerProvince.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        val selectedProvinceId = provinces[position].province_id
                        Log.d("3",selectedProvinceId)
                        loadDistricts(selectedProvinceId)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
        }

        buttonSubmit.setOnClickListener {
            validateInputs()
        }
        return view
    }

    private fun loadDistricts(provinceId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val districts = ApiClient.apiService.getDistricts(provinceId).results
            Log.d("1",districts.toString())
            if (districts != null) {
                val districtNames = districts.map { it.district_name}
                Log.d("2",districtNames.toString())
                val districtAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, districtNames)
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerDistrict.adapter = districtAdapter
                spinnerDistrict.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                        val selectedDistrictId = districts[position].district_id
                        Log.d("3",selectedDistrictId)
                        loadWard(selectedDistrictId)
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }

        }
    }

    private fun loadWard(wardId: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val wards = ApiClient.apiService.getWards(wardId).results
            Log.d("1",wards.toString())
            if (wards != null) {
                val wardNames = wards.map { it.ward_name}
                Log.d("2",wardNames.toString())
                val districtAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, wardNames)
                districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerWard.adapter = districtAdapter

            }

        }
    }

    private fun insertLich(){

    }

    private fun validateInputs() {
        val name = editName.text.toString().trim()
        val selectedGenderId = radioGroup.checkedRadioButtonId
        val phone = editSDT.text.toString().trim()
        val dateOfBirth = editDate.text.toString().trim()
        val province = spinnerProvince.selectedItem.toString()
        val district = spinnerDistrict.selectedItem.toString()
        val ward = spinnerWard.selectedItem.toString()
        val address = editDiachi.text.toString().trim()
        val reason = editLydo.text.toString().trim()

        when {
            name.isEmpty() -> {
                editName.error = "Họ tên bệnh nhân là bắt buộc"
                editName.requestFocus()
            }
            selectedGenderId == -1 -> {
                Toast.makeText(requireContext(), "Vui lòng chọn giới tính", Toast.LENGTH_SHORT).show()
            }
            phone.isEmpty() -> {
                editSDT.error = "Số điện thoại bệnh nhân là bắt buộc"
                editSDT.requestFocus()
            }
            !isValidPhoneNumber(phone) -> {
                editSDT.error = "Số điện thoại không hợp lệ"
                editSDT.requestFocus()
            }
            dateOfBirth.isEmpty() -> {
                editDate.error = "Ngày sinh là bắt buộc"
                editDate.requestFocus()
            }
            province.isEmpty() || province == "Chọn tỉnh/thành" -> {
                Toast.makeText(requireContext(),"Vui lòng chọn tỉnh/thành", Toast.LENGTH_SHORT).show()
            }
            district.isEmpty() || district == "Chọn quận/huyện" -> {
                Toast.makeText(requireContext(), "Vui lòng chọn quận/huyện", Toast.LENGTH_SHORT).show()
            }
            ward.isEmpty() || ward == "Chọn phường/xã" -> {
                Toast.makeText(requireContext(), "Vui lòng chọn phường/xã", Toast.LENGTH_SHORT).show()
            }
            address.isEmpty() -> {
                editDiachi.error = "Địa chỉ là bắt buộc"
                editDiachi.requestFocus()
            }
            reason.isEmpty() -> {
                editLydo.error = "Lý do khám là bắt buộc"
                editLydo.requestFocus()
            }
            else -> {
                Toast.makeText(requireContext(), "Đăng ký thành công!",Toast.LENGTH_SHORT).show()
                // Proceed with form submission
            }
        }
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        return phone.length == 10 && TextUtils.isDigitsOnly(phone)
    }

}