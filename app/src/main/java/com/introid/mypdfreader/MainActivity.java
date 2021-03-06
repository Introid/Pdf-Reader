package com.introid.mypdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView iv_pdf;
    public static ArrayList<File> fileList= new ArrayList<>();
    PDFAdapter obj_adapter;
    public static int REQUEST_PERMISSION =1;
    boolean boolean_permission;
    File dir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        iv_pdf= findViewById(R.id.list_view_pdf);
        dir = new File( Environment.getExternalStorageDirectory().toString() );
        
        permission_fn();


    }

    private void permission_fn() {
        if (ContextCompat.checkSelfPermission( MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale( MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE )){
                ActivityCompat.requestPermissions( MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION );
            }else{
                ActivityCompat.requestPermissions( MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_PERMISSION );
            }
        }else{
            boolean_permission= true;
            getFile(dir);
            obj_adapter= new PDFAdapter( getApplicationContext(),fileList );
            iv_pdf.setAdapter(obj_adapter);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        if (requestCode == REQUEST_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
                boolean_permission= true;
                getFile(dir);
                obj_adapter= new PDFAdapter( getApplicationContext(),fileList );
                iv_pdf.setAdapter(obj_adapter);
            }else {
                Toast.makeText( this, "Please Allow Permission",Toast.LENGTH_SHORT ).show();
            }
        }
    }

    public ArrayList<File> getFile(File dir){

}
}
