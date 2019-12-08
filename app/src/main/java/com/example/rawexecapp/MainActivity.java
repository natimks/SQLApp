package com.example.rawexecapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = openOrCreateDatabase("banco", MODE_PRIVATE, null);
        criarTabela();

//        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Natalia Maria' )");
//        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Isabela ' )");
//        bd.execSQL("INSERT INTO alunos (nome) VALUES ('Mateus Moraes' )");
        recuperarDados();
    }

    public void criarTabela() {
        bd.execSQL("CREATE TABLE IF NOT EXISTS alunos (" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR )");
    }

    public void recuperarDados() {
        Cursor cursor = bd.rawQuery("SELECT id, nome FROM alunos ",
                null);
        cursor.moveToFirst();
        ArrayList<String> itens = new ArrayList<>();
        do {
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            itens.add(nome);
        } while (cursor.moveToNext());
        ListView list;
        list = findViewById(R.id.list_view_alunos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );
        list.setAdapter(adapter);
    }
}
