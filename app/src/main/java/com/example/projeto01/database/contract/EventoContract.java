package com.example.projeto01.database.contract;

import com.example.projeto01.database.entity.CategoriaEntity;
import com.example.projeto01.database.entity.EventoEntity;

public class EventoContract {

    private EventoContract(){}

    public static final String criarTabela(){
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME_NOME + " TEXT," +
                EventoEntity.COLUMN_NAME_LOCAL + " TEXT," +
                EventoEntity.COLUMN_NAME_VALOR + " REAL," +
                EventoEntity.COLUMN_NAME_ID_CATEGORIA + " INTEGER," +
                "FOREIGN KEY (" + EventoEntity.COLUMN_NAME_ID_CATEGORIA + ") REFERENCES " +
                CategoriaEntity.TABLE_NAME + "(" + CategoriaEntity._ID + "))";
    }

    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }
}
