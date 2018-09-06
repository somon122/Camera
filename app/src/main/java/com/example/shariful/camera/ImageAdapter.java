package com.example.shariful.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<ImageMethod> {

    ArrayList<ImageMethod>imageList = new ArrayList<>();
     Context mContext;


    public ImageAdapter(@NonNull Context context, @NonNull ArrayList<ImageMethod>imageList) {
        super(context, R.layout.image_view, imageList);
        mContext = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);

        convertView = inflater.inflate(R.layout.image_view,parent,false);

        ImageView imageView = convertView.findViewById(R.id.Image_id);

        ImageMethod imageMethod = imageList.get(position);

        Bitmap bitmap = decodeBase64(imageMethod.getImage());

        imageView.setImageBitmap(bitmap);


        return convertView;
    }
    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
