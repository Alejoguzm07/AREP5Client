package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class RequestThread extends Thread{
    URL url;

    public RequestThread (URL url){
    	System.err.println(url.toString());
        this.url = url;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (Exception x) {
            System.err.println(x);
        }
    }
}
