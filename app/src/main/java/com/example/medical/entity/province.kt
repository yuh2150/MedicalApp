package com.example.medical.entity


data class Province(
    val province_id: String,
    val province_name: String,
    val province_type: String
)


data class District(
    val district_id: String,
    val district_name: String,
)


data class Ward(
    val ward_id: String,
    val ward_name: String,
)

data class ProvinceResponse(
    val results: List<Province>
)

data class DistrictResponse(
    val results: List<District>
)

data class WardResponse(
    val results: List<Ward>
)