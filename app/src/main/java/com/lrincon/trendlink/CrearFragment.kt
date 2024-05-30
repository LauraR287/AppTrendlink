package com.lrincon.trendlink

import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileOutputStream

class CrearFragment : Fragment(R.layout.fragment_crear) {

    private lateinit var drawingView: DrawingView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuButton = view.findViewById<ImageButton>(R.id.menu)
        drawingView = view.findViewById(R.id.drawingView)

        menuButton.setOnClickListener {
            showPopupMenu(it)
        }

        // Configurar botones de colores con el color del pincel
        view.findViewById<ImageButton>(R.id.color_black).setOnClickListener {
            drawingView.setColor(0xFF000000.toInt())
        }
        view.findViewById<ImageButton>(R.id.color_red).setOnClickListener {
            drawingView.setColor(0xFFF44336.toInt())
        }
        view.findViewById<ImageButton>(R.id.color_blue).setOnClickListener {
            drawingView.setColor(0xFF2196F3.toInt())
        }
        view.findViewById<ImageButton>(R.id.color_green).setOnClickListener {
            drawingView.setColor(0xFF8BC34A.toInt())
        }
        view.findViewById<ImageButton>(R.id.color_yellow).setOnClickListener {
            drawingView.setColor(0xFFFFC107.toInt())
        }
    }

    private fun showPopupMenu(view: View) {
        val popup = PopupMenu(requireContext(), view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.nav_crear, popup.menu)
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_guardar -> {
                    val bitmap = drawingView.getBitmap()
                    val file = File(requireContext().cacheDir, "creacion.jpg")
                    val fos = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                    fos.flush()
                    fos.close()

                    val uri = FileProvider.getUriForFile(requireContext(), "com.lrincon.trendlink.fileprovider", file)
                    FirebaseHelperCrear.guardarCreacion(uri)
                    Toast.makeText(requireContext(), "Tu diseño se ha guardado correctamente", Toast.LENGTH_SHORT)
                        .show()
                    drawingView.clear()
                    true
                }
                R.id.nav_nuevo -> {
                    // Acción para "Nuevo diseño"
                    drawingView.clear()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }
}



