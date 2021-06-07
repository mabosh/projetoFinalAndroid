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
import com.example.projeto01.database.EventoDAO;
import com.example.projeto01.modelo.Categoria;
import com.example.projeto01.modelo.Evento;

public class CadastroEventoActivity extends AppCompatActivity {

   private int id = 0;
    private Spinner spinnerCategorias;
    private ArrayAdapter<Categoria> categoriaAdapter;
    private EditText editTextNome;
    private EditText editTextValor;
    private EditText editTextLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");
        spinnerCategorias = findViewById(R.id.spinner_categorias);
        editTextNome = findViewById(R.id.editText_nome);
        editTextValor = findViewById(R.id.editText_valor);
        editTextLocal = findViewById(R.id.editText_local);
        carregarCategorias();
        carregarProduto();

    }

    private void carregarCategorias(){
        CategoriaDAO categoriaDAO = new CategoriaDAO(getBaseContext());
        categoriaAdapter = new ArrayAdapter<Categoria>(CadastroEventoActivity.this,
                android.R.layout.simple_spinner_item,
                categoriaDAO.listar());
        spinnerCategorias.setAdapter(categoriaAdapter);
    }

    private void carregarProduto(){
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&
                intent.getExtras().get("produtoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("produtoEdicao");
            editTextNome.setText(evento.getNome());
            editTextLocal.setText(evento.getLocal());
            editTextValor.setText(String.valueOf(evento.getValor()));
            int posicaoCategoria = obterPosicaoCategoria(evento.getCategoria());
            spinnerCategorias.setSelection(posicaoCategoria);
            id = evento.getId();
        }
    }

    private int obterPosicaoCategoria(Categoria categoria){
        for (int posicao = 0; posicao < categoriaAdapter.getCount(); posicao++){
            if (categoriaAdapter.getItem(posicao).getId() == categoria.getId()){
                return posicao;
            }
        }
        return 0;
    }

    public void onClickVoltar(View v){
        finish();
    }

    public void onClickSalvar(View v){
        String nome = editTextNome.getText().toString();
        Float valor = Float.parseFloat(editTextValor.getText().toString());
        String local = editTextLocal.getText().toString();
       // Categoria categoria = (Categoria) spinnerCategorias.getSelectedItem();
        int posicaoCategoria = spinnerCategorias.getSelectedItemPosition();
        Categoria categoria = (Categoria) categoriaAdapter.getItem(posicaoCategoria);
        Evento evento = new Evento(id,nome,valor, local, categoria);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        if (salvou){
            finish();
        }else {
                Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
            }
        }

    }
