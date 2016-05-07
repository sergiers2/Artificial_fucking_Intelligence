package logica;

import clasesBase.Enviroment;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.heuristic.EvaluationFunction;

/**
 * Created by sergiers on 15/3/16.
 */
public class IzqDisksEvaluationFunction extends EvaluationFunction{
    @Override
    public double calculateG(Node node) {
        return 0;
    }

    //soportes en la izquierda
    @Override
    public double calculateH(Node node) {
        Enviroment e = (Enviroment) node.getState();
        return e.soportes.get(1).torreDeDiscos.size();
    }

}
