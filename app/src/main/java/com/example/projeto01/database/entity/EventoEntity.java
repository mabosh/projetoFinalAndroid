package com.example.projeto01.database.entity;

import android.provider.BaseColumns;

public class EventoEntity implements BaseColumns {

    private EventoEntity(){}

    public static final String TABLE_NAME = "produto";
    public static final String COLUMN_NAME_NOME = "nome";
    public static final String COLUMN_NAME_VALOR = "valor";
    public static final String COLUMN_NAME_LOCAL = "local";
    public static final String COLUMN_NAME_ID_CATEGORIA = "idcategoria";
}
