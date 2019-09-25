package edu.escuelaing.arep.app;

import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    public static void main(String[] args) throws Exception {
    	int cantidad = 10;
    	ArrayList<URL> links = prepararUrls(cantidad);
    	//atacarNoConcurrentemente(links);
    	atacarConcurrentemente(links);
    }
    
    private static void atacarNoConcurrentemente(ArrayList<URL> urls) {
    	ArrayList<RequestThread> peticiones = new ArrayList<RequestThread>();
    	for(int i = 0; i < urls.size(); i++) {
    		peticiones.add(new RequestThread(urls.get(i)));
    	}
    	for(int i = 0; i < peticiones.size(); i++) {
    		peticiones.get(i).run();
    	}
    }
    
    private static void atacarConcurrentemente(ArrayList<URL> urls) {
    	ArrayList<RequestThread> peticiones = new ArrayList<RequestThread>();
    	for(int i = 0; i < urls.size(); i++) {
    		peticiones.add(new RequestThread(urls.get(i)));
    	}
    	for(int i = 0; i < peticiones.size(); i++) {
    		peticiones.get(i).start();
    	}
    }
    
    private static ArrayList<URL> prepararUrls(int num) throws MalformedURLException {
    	Random n = new Random();
		ArrayList<URL> links = new ArrayList<URL>();
		ArrayList<URL> peticiones = new ArrayList<URL>();
		links.add(new URL("https://proyecto-arep1.herokuapp.com/static/index.html"));links.add(new URL("https://proyecto-arep1.herokuapp.com/staticindex.html"));
		links.add(new URL("https://proyecto-arep1.herokuapp.com/static/CalculadoraWeb.html"));links.add(new URL("https://proyecto-arep1.herokuapp.com/static/CalculadoraWeb.htl"));
		links.add(new URL("https://proyecto-arep1.herokuapp.com/static/paisaje.jpg"));links.add(new URL("https://proyecto-arep1.herokuapp.com/static/paisaj.jpg"));
		links.add(new URL("https://proyecto-arep1.herokuapp.com/apps/Division?9,3"));links.add(new URL("https://proyecto-arep1.herokuapp.com/apps/Division?9"));
		links.add(new URL("https://proyecto-arep1.herokuapp.com/apps/Cuadrado?5"));links.add(new URL("https://proyecto-arep1.herokuapp.com/apps/Cuadrad?5"));
		for(int i = 0; i < num; i++) {
			peticiones.add(links.get(Math.abs(n.nextInt()) % links.size()));
		}
		return peticiones;
    }
}
