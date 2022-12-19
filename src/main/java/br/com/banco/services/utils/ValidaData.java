package br.com.banco.services.utils;


import java.sql.Timestamp;
import java.util.Date;

public class ValidaData {

    public Boolean verificaPeriodo(Timestamp dataInicio, Timestamp dataFim, Timestamp dataObj){

        if (dataObj.after(dataInicio) && dataObj.before(dataFim)){
            return true;
        }else{
            return false;
        }
    }
}
