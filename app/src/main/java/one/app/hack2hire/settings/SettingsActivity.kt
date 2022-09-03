package one.app.hack2hire.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import one.app.hack2hire.databinding.ActivitySettingsBinding

/**
 * Created by lengocanh on 29/08/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class SettingsActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivitySettingsBinding
    private val settingsPreference by lazy { SettingsPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySettingsBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        setupView()
    }

    private fun setupView() {
        viewBinding.etEndpoint.setText(settingsPreference.getEndpoint())
        viewBinding.btnSave.setOnClickListener {
            val newEndpoint = viewBinding.etEndpoint.text.toString().toString()
            if (newEndpoint.isEmpty()) {
                Toast.makeText(this, "API Endpoint is required", Toast.LENGTH_SHORT).show()
            } else {
                settingsPreference.setEndpoint(newEndpoint)
                finish()
            }
        }
    }
}
