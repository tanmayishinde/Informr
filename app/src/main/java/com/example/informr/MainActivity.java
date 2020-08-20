package com.example.informr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.informr.data.Article;
import com.example.informr.data.ArticleAdapter;
import com.example.informr.data.ArticleData;
import com.example.informr.data.ArticleListAsyncResponse;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //camera
        Button btncamera = (Button)findViewById(R.id.camera);


        btncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        });


        new ArticleData().getNewsList(new ArticleListAsyncResponse() {
            @Override
            public void processFinish(final ArrayList<Article> articles) {

                recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                articleAdapter = new ArticleAdapter(articles, getApplicationContext());

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(articleAdapter);

                articleAdapter.setOnClickListener(new ArticleAdapter.OnItemClickListner() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Article article = articles.get(position);
                        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                        intent.putExtra("url", article.getNewsUrl());
                        startActivity(intent);


                        Toast.makeText(getApplicationContext(), article.getNewsUrl(), Toast.LENGTH_LONG)
                                .show();



            }
        });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

    }



}

