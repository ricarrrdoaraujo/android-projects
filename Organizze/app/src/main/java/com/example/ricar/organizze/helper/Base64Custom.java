package com.example.ricar.organizze.helper;

import android.util.Base64;

public class Base64Custom {

    public static String codificarBase64(String texto) {
        //encodeToString(arraydebytes, flag)
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)","");

    }

    public static String decodificarBase64(String textoCodificado){
        //Base64.decode não retorna uma string
        return new String (Base64.decode(textoCodificado, Base64.DEFAULT));

    }

}
