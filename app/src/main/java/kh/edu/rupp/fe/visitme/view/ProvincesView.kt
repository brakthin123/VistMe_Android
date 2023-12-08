package kh.edu.rupp.fe.visitme.view

import kh.edu.rupp.fe.visitme.model.api.model.Province

interface ProvincesView {

    fun showProvinceList(provinces: List<Province>?)

    fun showError(message: String)

    fun showProvinceDetail(province: Province)

}