package com.jackson.cellularautomation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton

class MainActivity : AppCompatActivity(), NumberPickerDialog.OnValueChangeListener {
    override fun onChanged(rule: Int, blockSize: Int) {
        cellularView.start(rule, 0, pixelSize = blockSize)
    }

    lateinit var cellularView: CellularView
    lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cellularView = findViewById(R.id.cellular_view)
        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            val numPickDialog = NumberPickerDialog()
            numPickDialog.setValueChangeListener(this)
            numPickDialog.show(supportFragmentManager, "rule picker")
        }

        cellularView.start(30, 0, 10)
    }
}
