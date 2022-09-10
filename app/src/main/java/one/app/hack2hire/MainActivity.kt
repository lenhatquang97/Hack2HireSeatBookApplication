package one.app.hack2hire

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import one.app.hack2hire.databinding.ActivityMainBinding
import one.app.hack2hire.exam.ExamActivity
import one.app.hack2hire.sample.SampleActivity
import one.app.hack2hire.settings.SettingsActivity

/**
 * Created by lengocanh on 29/07/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
    }

}
