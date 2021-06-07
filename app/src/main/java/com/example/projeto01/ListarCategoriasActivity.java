package com.example.projeto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projeto01.database.CategoriaDAO;
import com.example.projeto01.modelo.Categoria;

public class ListarCategoriasActivity extends AppCompatActivity {

    private ListView listViewCategorias;
    private ArrayAdapter<Categoria> adapterCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_categorias);
        setTitle("Categorias");
        listViewCategorias = findViewById(R.id.listView_categorias);
        definirOnClickListenerListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        CategoriaDAO categoriaDao = new CategoriaDAO(getBaseContext());
        adapterCategorias = new ArrayAdapter<Categoria>(ListarCategoriasActivity.this,
                android.R.layout.simple_list_item_1,
                categoriaDao.listar());
        listViewCategorias.setAdapter(adapterCategorias);
    }
    public void definirOnClickListenerListView(){
        listViewCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Categoria categoriaClicada = adapterCategorias.getItem(position);
                Intent intent = new Intent(ListarCategoriasActivity.this, CadastroCategoriaActivity.class);
                intent.putExtra("categoriaEdicao", categoriaClicada);
                startActivity(intent);
            }
        });
    }
    public void onClickNovaCategoria(View v){
        Intent intent = new Intent(ListarCategoriasActivity.this, CadastroCategoriaActivity.class);
        startActivity(intent);
    }
    public void onClickProdutos(View v){
        Intent intent = new Intent(ListarCategoriasActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}