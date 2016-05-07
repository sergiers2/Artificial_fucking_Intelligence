package logica;

import clasesBase.Enviroment;
import clasesBase.Soporte;
import es.deusto.ingenieria.is.search.formulation.Operator;
import es.deusto.ingenieria.is.search.formulation.State;

/**
 * Created by sergiers on 5/3/16.
 */

public class Move extends Operator {

    public int soporteInicial;
    public int soporteDestino;

    public Move(int soporteInicial, int soporteDestino){
        this.soporteInicial = soporteInicial;
        this.soporteDestino = soporteDestino;
    }

    @Override
    public boolean isApplicable(State state) {
        Enviroment currentState = (Enviroment) state;


        Soporte inicial = currentState.soportes.get(soporteInicial);
        Soporte fin = currentState.soportes.get(soporteDestino);

        int tamUltimoDiscoInicial = inicial.getTamUltimoDisco();
        int tamUltimoDiscofin = fin.getTamUltimoDisco();


        //si el soporte inicial contiene disos
        return tamUltimoDiscoInicial != 0 && (tamUltimoDiscofin > tamUltimoDiscoInicial || tamUltimoDiscofin == 0);
    }

    @Override
    public State effect(State state) {
        if(isApplicable(state)) {
            //si es aplicable, movemos

            Enviroment newEnv = (Enviroment) ((Enviroment) state).clone();


            //obten los soportes
            Soporte inicial = newEnv.soportes.get(soporteInicial);
            Soporte fin = newEnv.soportes.get(soporteDestino);

            //añade el disco al soporte destino
            fin.torreDeDiscos.add(inicial.torreDeDiscos.get(inicial.torreDeDiscos.size()-1));

            //quita el disco del soporte origen
            inicial.torreDeDiscos.remove(inicial.torreDeDiscos.size()-1);

            newEnv.soportes.set(soporteInicial, inicial);
            newEnv.soportes.set(soporteDestino, fin);

            this.setName("De soporte '" + soporteInicial + "' al soporte '" + soporteDestino+"'");

            return newEnv;

        }else{
            //si no es aplicable, devolvemos el original
            System.out.println("NO APLICABLE");
            return state;
        }
    }
}
