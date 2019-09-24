package edu.escuelaing.arep.app;

import java.io.*;
import java.net.*;

public class Controller {
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 500; i++){
            URL url = new URL("https://proyecto-arep1.herokuapp.com/static/index.html");
            conect(url);
        }

    }

    private static void conect(URL url){
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
