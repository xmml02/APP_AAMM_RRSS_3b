package com.app.olders_rrss.Activity

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.app.olders_rrss.Clases.app
import com.app.olders_rrss.Clases.screenActiv
import com.app.olders_rrss.databinding.ActivityAct3Eval1eBinding
import kotlinx.coroutines.launch

class Act_3_Eval_1e : AppCompatActivity() {

    lateinit var binding: ActivityAct3Eval1eBinding
    lateinit var arrowImage: ImageView
    lateinit var imgTarget: ImageView
    lateinit var seekBarX: SeekBar
    lateinit var seekBarY: SeekBar

    private var screenActiv: screenActiv? = screenActiv()
    private var fltSizeFuente: Float = 15F

    private var booFinalizado: Boolean = false
    private var fltArrowX: Float = 0F
    private var fltArrowY: Float = 0F

    private var screenHeight: Int = 0
    private var screenWidth: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels
        screenWidth = displayMetrics.widthPixels

        binding = ActivityAct3Eval1eBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenActiv = app.usuario?.GetDiagScreen("Act_3_Eval_1e")
        fltSizeFuente = app.usuario!!.fltSizeFont!!

        arrowImage = binding.imageArrow
        imgTarget = binding.imgTarget
        seekBarX = binding.seekBarX
        seekBarY = binding.seekBarY

        InitComponents()
        InitUI()
    }

    private fun InitUI() {

        val imgTargetWidth: Int

        binding.lblInstrucc.textSize = fltSizeFuente

        imgTargetWidth = (screenWidth * 0.8).toInt()
        imgTarget.layoutParams.width = imgTargetWidth
        imgTarget.layoutParams.height = imgTargetWidth

        seekBarX.max = imgTargetWidth
        seekBarY.max = imgTargetWidth
        seekBarX.layoutParams.width = imgTargetWidth
        seekBarY.layoutParams.width = imgTargetWidth

        if (screenActiv!!.listActividades!![0].listResult[0].select == "") {
            seekBarX.progress = 0
            seekBarY.progress = 0

        } else {
            val arrXY = screenActiv!!.listActividades!![0].listResult[0].select!!.split("; ")
            seekBarX.progress = arrXY[0].toFloat().toInt()
            seekBarY.progress = arrXY[1].toFloat().toInt()
            arrowImage.y = arrXY[1].toFloat()
        }

        // post es para que se ejecute después de que se haya cargado la vista
        imgTarget.post{
            arrowImage.y += imgTarget.y
        }
    }

    private fun InitComponents() {

        seekBarX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                fltArrowX = progress.toFloat()
                arrowImage.x = fltArrowX

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {booFinalizado = true}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        seekBarY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                fltArrowY = progress.toFloat()

                arrowImage.y = fltArrowY + binding.imgTarget.y

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {booFinalizado = true}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        binding.btnBack.setOnClickListener {
            lifecycleScope.launch {
                screenActiv!!.listActividades!![0].listResult[0].select = "$fltArrowX; $fltArrowY"
                screenActiv!!.listActividades!![0].listResult[0].result = calculateAccuracy().toString()

                if (booFinalizado) SaveScreen()
            }
            startActivity(Intent(this@Act_3_Eval_1e, Act_3_Eval_1d::class.java))
        }
        binding.btnOK.setOnClickListener {
            lifecycleScope.launch {
                screenActiv!!.listActividades!![0].listResult[0].select = "$fltArrowX; $fltArrowY"
                screenActiv!!.listActividades!![0].listResult[0].result = calculateAccuracy().toString()

                if (booFinalizado) SaveScreen()
            }
            startActivity(Intent(this@Act_3_Eval_1e, Act_3_Eval_1f::class.java))
        }
    }

    private suspend fun SaveScreen() {
        app.usuario!!.SetDiagScreen(screenActiv!!, this@Act_3_Eval_1e)
    }

    fun calculateAccuracy(): Float {
        // Obtener las coordenadas del centro de imgTarget
        val targetCenterX = imgTarget.x + imgTarget.width / 2
        val targetCenterY = imgTarget.y + imgTarget.height / 2

        // Obtener las coordenadas del extremo inferior derecho de arrowImage
        val arrowBottomRightX = arrowImage.x
        val arrowBottomRightY = arrowImage.y

        // Calcular la distancia entre los dos puntos
        val distance = Math.sqrt(
            Math.pow(
                (targetCenterX - arrowBottomRightX).toDouble(),
                2.0
            ) + Math.pow((targetCenterY - arrowBottomRightY).toDouble(), 2.0)
        )

        // Definir el valor máximo de distancia (la diagonal de la pantalla)
        val maxDistance = Math.sqrt(
            Math.pow(screenWidth.toDouble(), 2.0) + Math.pow(screenHeight.toDouble(), 2.0)
        )

        // Calcular el porcentaje de acierto
        val accuracy = ((maxDistance - distance) / maxDistance).toFloat()

        return accuracy
    }
}