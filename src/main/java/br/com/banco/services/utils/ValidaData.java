package br.com.banco.services.utils;


import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidaData {

    public Boolean verificaPeriodo(Timestamp dataInicio, Timestamp dataFim, Timestamp dataObj){

        if (dataObj.after(dataInicio) && dataObj.before(dataFim)){
            return true;
        }else{
            return false;
        }
    }

    public Timestamp formataData(String data){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Timestamp dataFormatada = new Timestamp(dateFormat.parse(data).getTime());
            return dataFormatada;
        } catch (ParseException e) {
            return null;
        }
    }
}
