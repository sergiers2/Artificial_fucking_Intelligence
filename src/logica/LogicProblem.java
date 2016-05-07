package logica;

import clasesBase.Enviroment;
import es.deusto.ingenieria.is.search.algorithms.Node;
import es.deusto.ingenieria.is.search.algorithms.SearchMethod;
import es.deusto.ingenieria.is.search.formulation.Problem;
import es.deusto.ingenieria.is.search.formulation.State;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by sergiers on 5/3/16.
 */
public class LogicProblem extends Problem {

    int numDiscos = 0;
    int numSoportes = 0;


    public LogicProblem(int numDiscos, int numSoportes){
        this.numDiscos = numDiscos;
        this.numSoportes = numSoportes;
        this.createFinalStates();
        this.createOperators();
    }

    private void createOperators() {
        //convinaciones de soportes: N^2-N
           for(int i=1; i<numSoportes+1; i++){
            for(int j=1; j<numSoportes+1; j++) {
                if(j!=i) {
                    this.addOperator(new Move(i, j));
                }
            }
        }

    }

    public boolean isFinalState(State s) {
        Enviroment tempEnviroment = (Enviroment)s;

        return (s != null) && (tempEnviroment.soportes.get(tempEnviroment.soportes.size() - 1).equals(tempEnviroment.soportes.get(0)));
    }
    private void createFinalStates() {
        Enviroment e = new Enviroment(numDiscos, numSoportes);

        e.ponResultadoEnUltimoSoporte();
        e.soportes.get(1).torreDeDiscos = new ArrayList<>(0);


        this.addFinalState(e);
    }

    public void solve(SearchMethod searchMethod) {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.S");
        Date beginDate = GregorianCalendar.getInstance().getTime();
        System.out.println("\n* Start '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(beginDate) + ")");

        /////
        Node finalNode = searchMethod.search(this, this.getInitialStates().get(0));

        Date endDate = GregorianCalendar.getInstance().getTime();
        System.out.println("* End   '" + searchMethod.getClass().getSimpleName() + "' (" + formatter.format(endDate) + ")");

        long miliseconds = (int) Math.abs(beginDate.getTime() - endDate.getTime());
        long seconds = miliseconds / 1000;
        miliseconds %= 1000;
        long minutes = seconds / 60;
        seconds %= 60;
        long hours = minutes / 60;
        minutes %= 60;

        String time = "* Serach lasts: ";
        time += (hours > 0) ? hours + " h " : " ";
        time += (minutes > 0) ? minutes + " m " : " ";
        time += (seconds > 0) ? seconds + "s " : " ";
        time += (miliseconds > 0) ? miliseconds + "ms " : " ";

        System.out.println(time);

        if (finalNode != null) {
            System.out.println("\n- Solution found!     :)");
            List<String> operators = new ArrayList<>();
            searchMethod.solutionPath(finalNode, operators);
            searchMethod.createSolutionLog(operators);
            System.out.println("- Final state:" + finalNode.getState());
        } else {
            System.out.println("\n- Unable to find the solution!     :(");
        }

    }

    @Override
    public State gatherInitialPercepts() {
        return new Enviroment(numDiscos, numSoportes);
    }


}
