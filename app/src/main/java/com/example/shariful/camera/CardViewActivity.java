package com.example.shariful.camera;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class CardViewActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mCardView1,mCardView2,mCardView3,mCardView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        mCardView1 = findViewById(R.id.cardView1);
        mCardView2 = findViewById(R.id.cardView2);
        mCardView3 = findViewById(R.id.cardView3);
        mCardView4 = findViewById(R.id.cardView4);

        mCardView1.setOnClickListener(this);
        mCardView2.setOnClickListener(this);
        mCardView3.setOnClickListener(this);
        mCardView4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.cardView1)
        {
         startActivity(new Intent(CardViewActivity.this,MainActivity.class));

        }else if (v.getId() == R.id.cardView2)
        {
            startActivity(new Intent(CardViewActivity.this,MainActivity.class));

        } else if (v.getId() == R.id.cardView3)
        {
            startActivity(new Intent(CardViewActivity.this,MainActivity.class));

        } else if (v.getId() == R.id.cardView4)
        {
            startActivity(new Intent(CardViewActivity.this,MainActivity.class));

        }else {
            Toast.makeText(this, "Sorry for that", Toast.LENGTH_SHORT).show();
        }



    }
}
