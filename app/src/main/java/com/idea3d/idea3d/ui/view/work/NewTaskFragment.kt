package com.idea3d.idea3d.ui.view.work

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentNewTaskBinding
import com.idea3d.idea3d.databinding.FragmentWorksDetailsBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import com.idea3d.idea3d.utils.Functional
import com.idea3d.idea3d.utils.Functional.Companion.ACCEPT_MIME_TYPES
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import ru.cleverpumpkin.calendar.CalendarDate
import ru.cleverpumpkin.calendar.CalendarDate.Companion.today
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class NewTaskFragment : Fragment(), ScheduleDialogFragment.OnDateClick, AdapterView.OnItemClickListener {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private var idStatus = 1
    private var stringStatus = ""

    private val tasksViewModel by viewModels<TasksViewModel>()

    private lateinit var scheduleDialogFragment: ScheduleDialogFragment

    private val REQUIRED_PERMISSION = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
    private lateinit var registerPermissionLauncher: ActivityResultLauncher<Array<String>>

    var image:String?=null
    var extension: String?=null

    var resultGalleryLauncher =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent: Intent? = result.data
            val clipData = intent!!.clipData
            var concatUri = ""

            if (intent!=null){
                if (clipData != null) {
                    var i = 0
                    var concatUri: String = ""

                    while (i < clipData.itemCount) {
                        concatUri += "${
                            Functional.displayName(
                                requireActivity(),
                                clipData.getItemAt(i).uri
                            )
                        }\t"
                        i++
                        binding.buttonPhoto.setText(concatUri)
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            activity?.contentResolver,
                            clipData.getItemAt(i).uri
                        )
                        val base64 = Functional.getBase64ScaledImageString(bitmap)
                        val extension = Functional.getExtensionFromFile(requireActivity(), clipData.getItemAt(i).uri)

                        this.image = base64
                        this.extension = extension
                    }
                } else {

                    var concatString = Functional.displayName(
                        requireActivity(),
                        intent.data
                    )
                    binding.buttonPhoto.setText(concatString)
                    val extension = Functional.getExtensionFromFile(requireActivity(), intent.data)

                    if (extension.equals(".jpg") || extension.equals(".png")) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            activity?.contentResolver,
                            intent.data
                        )
                        val base64 = Functional.getBase64ScaledImageString(bitmap)
                        this.image = base64
                        this.extension = extension
                    }

                }
            }

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()

    }

    private fun setUp(){
        (activity as MainActivity).setThemeHome()

        createPermissionLauncher()

        binding.buttonPhoto.setOnClickListener {
            launchGaleryClicked()
        }

        binding.buttonSend.setOnClickListener {
            createTask()
            findNavController().navigate(R.id.action_newTaskFragment_to_worksDetailsFragment)
        }

        binding.dbBegin.setText(DISPLAY_DATE)

        binding.dbBegin.setOnClickListener {
            scheduleDialogFragment = ScheduleDialogFragment(this)
            val dateInst = scheduleDialogFragment.newInstance(1)
            dateInst.show(activity?.supportFragmentManager!!,"Dialog Bottom")
        }

        initArray()

        val clients = arguments?.getStringArrayList("clients")
        clients?.let { if (clients.isNotEmpty()) initClientArray(clients) }
    }

    private fun initClientArray(clients: ArrayList<String>) {


        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            clients
        )

        with(binding.etClient) {
            setAdapter(adapter)
        }
    }

    private fun createTask (){
        val nameTask = binding.inputName.text.toString()
        val descriptionTask = binding.inputDescription.text.toString()
        val priority = binding.cbPriority.isChecked
        val price = binding.etPrice.text.toString().toFloat()
        val cost = binding.etCost.text.toString().toFloat()
        val client = binding.etClient.text.toString()

        val task = Task(
            name =nameTask,
            description = descriptionTask,
            prioritize = priority,
            price = price,
            cost = cost,
            date_begin = DISPLAY_DATE,
            id_status = idStatus,
            status = stringStatus,
            client = client,
            thing_photo = image,
            thing_extension = extension
        )

        tasksViewModel.addTask(task)
    }

    companion object{
        var DISPLAY_DATE = today.toString()
    }

    override fun onDateClick(date:String) {
        binding.dbBegin.setText(DISPLAY_DATE)
    }

    private fun initArray(){

        binding.listStatus.setText(getString(R.string.maker_zone_status_1))
        stringStatus = (getString(R.string.maker_zone_status_1))

        val status = resources.getStringArray(R.array.status)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            status
        )

        with(binding.listStatus) {
            setAdapter(adapter)
            onItemClickListener=this@NewTaskFragment
        }
     }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        when (p2) {
            0 -> {
                idStatus = 1
                stringStatus = getString(R.string.maker_zone_status_1)
            }
            1 -> {
                idStatus = 2
                stringStatus = getString(R.string.maker_zone_status_2)
            }
            2 -> {
                idStatus = 3
                stringStatus = getString(R.string.maker_zone_status_3)
            }
            3 -> {
                idStatus = 4
                stringStatus = getString(R.string.maker_zone_status_4)
            }
            4 -> {
                idStatus = 5
                stringStatus = getString(R.string.maker_zone_status_5)
            }
            5 -> {
                idStatus = 6
                stringStatus = getString(R.string.maker_zone_status_6)
            }
            6 -> {
                idStatus = 7
                stringStatus = getString(R.string.maker_zone_status_7)
            }
            7 -> {
                idStatus = 8
                stringStatus = getString(R.string.maker_zone_status_8)
            }
            8 -> {
                idStatus = 9
                stringStatus = getString(R.string.maker_zone_status_9)
            }
            else -> {
                idStatus = 1
                stringStatus = getString(R.string.maker_zone_status_1)
            }
        }

    }

    private fun createPermissionLauncher() {
        registerPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permission ->
                if (permission[Manifest.permission.READ_EXTERNAL_STORAGE] == true) {
                    launchGalery()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Se necesita los permisos para lanzar la galeria",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    private fun launchGaleryClicked() {
        if (arePermissionGranted()) {
            launchGalery()
        } else {
            askPermission() // como pido permiso si no fueron otorgados
        }

    }


    private fun launchGalery() {

        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "application/pdf,image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra(Intent.EXTRA_MIME_TYPES, ACCEPT_MIME_TYPES)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        resultGalleryLauncher.launch(intent)

    }


    private fun askPermission() {
        registerPermissionLauncher.launch(REQUIRED_PERMISSION)
    }

    private fun arePermissionGranted(): Boolean = REQUIRED_PERMISSION.all {
        ContextCompat.checkSelfPermission(
            requireContext(),
            it
        ) == PackageManager.PERMISSION_GRANTED
    }


}