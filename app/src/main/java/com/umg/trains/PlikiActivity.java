package com.umg.trains;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class PlikiActivity extends AppCompatActivity {
    Button odczyt;
    Button zapis;
    TextView tekst;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pliki);
        tekst=findViewById(R.id.textView4);
        odczyt=findViewById(R.id.button9);
        odczyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    readFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        zapis=findViewById(R.id.button8);
        zapis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    createFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void createFile() throws IOException {
        String filename = "plik.txt";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void readFile() throws IOException {
        FileInputStream fis;
        fis = openFileInput("plik.txt");
        StringBuffer fileContent = new StringBuffer("");
        int n=0;
        byte[] buffer = new byte[1024];

        while ((n = fis.read(buffer)) != -1)
        {
            fileContent.append(new String(buffer, 0, n));
        }
        tekst.setText(fileContent);


        // ZAMYKANIE PLIKU:
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getStringFromInputStream(InputStream is) {

        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(is));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }
}
