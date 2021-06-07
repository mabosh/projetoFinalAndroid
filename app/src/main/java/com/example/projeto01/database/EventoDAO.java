package com.example.projeto01.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.projeto01.database.entity.CategoriaEntity;
import com.example.projeto01.database.entity.EventoEntity;
import com.example.projeto01.modelo.Categoria;
import com.example.projeto01.modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT produto._id, nome, valor, local, idcategoria, descricao FROM " +
            EventoEntity.TABLE_NAME +
            " INNER JOIN " + CategoriaEntity.TABLE_NAME + " ON " + EventoEntity.COLUMN_NAME_ID_CATEGORIA +
             " = " + CategoriaEntity.TABLE_NAME + "." + CategoriaEntity._ID;
    private DBGateway dbGateway;

    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public  boolean salvar(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_VALOR, evento.getValor());
        contentValues.put(EventoEntity.COLUMN_NAME_LOCAL, evento.getLocal());
        contentValues.put(EventoEntity.COLUMN_NAME_ID_CATEGORIA,evento.getCategoria().getId());
        if (evento.getId()> 0){
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues,
                    EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;
    }

    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            Float valor = cursor.getFloat(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_VALOR));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL));

            int idCategoria = cursor.getInt(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_ID_CATEGORIA));
            String descricao = cursor.getString(cursor.getColumnIndex(CategoriaEntity.COLUMN_NAME_DESCRICAO));
            Categoria categoria = new Categoria(idCategoria, descricao);
            eventos.add(new Evento(id, nome, valor, local, categoria));
        }
        cursor.close();
        return eventos;
    }
}
