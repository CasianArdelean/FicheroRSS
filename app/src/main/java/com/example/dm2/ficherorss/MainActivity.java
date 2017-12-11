package com.example.dm2.ficherorss;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnCargar;
    private TextView txtResultado;
    private List<Noticia> noticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCargar=(Button)findViewById(R.id.btnCargar);
        txtResultado=(TextView)findViewById(R.id.txtResultado);

        btnCargar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               CargarXmlTask tarea=new CargarXmlTask();
                tarea.execute("http://www.europapress.es/rss/rss.aspx");
            }
        });

    }

    public class CargarXmlTask extends AsyncTask<String,Integer,Boolean>{

        protected Boolean doInBackground(String... params){
            RssParserSax saxparser=new RssParserSax(params[0]);
            noticias = saxparser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result){
            for(int i=0;i<noticias.size();i++){
                txtResultado.setText(txtResultado.getText().toString()+ System.getProperty("line.separator")+noticias.get(i).getTitulo());
            }

        }

    }
}
