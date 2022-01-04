package pro.aidar.library.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import pro.aidar.library.R
import pro.aidar.library.databinding.ActivityPdfBinding

class PdfActivity : AppCompatActivity() {

    private val binding: ActivityPdfBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        binding.pdfView.fromUri(Uri.parse(uriPdf))
    }

    companion object {
        private var uriPdf: String = ""
        fun start(activity: AppCompatActivity, uri: String) {
            activity.startActivity(Intent(activity, PdfActivity::class.java))
            uriPdf = uri
        }
    }
}