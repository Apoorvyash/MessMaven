package com.androrubin.messmavendemo.ui.dashboard.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.androrubin.messmavendemo.MainActivity
import com.androrubin.messmavendemo.R
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.qrcode.encoder.QRCode
import com.journeyapps.barcodescanner.CaptureActivity
import org.json.JSONException
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class QrScan : Fragment(), EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {
    var scanQr: ImageView? = null
    var btnScan: Button? = null
    var edtCode: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_qr_scan, container, false)
        scanQr = view?.findViewById(R.id.scanQrImg)
        btnScan = view?.findViewById(R.id.scanQrBtn)
        edtCode = view?.findViewById(R.id.edtTxt)

        btnScan?.setOnClickListener {
            cameraTask()
        }

        scanQr?.setOnClickListener {
            cameraTask()
        }

        return view
    }

    private fun hasCameraAccess(): Boolean {
        return context!!.let { EasyPermissions.hasPermissions(it, android.Manifest.permission.CAMERA) }
    }

    private fun cameraTask() {

        if (hasCameraAccess()) {

            var qrScanner = IntentIntegrator(activity)
            qrScanner.setPrompt("scan a QR code")
            qrScanner.setCameraId(0)
            qrScanner.setOrientationLocked(true)
            qrScanner.setBeepEnabled(true)
            qrScanner.captureActivity = CaptureActivity::class.java
            qrScanner.initiateScan()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your camera so you can take pictures.",
                123,
                android.Manifest.permission.CAMERA
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Result Not Found", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    Toast.makeText(context, result.contents.toString(), Toast.LENGTH_LONG).show()
                    Log.d("QRCode",result.contents.toString())
                    edtCode!!.setText(result.contents.toString())
                } catch (exception: JSONException) {
                    Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    //edtCode!!.setText("")
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onRationaleDenied(requestCode: Int) {
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }

}