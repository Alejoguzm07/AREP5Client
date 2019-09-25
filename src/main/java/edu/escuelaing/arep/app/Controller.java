package edu.escuelaing.arep.app;

import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {

	private static AtomicInteger fallos;

    public static void main(String[] args) throws Exception {
    	int cantidad = Integer.parseInt(args[0]);
    	fallos = new AtomicInteger();
    	ArrayList<URL> links = prepararUrls(cantidad);
    	double ini = System.currentTimeMillis();
    	//atacarNoConcurrentemente(links);
    	atacarConcurrentemente(links);
		double fin = System.currentTimeMillis();
		System.err.println("Con " + cantidad + " peticiones el servidor tardo " + (fin - ini) + " milisegundos");
		System.err.println("La cantidad de peticiones no exitosas fue: " + fallos.get());
    }
    
    private static void atacarNoConcurrentemente(ArrayList<URL> urls) throws InterruptedException {
    	ArrayList<RequestThread> peticiones = new ArrayList<RequestThread>();
    	for(int i = 0; i < urls.size(); i++) {
    		peticiones.add(new RequestThread(urls.get(i),fallos));
    	}
		for(int i = 0; i < peticiones.size(); i++) {
			peticiones.get(i).run();
		}
    }
    
    private static void atacarConcurrentemente(ArrayList<URL> urls) throws InterruptedException {
    	ArrayList<RequestThread> peticiones = new ArrayList<RequestThread>();
    	for(int i = 0; i < urls.size(); i++) {
    		peticiones.add(new RequestThread(urls.get(i),fallos));
    	}
    	for(int i = 0; i < peticiones.size(); i++) {
    		peticiones.get(i).start();
    	}
		for(int i = 0; i < peticiones.size(); i++) {
			peticiones.get(i).join();
		}
    }
    
    private static ArrayList<URL> prepararUrls(int num) throws MalformedURLException {
    	Random n = new Random();
		ArrayList<URL> links = new ArrayList<URL>();
		ArrayList<URL> peticiones = new ArrayList<URL>();
		links.add(new URL("https://proyecto-arep1.herokuapp.com/static/index.html"));
		//links.add(new URL("https://proyecto-arep1.herokuapp.com/static/CalculadoraWeb.html"));
		//links.add(new URL("https://proyecto-arep1.herokuapp.com/static/paisaje.jpg"));
		//links.add(new URL("https://proyecto-arep1.herokuapp.com/apps/Division?9,3"));
		//links.add(new URL("https://proyecto-arep1.herokuapp.com/apps/Cuadrado?5"));
		//links.add(new URL("https://proyecto-arep1.herokuapp.com/static/CalculadoraWeb.htl"));
		for(int i = 0; i < num; i++) {
			peticiones.add(links.get(Math.abs(n.nextInt()) % links.size()));
		}
		return peticiones;
    }
}
