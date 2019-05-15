package com.example1.black.my_application;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSON extends AsyncTask<URL,Void,Void> {

    static String data = ""; //Total Data
    static String name = "";
    String type;

    public JSON(String type){
        this.type = type;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }


    @Override
    protected Void doInBackground(URL ... urls) {
       try {
            String bufferdata = "";

            URL url = urls[0];
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            InputStream inputstream= urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream));

            do {
                bufferdata = bufferedReader.readLine();
                data += bufferdata; //a
                System.out.println("here");
            }while (bufferdata !=null);


        JSONObject data1= new JSONObject(data); //transform data to JSON

            JSONArray jsonArray = data1.getJSONArray("results"); // for enter the results of JSON

           if(type.equals("Main")){ // For Initial App Operation

               MainActivity.mainpeople.removeAll(MainActivity.mainpeople);

               for(int i= 0; i< jsonArray.length(); i++){


                   JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                   MainActivity.mainpeople.add(new People( //Every People saved in the list
                           (String)(jsonObject.get("name")), //name
                           ((int)(jsonObject.get("id"))),    //id
                           ((double)(jsonObject.get("popularity"))), //popularity
                           (String)jsonObject.get("profile_path")));//image path
                   MainActivity.urldone = true; //the main code can continue
               }
           }
            if(type.equals("Search")){ // For Search Operation

                MainActivity.mainpeople.removeAll(MainActivity.mainpeople);

                for(int i= 0; i< 5; i++){

                   JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    MainActivity.mainpeople.add(new People( //Every People saved in the list
                           (String)(jsonObject.get("name")), //name
                           ((int)(jsonObject.get("id"))),    //id
                           ((double)(jsonObject.get("popularity"))), //popularity
                           (String)jsonObject.get("profile_path")));//image path

                    MainActivity.mainpeople.get(i).toString();
               }
                MainActivity.urldone = true;
           }

            System.out.println("URL reading is done" );


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

       data=""; //data cleared

        return null;
    }
}
