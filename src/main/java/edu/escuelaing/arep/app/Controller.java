package edu.escuelaing.arep.app;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Controller {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("https://proyecto-arep1.herokuapp.com/static/index.html");
        URL url2 = new URL("https://proyecto-arep1.herokuapp.com/static/CalculadoraWeb.html");
        ArrayList<RequestThread> peticiones = new ArrayList<RequestThread>();
        for(int i = 0; i < 100; i ++){
            peticiones.add(new RequestThread(url1));
            peticiones.add(new RequestThread(url2));
        }
        for(int i = 0; i < peticiones.size(); i++){
            peticiones.get(i).start();
        }
    }
}
