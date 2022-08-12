package com.star.listfragmentactivity

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.star.listfragmentactivity.databinding.AddItemBinding
import com.star.listfragmentactivity.databinding.FragmentArrayListBinding
import com.star.listfragmentactivity.databinding.UpdateXmlBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentArrayList.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentArrayList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentArrayListBinding
    var arrayList:ArrayList<String> = ArrayList()
    lateinit var listActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        listActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArrayListBinding.inflate(layoutInflater)
        var adapter = ArrayAdapter(listActivity,android.R.layout.simple_list_item_1, arrayList)
        arrayList.add("qwerty")
        arrayList.add("qwerty 1")
        arrayList.add("qwerty 2")
        arrayList.add("qwerty 3")
        arrayList.add("qwerty 4")
        binding.list.adapter = adapter

        binding.btnFab.setOnClickListener {

            var dialogBinding = AddItemBinding.inflate(layoutInflater)
            var dialog = Dialog(requireContext())
            dialog.setCancelable(false)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.btnOK.setOnClickListener {
                if (dialogBinding.etAddItem.text.toString().isNullOrEmpty()) {
                    dialogBinding.etAddItem.setError("Please Add Item")
                } else {
                }

                arrayList.add(dialogBinding.etAddItem.text.toString())
                dialog.dismiss()
            }
            dialog.show()
        }




    binding.list.setOnItemClickListener { adapterView, view, i, l ->

        var dialogBinding = UpdateXmlBinding.inflate(layoutInflater)
        var dialog = Dialog(listActivity)
        dialog.setCancelable(false)
        dialog.setContentView(dialogBinding.root)
        val layout = dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.etUpdateItem.setText(arrayList[i])

        dialogBinding.btnUpdate.setOnClickListener {
            if (dialogBinding.etUpdateItem.text.toString().isNullOrEmpty()) {
                dialogBinding.etUpdateItem.setError("Please Update Item")
            }
            else {
                arrayList.set(i ,dialogBinding.etUpdateItem.text.toString())
                dialog.dismiss()

            }
        }
        dialog.show()
    }
    return binding.root
}


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentArrayList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentArrayList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}