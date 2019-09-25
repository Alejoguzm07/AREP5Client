package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestThread extends Thread{
    URL url;
    AtomicInteger a;

    public RequestThread (URL url, AtomicInteger a){
    	System.err.println(url.toString());
        this.url = url;
        this.a = a;
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
            a.getAndIncrement();
            System.err.println(x);
        }
    }
}
