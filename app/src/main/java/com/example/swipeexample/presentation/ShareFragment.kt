package com.example.swipeexample.presentation

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.swipeexample.data.PreferencesStore
import com.example.swipeexample.databinding.ShareFragmentBinding
import com.example.swipeexample.domain.interactors.GetFlagUseCase
import com.example.swipeexample.domain.interactors.SetFlogUseCase
import com.example.swipeexample.presentation.viewmodel.ShareViewModel
import java.io.File
import java.io.FileOutputStream

class ShareFragment: Fragment() {

    private lateinit var binding: ShareFragmentBinding
    private val viewModel: ShareViewModel by viewModels {
        val preferences = PreferencesStore(requireContext())
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ShareViewModel(
                    GetFlagUseCase(preferences),
                    SetFlogUseCase(preferences)
                ) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShareFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBinding()
    }

    private fun setUpBinding() {
        viewModel.getFlag()
        binding.btnShare.setOnClickListener {
            val screenShoot = takeScreenShot()
            share(screenShoot)
        }
        binding.btnPreferences.setOnClickListener {
            if (viewModel.flag.value == false) {
                viewModel.setFlag(true)
            } else {
                viewModel.setFlag(false)
            }
        }
        viewModel.flag.observe(viewLifecycleOwner) {
            binding.inTitle.text = it.toString()
        }
    }

    private fun takeScreenShot(): Uri {
        val bitmap = Bitmap.createBitmap(binding.screenshotArea.width, binding.screenshotArea.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        binding.screenshotArea.draw(canvas)

        val file = File.createTempFile("sharedScreen", ".jpg", requireContext().cacheDir)
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.close()

        val imageUri = FileProvider.getUriForFile(
            requireContext(),
            "com.example.swipeexample.provides",
            file
        )
        file.deleteOnExit()
        return imageUri
    }

    private fun share(fileUri: Uri) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_STREAM, fileUri)
            type = "image/jpg"
        }
        startActivity(Intent.createChooser(intent, null))
    }
}