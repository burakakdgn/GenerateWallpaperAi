package com.brk.basic.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brk.basic.R
import com.brk.basic.databinding.ActivityDetayBinding
import com.brk.basic.databinding.ActivityMainBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class DetayActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    }
