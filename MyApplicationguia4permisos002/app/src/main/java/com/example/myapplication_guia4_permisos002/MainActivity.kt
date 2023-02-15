package com.example.myapplication_guia4_permisos002

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val TAG ="Practica 04 - permisos"
    private val CODIGOSSOLICITUDGRABAR=101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarPermisos()
    }

    private fun configurarPermisos(){
        val permission=ContextCompat.checkSelfPermission(this,android.Manifest.permission.RECORD_AUDIO)
        if(permission!=PackageManager.PERMISSION_GRANTED)
            Log.i(TAG,"permiso denegado para grabar")

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.RECORD_AUDIO)){
            val builder=AlertDialog.Builder(this)
            builder.setMessage("permiso para acceder al migrofono es requerido para grabar audio")
                .setTitle("Permission requiered")
            builder.setPositiveButton("ok"){
                dialog,id->Log.i(TAG,"Selecionado")
                hacerSolicitudPermiso()
            }
            val dialog=builder.create()
            dialog.show()
        }else{
            hacerSolicitudPermiso()
        }
    }

    private fun hacerSolicitudPermiso(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECORD_AUDIO),CODIGOSSOLICITUDGRABAR)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CODIGOSSOLICITUDGRABAR->{
                if(grantResults.isEmpty()||grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    Log.i(TAG,"PERMISO A SIDO DENEGADO POR EL USUARIO")
                    Toast.makeText(applicationContext,"permiso a sido denegador por el usuario",Toast.LENGTH_SHORT).show()
                }else{
                    Log.i(TAG,"permiso concedido al usuario")
                    Toast.makeText(applicationContext,"permiso concedido al usuario",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}