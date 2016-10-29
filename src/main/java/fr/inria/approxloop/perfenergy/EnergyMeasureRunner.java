package fr.inria.approxloop.perfenergy;//package fr.inria.measurenergy;


public class EnergyMeasureRunner {

	public void runJsyn() {
		try {
			for ( int i = 0; i < 100; i++ ) {
                System.out.println(Math.sin(i));
            }
		} catch(Exception e) {
		}
	}

}
