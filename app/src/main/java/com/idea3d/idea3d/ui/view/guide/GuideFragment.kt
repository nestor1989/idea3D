package com.idea3d.idea3d.ui.view.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.Problems
import com.idea3d.idea3d.databinding.FragmentGuideBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.view.adapter.ProblemsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment(), OnFragmentActionsListener {
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    private lateinit var solutionFragment: SolutionFragment

    private lateinit var adapter: ProblemsAdapter

    private lateinit var errorGuide: List<Problems>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(inflater, container, false)

        (activity as MainActivity).setThemeMain()

        initGuide()
        initAdapter()
        setUpSearchView()

        return binding.root
    }

    private fun initAdapter(){
        val appContext = requireContext().applicationContext
        binding.recyclerGuia.layoutManager= LinearLayoutManager(appContext)
        adapter = ProblemsAdapter(this, errorGuide)
        binding.recyclerGuia.adapter = adapter
    }

    override fun onClickFragmentButton(value:Int, button:Int, image: Int, title:String) {
        val bundle = Bundle()
        bundle.putInt("button", button)
        bundle.putInt("value", value)
        bundle.putInt("image", image)
        bundle.putString("title", title)
        solutionFragment = SolutionFragment()
        val solutionFragmentInst = solutionFragment.newInstance(bundle)
        solutionFragmentInst.show(activity?.supportFragmentManager!!, "solution")

    }

    override fun onImageClick(value: Int, image:Int, title:String) {
        val bundle = Bundle()
        bundle.putInt("value", value)
        bundle.putInt("image", image)
        bundle.putString("title", title)
        solutionFragment = SolutionFragment()
        val solutionFragmentInst = solutionFragment.newInstance(bundle)
        solutionFragmentInst.show(activity?.supportFragmentManager!!, "IMAGE")
    }

    private fun setUpSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                var search = p0!!
                showFilterList(search)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var search = p0!!
                showFilterList(search)
                return false
            }
        })
    }

    private fun showFilterList(search:String){
        var listFilter: ArrayList<Problems> = arrayListOf()
        val list = errorGuide
        for (i in list.indices){
            if (list[i].name.contains(search, ignoreCase = true) || list[i].description.contains(search, ignoreCase = true)){
                listFilter.add(list[i])
            }
        }
        setAdapter(listFilter)
    }

    private fun setAdapter(list: ArrayList<Problems>){
        adapter = ProblemsAdapter(this, list)
        binding.recyclerGuia.adapter = adapter
    }

    private fun initGuide(){
        errorGuide = listOf(
            Problems(getString(R.string.no_extrusion_start_title), getString(R.string.no_extrusion_start_description), getString(
                R.string.no_extrusion_start_cause1), getString(R.string.no_extrusion_start_cause2), getString(
                R.string.no_extrusion_start_cause3), R.drawable.no_arranca),
            Problems(getString(R.string.first_layer_not_adhering_title), getString(R.string.first_layer_not_adhering_description), getString(
                R.string.first_layer_not_adhering_cause1), getString(R.string.first_layer_not_adhering_cause2), getString(
                R.string.first_layer_not_adhering_cause3), R.drawable.primera_capa_nopega),
            Problems(getString(R.string.little_plastic_nozzle_title), getString(R.string.little_plastic_nozzle_description), getString(
                R.string.little_plastic_nozzle_cause1), getString(R.string.little_plastic_nozzle_cause2), null, R.drawable.sale_poco_plastico),
            Problems(getString(R.string.lot_plastic_nozzle_title), getString(R.string.lot_plastic_nozzle_description), getString(
                R.string.lot_plastic_nozzle_cause1), null, null, R.drawable.sale_mucho_plastico),
            Problems(getString(R.string.holes_upper_layers_title), getString(R.string.holes_upper_layers_description), getString(
                R.string.holes_upper_layers_cause1), getString(R.string.holes_upper_layers_cause2), getString(
                R.string.holes_upper_layers_cause3), R.drawable.agujeros_capas_superiores),
            Problems(getString(R.string.threads_outside_piece_title), getString(R.string.threads_outside_piece_description), getString(
                R.string.threads_outside_piece_cause1), getString(R.string.threads_outside_piece_cause2), getString(
                R.string.threads_outside_piece_cause3), R.drawable.hilos),
            Problems(getString(R.string.deformation_excess_temperature_title), getString(R.string.deformation_excess_temperature_description), getString(
                R.string.deformation_excess_temperature_cause1), getString(R.string.deformation_excess_temperature_cause2), getString(
                R.string.deformation_excess_temperature_cause3), R.drawable.def_exc_temp),
            Problems(getString(R.string.layer_displacement_title), getString(R.string.layer_displacement_description), getString(
                R.string.layer_displacement_cause1), getString(R.string.layer_displacement_cause2), getString(
                R.string.layer_displacement_cause3), R.drawable.desplazamiento_capas),
            Problems(getString(R.string.filament_jammed_title), getString(R.string.filament_jammed_description), getString(
                R.string.filament_jammed_cause1), getString(R.string.filament_jammed_cause2), getString(
                R.string.filament_jammed_cause3), R.drawable.fila_mordido),
            Problems(getString(R.string.extruder_jammed_title), getString(R.string.extruder_jammed_description), getString(
                R.string.extruder_jammed_cause1), getString(R.string.extruder_jammed_cause2), getString(
                R.string.extruder_jammed_cause3), R.drawable.ext_obstruido),
            Problems(getString(R.string.stops_extruding_plastic_title), getString(R.string.stops_extruding_plastic_description), getString(
                R.string.stops_extruding_plastic_cause1), getString(R.string.stops_extruding_plastic_cause2), getString(
                R.string.stops_extruding_plastic_cause3), R.drawable.deja_de_imprimir),
            Problems(getString(R.string.deficient_filling_title), getString(R.string.deficient_filling_description), getString(
                R.string.deficient_filling_cause1), getString(R.string.deficient_filling_cause2), getString(
                R.string.deficient_filling_cause3), R.drawable.relleno_deficiente),
            Problems(getString(R.string.plastic_stuck_piece_title), getString(R.string.plastic_stuck_piece_description), getString(
                R.string.plastic_stuck_piece_cause1), getString(R.string.plastic_stuck_piece_cause2), null, R.drawable.plastico_pegado),
            Problems(getString(R.string.holes_outer_layers_title), getString(R.string.holes_outer_layers_description), getString(
                R.string.holes_outer_layers_cause1), getString(R.string.holes_outer_layers_cause2), getString(
                R.string.holes_outer_layers_cause3), R.drawable.huecos_capas_ext),
            Problems(getString(R.string.warping_title), getString(R.string.warping_description), getString(
                R.string.warping_cause1), getString(R.string.warping_cause2), getString(R.string.warping_cause3), R.drawable.warping),
            Problems(getString(R.string.upper_layer_scratched_title), getString(R.string.upper_layer_scratched_description), getString(
                R.string.upper_layer_scratched_cause1), getString(R.string.upper_layer_scratched_cause2), null, R.drawable.cara_sup_rayada),
            Problems(getString(R.string.layer_desfazaje_title), getString(R.string.layer_desfazaje_description), getString(
                R.string.layer_desfazaje_cause1), null, null, R.drawable.desfazaje_capas),
            Problems(getString(R.string.lines_piece_title), getString(R.string.lines_piece_description), getString(
                R.string.lines_piece_cause1), getString(R.string.lines_piece_cause2), getString(R.string.lines_piece_cause3), R.drawable.lineas_impresion),
            Problems(getString(R.string.lack_filling_title), getString(R.string.lack_filling_description), getString(
                R.string.lack_filling_cause1), getString(R.string.lack_filling_cause2), null, R.drawable.falta_relleno),
            Problems(getString(R.string.low_resolution_title), getString(R.string.low_resolution_description), getString(
                R.string.low_resolution_cause1), getString(R.string.low_resolution_cause2), getString(R.string.low_resolution_cause3), R.drawable.poca_resolucion),
            Problems(getString(R.string.layers_far_apart_title), getString(R.string.layers_far_apart_description), getString(
                R.string.layers_far_apart_cause1), getString(R.string.layers_far_apart_cause2), null, R.drawable.capas_muy_separadas),
            Problems(getString(R.string.excess_plastic_title), getString(R.string.excess_plastic_description), getString(
                R.string.excess_plastic_cause1), null, null, R.drawable.gotas),
            Problems(getString(R.string.deficient_first_layer_title), getString(R.string.deficient_first_layer_description), getString(
                R.string.deficient_first_layer_cause1), getString(R.string.deficient_first_layer_cause2), getString(
                R.string.deficient_first_layer_cause3), R.drawable.primera_capa_deficiente)
        )
    }

}