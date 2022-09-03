package one.app.hack2hire.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import one.app.hack2hire.databinding.ActivitySampleBinding
import one.app.hack2hire.model.BookingMessage
import one.app.hack2hire.settings.SettingsPreference

/**
 * Created by lengocanh on 29/07/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class SampleActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySampleBinding
    private lateinit var viewModel: SampleViewModel
    private val settingsPreference by lazy { SettingsPreference(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        viewBinding.btnGet.setOnClickListener {
            val messageId = viewBinding.etMessageId.text.toString().trim()
            if (messageId.isEmpty()) {
                Toast.makeText(this, "Message Id is required", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.getBookingMessage(messageId)
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(SampleViewModel::class.java)
        viewModel.setEndpoint(settingsPreference.getEndpoint())

        viewModel.loadingLiveData.observe(this) {
            viewBinding.tvMessage.visibility = View.GONE
            viewBinding.tvError.visibility = View.GONE
            viewBinding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE
            viewBinding.btnGet.isEnabled = it.not()
        }

        viewModel.errorLiveData.observe(this) {
            viewBinding.tvError.visibility = View.VISIBLE
            viewBinding.tvError.text = it
        }

        viewModel.reposLiveData.observe(this) {
            setupRepos(it)
        }
    }

    private fun setupRepos(message: BookingMessage) {
        viewBinding.tvError.visibility = View.GONE
        viewBinding.pbLoading.visibility = View.GONE
        viewBinding.tvMessage.visibility = View.VISIBLE
        viewBinding.tvMessage.text = Gson().toJson(message.data)
    }
}
