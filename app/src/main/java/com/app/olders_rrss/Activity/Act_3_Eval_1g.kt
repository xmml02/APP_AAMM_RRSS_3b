package com.app.olders_rrss.Activity

import android.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.app.olders_rrss.Clases.ListItem
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.databinding.ActivityAct3Eval1gBinding

class Act_3_Eval_1g : AppCompatActivity() {

    lateinit var binding: ActivityAct3Eval1gBinding
    private var fltSizeFuente: Float = 15F
    lateinit var ListView: ListView
    private val ListEvalPend = app.usuario!!.objDiagnostico!!.CalcularEvalPend()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityAct3Eval1gBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("PRUEBA", app.usuario.toString())

        ListView = binding.listEvalPend
        fltSizeFuente = app.usuario!!.fltSizeFont!!

        InitUI()
        InitComponents()

    }

    private fun InitComponents() {
        //cuando se toque el item de ListView realizar una accion Toast
        ListView.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position) as ListItem

            when (item.key) {
                "Act_3_Eval_1b" -> startActivity(Intent(this, Act_3_Eval_1b::class.java))
                "Act_3_Eval_1c" -> startActivity(Intent(this, Act_3_Eval_1c::class.java))
                "Act_3_Eval_1d" -> startActivity(Intent(this, Act_3_Eval_1d::class.java))
                "Act_3_Eval_1e" -> startActivity(Intent(this, Act_3_Eval_1e::class.java))
                "Act_3_Eval_1f" -> startActivity(Intent(this, Act_3_Eval_1f::class.java))
            }
        }
    }

    private fun InitUI() {

        ListView.adapter = CustomAdapter(this, R.layout.simple_list_item_activated_1 ,ListEvalPend, fltSizeFuente)
        binding.lblInstrucc.textSize = fltSizeFuente
    }

    class CustomAdapter(
        context: Context,
        private val resource: Int,
        dataSource: List<ListItem>,
        private val textSize: Float
    ) :
        ArrayAdapter<ListItem>(context, resource, dataSource) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view: View = convertView ?: LayoutInflater.from(context)
                .inflate(resource, parent, false)

            // R.layout.simple_list_item_activated_1
            val textView = view.findViewById<TextView>(R.id.text1)
            val item = getItem(position)
            textView.text = item?.strActDesc
            textView.textSize = textSize
            return view
        }
    }

}
