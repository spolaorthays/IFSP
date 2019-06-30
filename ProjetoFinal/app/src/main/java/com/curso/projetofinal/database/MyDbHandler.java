package com.curso.projetofinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.curso.projetofinal.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    //Constantes do Database
    public static final String DATABASE_NAME = "projeto_final.db";
    public static final int VERSION = 1;

    //Constantes para a tabela de usuários
    public static final String TABLE_USER = "usuario";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NOME = "nome";
    public static final String COLUMN_USER_CPF = "cpf";
    public static final String COLUMN_USER_IDADE = "idade";
    public static final String COLUMN_USER_TELEFONE = "telefone";
    public static final String COLUMN_USER_EMAIL = "email";

    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " +TABLE_USER+ "(" +
                COLUMN_USER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NOME + " TEXT, " +
                COLUMN_USER_CPF + " TEXT, " +
                COLUMN_USER_IDADE + " TEXT, " +
                COLUMN_USER_TELEFONE + " TEXT, " +
                COLUMN_USER_EMAIL + " TEXT " + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertUser(Usuario usuario){
        ContentValues values = new ContentValues();
        if (usuario.getNome()!= null)
            values.put(COLUMN_USER_NOME, usuario.getNome());
        if (usuario.getCpf()!= null)
            values.put(COLUMN_USER_CPF, usuario.getCpf());
        if (usuario.getIdade()!= null)
            values.put(COLUMN_USER_IDADE, usuario.getIdade());
        if (usuario.getTelefone()!= null)
            values.put(COLUMN_USER_TELEFONE, usuario.getTelefone());
        if (usuario.getEmail()!= null)
            values.put(COLUMN_USER_EMAIL, usuario.getEmail());

        SQLiteDatabase db = getWritableDatabase();
        long insertedId = db.insert(TABLE_USER, null, values);
        db.close();
        return insertedId;
    }

    public List<Usuario> getAllUsers(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " +TABLE_USER+ " WHERE 1 ORDER BY " +COLUMN_USER_NOME+ " COLLATE NOCASE ASC ";

        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        List<Usuario> usuarioList = new ArrayList<>();
        while (!cursor.isAfterLast()){
            Usuario usuario = new Usuario();
            if (cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))!= null){
                usuario.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
                usuario.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NOME)));
                usuario.setCpf(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CPF)));
                usuario.setIdade(cursor.getString(cursor.getColumnIndex(COLUMN_USER_IDADE)));
                usuario.setTelefone(cursor.getString(cursor.getColumnIndex(COLUMN_USER_TELEFONE)));
                usuario.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));

            }
            usuarioList.add(usuario);
            cursor.moveToNext();
        }
        db.close();
        return usuarioList;
    }

    public void deleteAll(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_USER);
    }

}
