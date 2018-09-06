package com.example.shariful.camera;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button captureBtn,saveBtn;
    private String mImage;
    MydbHelper mydbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mydbHelper=new MydbHelper(this);

        captureBtn=findViewById(R.id.captureID);
        saveBtn=findViewById(R.id.saveID);
        imageView=findViewById(R.id.imageViewID);


        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);

            }
        });


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImageMethod imageMethod = new ImageMethod(mImage);

                boolean isImage =  mydbHelper.insertData(imageMethod);



             if (isImage) {
                   Toast.makeText(getApplicationContext(),"Successfully", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(MainActivity.this,ShowImageActivity.class));

             } else {
                   Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show(); }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap=(Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        mImage = encodeToBase64(bitmap,Bitmap.CompressFormat.JPEG,100);



    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    public void LogInButton(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.custom_login,null);
        final EditText email = view1.findViewById(R.id.logInEmail_id);
        final EditText password = view1.findViewById(R.id.logInPassword_id);
        Button logInButton = view1.findViewById(R.id.logIn_id);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString();
                String mPassword = password.getText().toString();

                if (!mEmail.isEmpty() && !mPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,CardViewActivity.class));

                }else {
                    Toast.makeText(MainActivity.this, "LogIn Field", Toast.LENGTH_SHORT).show();
                }



            }
        });
        builder.setView(view1);
        AlertDialog dialog = builder.create();
        dialog.show();


    }
}


