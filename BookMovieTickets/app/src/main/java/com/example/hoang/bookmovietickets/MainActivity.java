package com.example.hoang.bookmovietickets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView, recyclerView1;
    private RVAdapter adapter;
    private List<MovieModel> list;
    private DBHelper dbHelper;
    private MenuItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getControls();

    }
//e, bo ho t luon cai recycle, 1 dong anh ,cho it it thoi-_-
    // bo di thi khi book tickets n k hien trong my list dau, the cu moi lan book lai them 3 phim giong nhau a -_- :3 de sua xem
    public void getControls(){
        dbHelper = new DBHelper(this);
        MovieModel model1 = new MovieModel("Guardian Of The Galaxy", "This is a greate movie , You'll be regret so much if you don't see it This is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see itThis is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/guradian", false );
        MovieModel model2 = new MovieModel("AntMan", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/antman", false );
        MovieModel model3 = new MovieModel("Despicableme", "This is a greate movie , You'll be regret so much if you don't see it",
                "@drawable/despicableme", false );
        recyclerView = (RecyclerView) findViewById(R.id.phimdangchieu);
        recyclerView1 = (RecyclerView) findViewById(R.id.phimsapchieu);
        list = new ArrayList<MovieModel>();
        list = dbHelper.getListMovie();
        if (list.size() == 0){
            dbHelper.createMovie(model1);
            dbHelper.createMovie(model2);
            dbHelper.createMovie(model3);
            list = dbHelper.getListMovie();
        }
        adapter = new RVAdapter(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView1.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                }
                return 1;
            }
        });

        gridLayoutManager1.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView1.setLayoutManager(gridLayoutManager1);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    adapter.getFilter().filter("");
                }else {
                    adapter.getFilter().filter(newText.toString());
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.user) {
            user = item;
            Intent intent = new Intent(MainActivity.this, LoginForm.class);
            startActivityForResult(intent, 1);//day la ham hien form dang nhap nay
            // Handle the camera action
        } else if (id == R.id.my_list) {
            Intent intent = new Intent(MainActivity.this, MyListTicketsActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                boolean isLogin = data.getBooleanExtra("isLogin", false);
                if (isLogin){
                    user.setTitle("User Profile");
                }//da bao r, thu settile khi khoi dong chuong trinh xem
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }//hahah dc chua t k xem, ko dc nhu cu~ cai settitle sai, m thu set khi no khoi dong xem dc k, khoi dong cai set luon
    // bay h, m thu vua khoi dong chuong trinh roi settitle luon xem nao , chac k phai o day dau dau tien co bi loi dau

}
