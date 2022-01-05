package pro.aidar.library.ui.pdf

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import by.kirich1409.viewbindingdelegate.viewBinding
import pro.aidar.library.R
import pro.aidar.library.databinding.ActivityPdfBinding
import pro.aidar.library.utils.getFile

class PdfActivity : AppCompatActivity() {
    private val uriPdf: String?
        get() = intent.getStringExtra("URI")
    private val binding: ActivityPdfBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        binding.pdfView.fromFile(uriPdf!!.toUri().getFile(this))
            .load()
    }
}