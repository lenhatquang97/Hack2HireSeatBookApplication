package one.app.hack2hire.exam

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import one.app.hack2hire.databinding.ActivityExamBinding

/**
 * Created by lengocanh on 29/08/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class ExamActivity : AppCompatActivity() {

    lateinit var viewBinding: ActivityExamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityExamBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding.root)
    }
}
