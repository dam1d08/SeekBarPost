package com.pdm.seekbarpost;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    SeekBar pbar;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbar = (SeekBar) findViewById(R.id.seekBar);
        boton = (Button) findViewById(R.id.button);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               empezar();
            }
        });

    }

    private void empezar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                pbar.post(new Runnable() {
                    @Override
                    public void run() {
                        pbar.setProgress(0);
                    }
                });
                for(int i =1; i<=10;i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pbar.post(new Runnable() {
                        @Override
                        public void run() {
                            pbar.incrementProgressBy(10);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();

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
