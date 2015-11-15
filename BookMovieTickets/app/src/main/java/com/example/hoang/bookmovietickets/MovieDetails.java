package com.example.hoang.bookmovietickets;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetails extends AppCompatActivity {

    private TextView movieTitle;
    private ImageView movieImage;
    private TextView movieDes;
    private Button btnRemove, btnBook;
    private ImageButton btnShare;
    private DBHelper dbHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getControls();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("DATA");
        id = bundle.getInt("ID");
        MovieModel model = dbHelper.getMovie(id);
        movieTitle.setText(model.getTitle());
        int imageResource = this.getResources().getIdentifier(model.getImage(), null, this.getPackageName());
        Drawable drawable = this.getResources().getDrawable(imageResource);
        movieImage.setImageDrawable(drawable);
        movieDes.setText(model.getDescription());
        addEvents();
    }

    public void getControls(){
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieDes = (TextView) findViewById(R.id.movieDes);
        movieImage = (ImageView) findViewById(R.id.movieImage);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnBook = (Button) findViewById(R.id.btnBookTicket);
        btnShare = (ImageButton) findViewById(R.id.btnShare);
        dbHelper = new DBHelper(this);
    }

    public void addEvents(){
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetails.this, BookTicketsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", id);
                intent.putExtra("DATA", bundle);
                startActivity(intent);
            }
        });
    }

}
