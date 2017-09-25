package com.kirtidkoplabs.newsreaderapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.LoggingMXBean;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class MainActivity extends AppCompatActivity {

    Map<Integer,String> articleURLs = new HashMap<Integer, String>();
    Map<Integer,String> articleTitles = new HashMap<Integer,String>();
    ArrayList<Integer> articleIDs =  new ArrayList<Integer>();

    SQLiteDatabase articleDB;
    ArrayList<String> title = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    ArrayList<String>  urls = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView listView = (ListView)findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,title);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent i = new Intent(getApplicationContext(),ArticleActivity.class);
                i.putExtra("articleUrl",urls.get(position));
                startActivity(i);
                Log.i("Article URL",urls.get(position));
            }
        });


        articleDB =  this.openOrCreateDatabase("Articles",MODE_PRIVATE,null);
        articleDB.execSQL("CREATE TABLE IF NOT EXISTS articles(id INTEGER PRIMARY KEY, articleId INTEGER,url VARCHAR,title VARCHAR,content VARCHAR)");

        updateListView();

        DownloadTask task =  new DownloadTask();


       // String result = null;
        try {

          task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
          //  updateListView();

        }

        catch(Exception e){

            e.printStackTrace();
        }

    }

    public void updateListView(){

        try{
        Cursor c =  articleDB.rawQuery("SELECT * FROM articles ORDER BY articleId DESC",null);

        int articleIdIndex =  c.getColumnIndex("articleId");
        int articleUrlIndex = c.getColumnIndex("url");
        int articleTitleindex =  c.getColumnIndex("title");

        c.moveToFirst();
        title.clear();


        while(c != null){

            title.add(c.getString(articleTitleindex));
            urls.add(c.getString(articleUrlIndex));

                        /*Log.i("ArticleID",Integer.toString(c.getInt(articleIdIndex)));
                        Log.i("ArticleURL",c.getString(articleUrlIndex));
                        Log.i("ArticleTitle",c.getString(articleTitleindex)); */
            c.moveToNext();

        }
        arrayAdapter.notifyDataSetChanged();

            /*Log.i("Article ID",articleIDs.toString());
            Log.i("Article URL",articleURLs.toString());
            Log.i("Article Title",articleTitles.toString());
            */

    }
        catch(Exception e){

            e.printStackTrace();
        }
    }

    public class DownloadTask extends AsyncTask<String , Void ,String>{

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }

                JSONArray jsonArray = new JSONArray(result);
                articleDB.execSQL("DELETE FROM articles");

                for (int i = 0; i < 20; i++) {

                    //Log.i("ArticleID",jsonArray.getString(i));

                    DownloadTask getArticle = new DownloadTask();
                    String articleId = jsonArray.getString(i);
                    url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articleId + ".json?print=pretty");
                    urlConnection =  (HttpURLConnection)url.openConnection();
                    in = urlConnection.getInputStream();
                    reader =  new InputStreamReader(in);

                    data = reader.read();
                    String articleInfo="";

                        while(data !=-1){

                            char current =  (char) data;
                            articleInfo +=current;
                            data= reader.read();
                        }



                    JSONObject jsonObject = new JSONObject(articleInfo);

                    String articleTitle = jsonObject.getString("title");
                    String articleUrl = jsonObject.getString("url");

                    articleIDs.add(Integer.valueOf(articleId));
                    articleTitles.put(Integer.valueOf(articleId), articleTitle);
                    articleURLs.put(Integer.valueOf(articleId), articleUrl);

                    String sql = "INSERT INTO articles(articleId,url,title)VALUES(?,?,?)";
                    SQLiteStatement statement = articleDB.compileStatement(sql);

                    statement.bindString(1, articleId);
                    statement.bindString(2, articleUrl);
                    statement.bindString(3, articleTitle);

                    statement.execute();


                }
            }
            catch(Exception e){

                e.printStackTrace();

            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            updateListView();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
