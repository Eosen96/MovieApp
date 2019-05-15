package com.example1.black.my_application;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static  boolean urldone = false;
    public static ArrayList<People> mainpeople = new ArrayList<>();
    static ListAdapter customlist;
    ListView listViewist;
    int page = 1;
    static String query = "";
    String type = "main";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url(type,page,query); // main-screen for the initial


        while (!urldone); //wait for the url

        ListAdapter customlist = new PeopleListAdp(this, mainpeople);
        listViewist = (ListView) findViewById(R.id.list11);
        listViewist.setAdapter(customlist); //Custom List Adapter

        ((PeopleListAdp)listViewist.getAdapter()).notifyDataSetChanged(); //Update the list

        int aqua = getResources().getColor(R.color.coloraqua);
        listViewist.setBackgroundColor(aqua);

        Button nextB = (Button) findViewById(R.id.nextButton);
        Button prevB = (Button) findViewById(R.id.prevButton);
        final TextView pagetxt = (TextView) findViewById(R.id.pagetxt);
        pagetxt.setText("Page: 1");


        nextB.setOnClickListener(new View.OnClickListener() { //for next page
            @Override
            public void onClick(View v) {

                page++;
                url(type,page,query);
                pagetxt.setText("Page: " + page);

            }
        });

        prevB.setOnClickListener(new View.OnClickListener() { //for previous page
            @Override
            public void onClick(View v) {

                if (page == 1){
                    Toast.makeText(getApplicationContext(),"There is no previous page!",Toast.LENGTH_SHORT); }
                else{
                    page--;
                    pagetxt.setText("Page: " + page);
                    type= "main";
                    url(type,page,query); //method for the mainscreen
            }}
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //For submit text

                if (query.equals("main")){
                    url("main",1,query);}

               else {
                    type = "search";
                    page = 1;

                    url(type, 1, query); //method for the searching
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false; } //take action when text is change
        });

    return super.onCreateOptionsMenu(menu);
    }

    private void url(String type, int page, String query){

        if (type.equals("search")){

            try {
                URL searchurl = new URL(String.format("https://api.themoviedb.org/3/search/person?api_key=f8c1a5f5b4725352c3c710d0e8f1dbf8&language=en-US&query=%s&page=%d&include_adult=false",query,page));
                System.out.println(searchurl);
                new JSON("Search").execute(searchurl);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            while (!urldone); //wait for the url

            ((PeopleListAdp)listViewist.getAdapter()).notifyDataSetChanged();
        }



        if (type.equals("main")){ // For main-screen

            try {
                URL mainURL= new URL("https://api.themoviedb.org/3/person/popular?api_key=f8c1a5f5b4725352c3c710d0e8f1dbf8&language=en-US&page="+page); //Main_Screen URL
                new JSON("Main").execute(mainURL); //JSON for main-screen
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }



    }


}
