package br.com.team.appx.convinience.util;

import antlr.StringUtils;

import java.util.Collection;

public class MyExeptionUtils extends RuntimeException{

    private Collection<String> messages;

    public MyExeptionUtils(String msg){
        super(msg);
    }


    public MyExeptionUtils(String msg, Exception cause){
        super(msg, cause);
    }


    public MyExeptionUtils(Collection<String> messages){
        super();
        this.messages= messages;
    }


    public MyExeptionUtils(Collection<String> messages, Exception cause){
        super(cause);
        this.messages= messages;
    }

    @Override
    public String getMessage(){
        String msg;

        if(this.messages!=null && !this.messages.isEmpty()){
            msg="[";

            for(String message : this.messages){
                msg+=message+",";
            }



        }else msg= super.getMessage();

        return msg;
    }

}