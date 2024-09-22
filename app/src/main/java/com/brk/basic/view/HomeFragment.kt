package com.brk.basic.view

import ApiInterface
import GenelViewModel
import android.app.AlertDialog
import android.app.Dialog
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.Transition
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brk.basic.base.ViewModelFactory
import com.brk.basic.data.api.ApiHelper
import com.brk.basic.data.api.Retrofit


import com.brk.basic.databinding.FragmentHomeBinding
import com.brk.basic.viewmodel.LoadingDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import java.io.IOException


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private lateinit var viewModel: GenelViewModel
    private lateinit var loading : Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        init()
        funcInit()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(Retrofit.getClient().create(ApiInterface::class.java)))
        )[GenelViewModel::class.java]
        loading = LoadingDialog(requireContext(),false).getLoading()
    }
    private fun funcInit(){


    }

    private fun init(){
        setupUI()

    }
    private fun setupUI() {
        binding.btnGenerate.setOnClickListener {
            val queryText = binding.etSearchQuery.text.toString()
            if (queryText.isNotEmpty()) {
                loading.show()
                viewModel.generateImageFromText(queryText)
            }
        }


        viewModel.imageLiveData.observe(viewLifecycleOwner, Observer { imageUrl ->
            if (!imageUrl.isNullOrEmpty()) {
                Glide.with(this)
                    .load(imageUrl)
                    .into(binding.ivGeneratedImage)
                binding.ivGeneratedImage.visibility = View.VISIBLE
                loading.dismiss()
                binding.imageView13.visibility = View.VISIBLE
                binding.btnSetWallpaper.visibility = View.VISIBLE
                binding.btnSetWallpaper.setOnClickListener {
                    setWallpaper(imageUrl)
                }
            } else {
                // Handle case where imageUrl is empty or null
                handleFailure()
                binding.imageView13.visibility = View.GONE
                binding.btnSetWallpaper.visibility = View.GONE
            }
        })
    }
    private fun handleFailure() {
        loading.dismiss()
        AlertDialog.Builder(requireContext())
            .setTitle("Error")
            .setMessage("Failed to load image. Please try again.")
            .setPositiveButton("Retry") { _, _ ->
                val queryText = binding.etSearchQuery.text.toString()
                if (queryText.isNotEmpty()) {
                    loading.show()
                    viewModel.generateImageFromText(queryText)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
    private fun setWallpaper(imageUrl: String) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
                ) {
                    val wallpaperManager = WallpaperManager.getInstance(requireContext())
                    try {
                        wallpaperManager.setBitmap(resource)
                        Toast.makeText(requireContext(), "Duvar kağıdı başarıyla ayarlandı!", Toast.LENGTH_SHORT).show()
                    } catch (e: IOException) {
                        Toast.makeText(requireContext(), "Duvar kağıdı ayarlanamadı!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Handle resource cleanup if necessary
                }
            })
    }


}