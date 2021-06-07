package com.example.projeto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projeto01.database.CategoriaDAO;
import com.example.projeto01.modelo.Categoria;

public class CadastroCategoriaActivity extends AppCompatActivity {


    private int id = 0;
    private EditText editTextDescricao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_categoria);
        setTitle("Cadastro de Categoria");
        editTextDescricao = findViewById(R.id.editText_descricao_categoria);
        carregarCategoria();


    }

    public void carregarCategoria(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("categoriaEdicao") != null){
            Categoria categoria = (Categoria) intent.getExtras().get("CategoriaEdicao");
            EditText editTextDescricao = findViewById(R.id.editText_descricao_categoria);
            editTextDescricao.setText(categoria.getDescricao());
            id = categoria.getId();

        }
    }

    public void  onClickVoltar (View v){
        finish();
    }

    public void onClickSalvar(View v){
        EditText editTextDescricao = findViewById(R.id.editText_descricao_categoria);

        String descricao = editTextDescricao.getText().toString();

        Categoria categoria = new Categoria(id, descricao);
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        boolean salvou = categoriaDAO.salvar(categoria);
        if (salvou){
            finish();
        }else{
            Toast.makeText(CadastroCategoriaActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }
    }
}