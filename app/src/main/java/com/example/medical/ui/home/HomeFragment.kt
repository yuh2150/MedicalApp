package com.example.medical.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.medical.adapters.BSNoiBatAdapter
import com.example.medical.adapters.BacSiAdapter
import com.example.medical.adapters.ChuyenKhoaAdapter
import com.example.medical.adapters.DichVuAdapter
import com.example.medical.adapters.SliderAdapter
import com.example.medical.adapters.ViewPagerAdapter
//import com.denzcoskun.imageslider.ImageSlider
import com.example.medical.databinding.FragmentHomeBinding
import com.example.medical.viewmodel.BacSiViewModel
import com.example.medical.viewmodel.ChuyenKhoaViewModel
import com.smarteist.autoimageslider.SliderView

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var image1Url: ArrayList<String>
    lateinit var imageDv: ArrayList<String>
    lateinit var titleDv: ArrayList<String>

    lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapter

    private lateinit var mChuyenKhoaViewModel: ChuyenKhoaViewModel
    private lateinit var mBacSiViewModel: BacSiViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)



        sliderView = binding.imageSlider1

        image1Url = ArrayList()

        image1Url =
            (image1Url + "https://i.ibb.co/c8r7D0p/1.jpg") as ArrayList<String>
        image1Url =
            (image1Url + "https://i.ibb.co/gPTy3n3/2.jpg") as ArrayList<String>
        image1Url =
            (image1Url + "https://i.ibb.co/1rNNWQ7/3.jpg") as ArrayList<String>
        image1Url =
            (image1Url + "https://i.ibb.co/ypBPth2/4.jpg") as ArrayList<String>

        sliderAdapter = SliderAdapter( image1Url)


        imageDv = ArrayList()
        titleDv = ArrayList()
        imageDv =
            (imageDv + "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161905-iconkham-chuyen-khoa.png") as ArrayList<String>
        imageDv =
            (imageDv + "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161817-iconkham-tu-xa.png") as ArrayList<String>
        imageDv =
            (imageDv + "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161350-iconkham-tong-quan.png") as ArrayList<String>
        imageDv =
            (imageDv + "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161410-iconkham-nha-khoa.png") as ArrayList<String>
        imageDv =
            (imageDv + "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161421-icongoi-phau-thuat.png") as ArrayList<String>
        imageDv =
            (imageDv + "https://cdn.bookingcare.vn/fo/w128/2023/09/20/145257-thiet-ke-chua-co-ten-3.png") as ArrayList<String>

        titleDv =
            (titleDv + "Khám chuyên khoa") as ArrayList<String>
        titleDv =
            (titleDv + "Khám từ xa") as ArrayList<String>
        titleDv =
            (titleDv + "Khám tổng quát") as ArrayList<String>
        titleDv =
            (titleDv + "Khám nha khoa") as ArrayList<String>
        titleDv =
            (titleDv + "Gói phẩu thuật") as ArrayList<String>
        titleDv =
            (titleDv + "Sức khỏe tiểu đường") as ArrayList<String>


        binding.listDichvu.adapter = DichVuAdapter(imageDv,titleDv)

        binding.listDichvu.layoutManager = GridLayoutManager(requireContext(),2)

//        binding.imageView5.setImageURI()
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1516549655169-df83a0774514?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
            .centerCrop()
            .into(binding.imageView5)
        Glide.with(this)
            .load("https://images.pexels.com/photos/1692693/pexels-photo-1692693.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
            .centerCrop()
            .into(binding.imageVie)
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1551076805-e1869033e561?q=80&w=1932&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
            .centerCrop()
            .into(binding.image)

        sliderView.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
        sliderView.setSliderAdapter(sliderAdapter)
        sliderView.scrollTimeInSec = 3
        sliderView.isAutoCycle = true
        sliderView.startAutoCycle()


        mChuyenKhoaViewModel = ViewModelProvider(this).get(ChuyenKhoaViewModel::class.java)
        val recyclerView : RecyclerView = binding.recycleViewCkhome
        val adapter = ChuyenKhoaAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        mChuyenKhoaViewModel.readAllData.observe(viewLifecycleOwner, Observer {chuyenkhoa ->
//            Log.d("ChuyenKhoaFragment", "Received data from ViewModel: $chuyenkhoa")
            adapter.setData(chuyenkhoa)

//            Log.d("TAG", "Dữ liệu từ ViewModel: $chuyenkhoa")
        })

        mBacSiViewModel = ViewModelProvider(this).get(BacSiViewModel::class.java)

        val recyclerView_bs : RecyclerView = binding.recycleViewBacsi
        val adapter_bs = BSNoiBatAdapter(requireContext())
        recyclerView_bs.adapter = adapter_bs
        recyclerView_bs.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)

        mBacSiViewModel.readLimit(6).observe(viewLifecycleOwner, Observer { bacsi ->
            adapter_bs.setData(bacsi)
        })


        val root: View = binding.root
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}