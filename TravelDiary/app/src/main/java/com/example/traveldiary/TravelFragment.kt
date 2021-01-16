package com.example.traveldiary

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_travel.*
import java.io.ByteArrayOutputStream
import java.util.jar.Manifest


class TravelFragment : Fragment() {
    var selectedİmg: Uri?=null
    var selectedBitmp: Bitmap?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_travel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSave.setOnClickListener {
            save(it)
        }
        imageView.setOnClickListener{
            selectImage(it)
        }
        
    }

    fun save(view: View){
        val cityName=cityNameText.text.toString()
        val detailInfo=detailText.text.toString()
        if(selectedBitmp!=null){
            val smallBitmap=reduceImageSize(selectedBitmp!!,300)
            val outputStream=ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArr=outputStream.toByteArray()

            try {
                context?.let {
                    val database=it.openOrCreateDatabase("Travels",Context.MODE_PRIVATE,null)
                    database.execSQL("CREATE TABLE IF NOT EXISTS travels (id INTEGER PRIMARY KEY, cityName VARCHAR,detailInfo VARCHAR, image BLOB)")
                    val sqlString="INSERT INTO travels (cityName,detailInfo,image) VALUES (?, ?, ?)"
                    val statement=database.compileStatement(sqlString)
                    statement.bindString(1,cityName)
                    statement.bindString(2,detailInfo)
                    statement.bindBlob(3,byteArr)
                    statement.execute()
                }

            }catch (e:Exception){
                e.printStackTrace()
            }

            val action=TravelFragmentDirections.actionTravelFragmentToListFragment()
            Navigation.findNavController(view).navigate(action)
        }

    }

    fun selectImage(view: View){
        activity?.let{
            if(ContextCompat.checkSelfPermission(it.applicationContext, android.Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){ //not permissioned
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }else{ //permission already granted
                val galIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galIntent,2)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==1){
            if(grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){//we got permission
                val galIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==2&&resultCode==Activity.RESULT_OK&&data!=null){
            selectedİmg=data.data
            try {
                context?.let {
                    if(selectedİmg!=null){
                        if(Build.VERSION.SDK_INT>=28){
                            val source=ImageDecoder.createSource(it.contentResolver,selectedİmg!!)
                            selectedBitmp=ImageDecoder.decodeBitmap(source)
                            imageView.setImageBitmap(selectedBitmp)
                        }
                    }else{
                        selectedBitmp=MediaStore.Images.Media.getBitmap(it.contentResolver,selectedİmg)
                        imageView.setImageBitmap(selectedBitmp)
                    }
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun reduceImageSize(selectedBitmp:Bitmap,maxSize:Int):Bitmap{
        var width=selectedBitmp.width
        var height=selectedBitmp.height
        val bitmapRate:Double=width.toDouble()/height.toDouble()
        if(bitmapRate>1){ //horizontal image
            width=maxSize
            val newHeight=width/bitmapRate
            height=newHeight.toInt()
        }else{ //vertical image
            height=maxSize
            val newWidth=height*bitmapRate
            width=newWidth.toInt()
        }

        return Bitmap.createScaledBitmap(selectedBitmp,width,height,true)
    }
}