package com.phong.hotrohoctap.News;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.phong.hotrohoctap.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {
     ListView lvNew;
     AdapterDocTin adapterDocBao;
     ArrayList<DocTin> mangdocbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         mangdocbao=new ArrayList<>();

        lvNew=(ListView)findViewById(R.id.lvNews);

        // duong dan cua trang web
        new ReadRSS().execute("https://vnexpress.net/rss/giao-duc.rss");



        lvNew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(NewsActivity.this,wedview.class);
                intent.putExtra("Link",mangdocbao.get(i).Link);
                startActivity(intent);
            }
        });

    }

    // Doc Du Lieu
    private class ReadRSS extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuffer content=new StringBuffer();
            //kiem tra url va doc du lieu
            try {
                URL url=new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    content.append(line);
                }

                // dong diu lieu
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  content.toString();
        }
        @Override
        protected void onPostExecute(String s) {


            // doc trong Item :title,link , trong trang wed
            XMLDOMParser parser=new XMLDOMParser();
            Document document=parser.getDocument(s);

            NodeList nodeList=document.getElementsByTagName("item");
            NodeList nodeList1=document.getElementsByTagName("description");
            String tieude="";
            String link="";
            String hinhanh="";
            //doc tung du lieu trong rss
            for (int i=0;i<nodeList.getLength();i++){
                ///hinh anh
                // description co 2 phan:
                //  phan1: chua string  (.item(i))
                // phan2: chua duong dan hinh anh  (.item(i+1))
                String cdata=nodeList1.item(i+1).getTextContent();

                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher m =p.matcher(cdata);

                if (m.find()){
                    hinhanh=m.group(1);
                }
                //doc du lieu tu tren xun
                Element element= (Element) nodeList.item(i);

                // co elemnt doc phan tu? con trong item co ten Title
                tieude=parser.getValue(element,"title");
                // co elemnt doc phan tu? con trong item co ten link
                link=parser.getValue(element,"link");


                mangdocbao.add(new DocTin(tieude,link,hinhanh));
                adapterDocBao =new AdapterDocTin(NewsActivity.this,android.R.layout.simple_list_item_1,mangdocbao);
                lvNew.setAdapter(adapterDocBao);


            }
            super.onPostExecute(s);


        }

    }


}
