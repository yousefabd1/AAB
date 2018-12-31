package com.soshians_co.aab;

/**
 * Created by yousef on 5/10/2016.
 */
public class BABDbInterface {

    //private variables
    //int _idd;
    //aircooler

    String _login;
    String _username;
    String _switch;


    // Empty constructor
    public BABDbInterface(){

    }
    // aircoolernine constructor
    public BABDbInterface(String login, String username){
        this._login = login;
        this._username = username;

    }

    public BABDbInterface(String switch2){
        this._switch = switch2;

    }



    public String getLogin(){
        return this._login;
    }

    // setting id
    public void setLogin(String login){
        this._login = login;
    }

    public String getUsername(){
        return this._username;
    }

    // setting id
    public void setUsername(String username){
        this._username = username;
    }


    public String getSwitch(){
        return this._switch;
    }

    // setting id
    public void setSwitch(String s){
        this._switch = s;
    }

}
