package com.example.projeto01.database.contract;

import com.example.projeto01.database.entity.CategoriaEntity;

public class CategoriaContract {

    private CategoriaContract(){}

    public static final String criarTabela(){
        return "CREATE TABLE " + CategoriaEntity.TABLE_NAME + " (" +
                CategoriaEntity._ID + " INTEGER PRIMARY KEY," +
                CategoriaEntity.COLUMN_NAME_DESCRICAO + " TEXT)";
    }


    public static final String removerTabela(){
        return "DROP TABLE IF EXISTS " + CategoriaEntity.TABLE_NAME;
    }
}
