package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME ="namafile.txt";
    Button buatFile, ubahFile, bacaFile, delateFile;
    TextView textBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        buatFile = findViewById(R.id.buttonBuatFile);
        ubahFile = findViewById(R.id.buttonUbahFile);
        bacaFile = findViewById(R.id.buttonBacaFile);
        delateFile = findViewById(R.id.buttonHapusFile);
        textBaca = findViewById(R.id.textBaca);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        delateFile.setOnClickListener(this);

    }
    void buatFile(){
        String isiFile = "Coba Isi Data File Tetx";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,true);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void ubahFile(){
        String ubah = "Semangat Berjuang Peserta DTS";
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;

        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();

            Toast.makeText(this, "file berhasil diubah", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bacaFile(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

        if(file.exists()){
            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
                Toast.makeText(this, "Membaca File", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e){
                System.out.println("Error "+e.getMessage());
            }
            textBaca.setText(text.toString());
        }
    }

    void hapusFile(){
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void onClick(View v) { jalankanPerintah(v.getId()); }
    public void jalankanPerintah(int id) {
        switch (id) {
            case R.id.buttonBuatFile:
                buatFile();
                break;
            case R.id.buttonBacaFile:
                bacaFile();
                break;
            case R.id.buttonUbahFile:
                ubahFile();
                break;
            case R.id.buttonHapusFile:
                hapusFile();
                break;
        }
    }
}