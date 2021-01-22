package com.example.cams

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_scanner.*
import java.text.SimpleDateFormat
import java.util.*


class Scanner : AppCompatActivity() {
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()
    val integrator = IntentIntegrator(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)

        var bundle=intent.extras
        tvScannerText.text = bundle!!.getString("activityFrom")

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val sdf=SimpleDateFormat("dd-MM-yyyy HH:mm:SS")
                val curdate=sdf.format(Calendar.getInstance().time)
                val dateandtime=curdate.split(" ")
                Log.d("VALUES","${dateandtime[0]}")
                Log.d("VALUES","${dateandtime[1]}")
                myRef.child("students").child(result.contents.toString()).child(dateandtime[0]).child(dateandtime[1]).setValue(tvScannerText.text.toString())
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun scanCode(view: View) {
        //integrator.setTorchEnabled(true)
        integrator.initiateScan()
    }
}