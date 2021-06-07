package com.example.projeto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.example.projeto01.database.EventoDAO;
import com.example.projeto01.modelo.Evento;


public class MainActivity extends AppCompatActivity {


    private ListView listViewProdutos;
    private ArrayAdapter<Evento> adapterProdutos;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Produtos");
        listViewProdutos = findViewById(R.id.listView_eventos);
        definironClickListinerListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDao = new EventoDAO(getBaseContext());
        adapterProdutos = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDao.listar());
        listViewProdutos.setAdapter(adapterProdutos);
    }

    private void definironClickListinerListView(){
        listViewProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterProdutos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
                intent.putExtra("produtoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }
    public void onClickNovoProduto(View v){
        Intent intent = new Intent(MainActivity.this, CadastroEventoActivity.class);
        startActivity(intent);
    }

    public void onClickCategorias(View v){
        Intent intent = new Intent(MainActivity.this, ListarCategoriasActivity.class);
        startActivity(intent);
        finish();
    }

}
