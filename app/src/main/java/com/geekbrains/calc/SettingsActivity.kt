package com.geekbrains.calc

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import com.google.android.material.button.MaterialButton

class SettingsActivity : AppCompatActivity() {
    private var themeId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let { themeId = it.getInt(THEMEID) }
        setContentView(R.layout.activity_settings)
        var rg = findViewById<RadioGroup>(R.id.radio_settings_group)
        findViewById<RadioButton>(R.id.radio_theme1).setOnClickListener { themeId = 0 }
        findViewById<RadioButton>(R.id.radio_theme2).setOnClickListener { themeId = 1 }
        rg.check(if (themeId == 0) R.id.radio_theme1 else R.id.radio_theme2)
        findViewById<MaterialButton>(R.id.settings_back).setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply { putExtra(THEMEID, themeId) })
            finish()
        }
    }

}