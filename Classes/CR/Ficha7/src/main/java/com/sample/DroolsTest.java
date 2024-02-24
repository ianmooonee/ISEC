package com.sample;

import org.kie.api.KieServices;
import java.util.Scanner;
import java.io.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample class to launch a rule.
 */
public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	Scanner input=new Scanner(new FileInputStream("fogo.txt"));
        	String linha=input.nextLine();
        	
        	while(input.hasNextLine()) {
        		linha=input.nextLine();
        		String[] campos=linha.split(";");
        		
        		Divisao d=new Divisao(campos[0]);
        		kSession.insert(d);
        		
        		if(campos[1].equals("sim")) {
        			Aspersor asp=new Aspersor(d, false);
        			kSession.insert(asp);
        		}
        		if(campos[2].equals("sim")) {
        			Fogo f=new Fogo(d);
        			kSession.insert(f);
        		}
        	}
        	input.close();
            
        	/*Divisao d1 = new Divisao ("cozinha");
        	Divisao d2 = new Divisao ("escritório");
        	Divisao d3 = new Divisao ("sala");
        	kSession.insert( d1 );
        	kSession.insert( d2 );
        	kSession.insert( d3 );
        	
        	Aspersor asp1 = new Aspersor( d1, false ); //cozinha tem aspersor
        	Aspersor asp2 = new Aspersor( d2, false ); //escritório tem aspersor
        	Aspersor asp3 = new Aspersor( d3, false ); //sala tem aspersor
        	kSession.insert( asp1 );
        	kSession.insert( asp2 );
        	kSession.insert( asp3 );
        	
        	Fogo f1 = new Fogo (d1); //Fogo na cozinha
        	Fogo f2 = new Fogo (d2); //Fogo no escritório
        	Fogo f3 = new Fogo (d3); //Fogo na sala
        	kSession.insert( f1 );
        	kSession.insert( f2 );
        	kSession.insert( f3 );*/
        	
        	
        	
        	kSession.fireAllRules() ;
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
