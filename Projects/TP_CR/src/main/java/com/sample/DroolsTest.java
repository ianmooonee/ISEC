package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DroolsTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");
        	
        	//divisões
        	Divisao d1 = new Divisao ("laboratorio", 3);
        	Divisao d2 = new Divisao ("escritório", 3);
        	Divisao d3 = new Divisao ("sala de reuniões", 1);
        	Divisao d4 = new Divisao ("gabinete", 1);
        	kSession.insert( d1 );
        	kSession.insert( d2 );
        	kSession.insert( d3 );
        	kSession.insert( d4 );
        	
        	//pessoas - não autorizadas
        	Pessoa p1 = new Pessoa ("Pedro", 123, -1, d1, "Porta");
        	Pessoa p2 = new Pessoa ("Mike", 124, -1, d2, "Janela Traseiras");
        	Pessoa p9 = new Pessoa ("John", 124, -1, d4, "Interior");
			
			//pessoas - funcionário
        	Pessoa p3 = new Pessoa ("Serrano", 125, 1, d1, "Porta"); //sem acesso, cartão válido
			Pessoa p4 = new Pessoa ("Maria", 126, 2, d2, "Porta");	//sem acesso, cartão expirado
        	Pessoa p5 = new Pessoa ("Joana", 127, 2, d2, "Porta"); //sem acesso, cartão válido
        	Pessoa p6 = new Pessoa ("Tammy", 128, 2, d2, "Porta");	//sem acesso, cartão válido
        	Pessoa p7 = new Pessoa ("Mariana", 129, 3, d3, "Porta"); //com acesso, cartão expirado
        	Pessoa p8 = new Pessoa ("Taremi", 130, 3, d3, "Porta"); //com acesso, cartão válido
        	Pessoa p10 = new Pessoa ("Mehdi", 131, 3, d4, "Porta"); //com acesso, cartão válido
        	kSession.insert( p1 );
        	kSession.insert( p2 );
        	kSession.insert( p3 );
        	kSession.insert( p4 );
        	kSession.insert( p5 );
        	kSession.insert( p6 );
        	kSession.insert( p7 );
        	kSession.insert( p8 );
        	kSession.insert( p9 );
        	kSession.insert( p10 );
        	
        	//cartões
        	Cartao ca1 = new Cartao(true, p3);
        	Cartao ca2 = new Cartao(false, p4);
        	Cartao ca3 = new Cartao(true, p5);
        	Cartao ca4 = new Cartao(true, p6);
        	Cartao ca5 = new Cartao(false, p7);
        	Cartao ca6 = new Cartao(true, p8);
        	Cartao ca7 = new Cartao(true, p10);
        	kSession.insert( ca1 );
        	kSession.insert( ca2 );
        	kSession.insert( ca3 );
        	kSession.insert( ca4 );
        	kSession.insert( ca5 );
        	kSession.insert( ca6 );
        	kSession.insert( ca7 );

        	//falso intruso
        	Pessoa i1 = new Pessoa("Gato", -1, -1, d1, "Janela Traseiras");
        	kSession.insert( i1 );
        	
        	//sensores
        	SensorVolumetrico s1 = new SensorVolumetrico(d1,true, false);
        	SensorVolumetrico s2 = new SensorVolumetrico(d2,true, false);
        	SensorVolumetrico s3 = new SensorVolumetrico(d3,true, false);
        	SensorVolumetrico s4 = new SensorVolumetrico(d4,true, true);
        	kSession.insert( s1 );
        	kSession.insert( s2 );
        	kSession.insert( s3 );
        	kSession.insert( s4 );
        	
        	//camaras
            Camara c1 = new Camara(d1, 1, "Porta", true);
			Camara c2 = new Camara(d1, 2, "Janela Traseiras", false);
            Camara c3 = new Camara(d2, 3, "Porta", true);
            Camara c4 = new Camara(d2, 4, "Janela Traseiras", true);
            Camara c5 = new Camara(d3, 5, "Porta", true);
            Camara c6= new Camara(d3, 6, "Janela Traseiras", true);
            Camara c7= new Camara(d4, 7, "Porta", true);
            Camara c8= new Camara(d4, 8, "Janela Traseiras", true);
            kSession.insert( c1 );
        	kSession.insert( c2 );
        	kSession.insert( c3 );
        	kSession.insert( c4 );
        	kSession.insert( c5 );
			kSession.insert( c6 );
			kSession.insert( c7 );
			kSession.insert( c8 );
           
            kSession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
