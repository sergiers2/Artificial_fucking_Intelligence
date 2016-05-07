package main;

import clasesBase.Enviroment;
import es.deusto.ingenieria.is.search.algorithms.blind.DepthFS;
import es.deusto.ingenieria.is.search.algorithms.heuristic.BestFS;
import logica.LogicProblem;
import logica.Move;
import logica.LeftAndMidDisksEvaluationFunction;

/**
 * Created by sergiers on 3/3/16.
 */
public class Main {
    static int numeroDiscos = 4;
    static int numeroSoportes = 3;

    public static void main(String[] args){

              //  System.out.println("TEST 1: Probando FinalState");
        //  comprobarIsFinalState();
        //  System.out.println("-------------------------");
        //  System.out.println("TEST 2: Probando IsApplicable");
        //  comprobarIsApplicable();
        //  System.out.println("-------------------------");
         // System.out.println("TEST 3: Probando Método Clone()");
          //comprobarClone();



         //System.out.println("-------------------------");
         // System.out.println("TEST 4: Probando Método solve()");
         // comprobarSolveDFS();
         // System.out.println("-------------------------");
         // comprobarSolveBFS();

         System.out.println("TEST 5: Probando ultimos metodos");
        Enviroment e = new Enviroment(numeroDiscos, numeroSoportes);
        LogicProblem lg = new LogicProblem(numeroDiscos, numeroSoportes);

        lg.addInitialState(lg.gatherInitialPercepts());
        lg.solve(new BestFS(new LeftAndMidDisksEvaluationFunction()));



    }

    public static void comprobarSolveDFS(){
        Enviroment e = new Enviroment(numeroDiscos, numeroSoportes);
        LogicProblem lg = new LogicProblem(numeroDiscos, numeroSoportes);

        lg.addInitialState(lg.gatherInitialPercepts());
        //lg.addInitialState(e);

        lg.solve(DepthFS.getInstance());

        //lg.solve(new BestFS(new AttacksEvaluationFunction()));

    }

    public static void comprobarSolveBFS(){
        Enviroment e = new Enviroment(numeroDiscos, numeroSoportes);
        LogicProblem lg = new LogicProblem(numeroDiscos, numeroSoportes);

        lg.addInitialState(lg.gatherInitialPercepts());
        //lg.addInitialState(e);

        lg.solve(new BestFS(new LeftAndMidDisksEvaluationFunction()));

        //lg.solve(new BestFS(new AttacksEvaluationFunction()));

    }

    public static void comprobarClone(){
        Enviroment env = new Enviroment(numeroDiscos, numeroSoportes);
        Enviroment envCopia = (Enviroment) env.clone();

        System.out.println("==================");
        System.out.print("ORIGINAL ENV: ");
        env.toString();
        System.out.print("COPIA DE ENV: ");
        envCopia.toString();
        System.out.println("==================");


        System.out.println(" ");

        //modifico uno de los valores
        System.out.println("modifico muevo en ENV del soporte 1 al soporte 2");
        Move muevo = new Move(1, 2);

        env = (Enviroment) muevo.effect(env);

        System.out.println(" ");

        System.out.println("==================");
        System.out.print("ORIGINAL ENV: ");
        env.toString();
        System.out.print("COPIA DE ENV: ");
        envCopia.toString();
        System.out.println("==================");
        System.out.println(" ");

        System.out.println("¿Funciona el método clone()?: "+ !env.equals(envCopia));
    }

    public static void comprobarIsApplicable(){

        Enviroment e = new Enviroment(numeroDiscos, numeroSoportes);

        System.out.println("Estado del primer soporte: ");
        System.out.println(e.soportes.get(1));
        System.out.println("Estado de segundo soporte: ");
        System.out.println(e.soportes.get(2));

        System.out.println(" ");
        System.out.println("Ejecutamos un movimiento del soporte inicial al soporte dos");

        Move mover = new Move(e.soportes.get(1).idDeSoporte, e.soportes.get(2).idDeSoporte);
        e = (Enviroment)mover.effect(e);

        System.out.println(" ");
        System.out.println("Estado del primer soporte: ");
        System.out.println(e.soportes.get(1));
        System.out.println("Estado de segundo soporte: ");
        System.out.println(e.soportes.get(2));

        System.out.println(" ");
        System.out.println("Si se ha ejecutado el movimiento, es correcto");

        System.out.println(" ");
        System.out.println("Ejecutamos un movimiento del soporte inicial al soporte dos");

        mover = new Move(e.soportes.get(1).idDeSoporte, e.soportes.get(2).idDeSoporte);
        e = (Enviroment)mover.effect(e);

        System.out.println(" ");
        System.out.println("Estado del primer soporte: ");
        System.out.println(e.soportes.get(1));
        System.out.println("Estado de segundo soporte: ");
        System.out.println(e.soportes.get(2));

        System.out.println(" ");
        System.out.println("Si NO se ha ejecutado el movimiento, es correcto");

        System.out.println(" ");
        e.toString();
        System.out.println(" ");

    }

    public static void comprobarIsFinalState(){
        //inicializamos
        System.out.println("Programa inicializado");
        Enviroment e = new Enviroment(numeroDiscos, numeroSoportes);

        LogicProblem lg = new LogicProblem(numeroDiscos, numeroSoportes);
        System.out.println("¿Es estado final?: "+ lg.isFinalState(e));

        //ponemos el resultado en el ultimo soporte
        e.ponResultadoEnUltimoSoporte();
        System.out.println("Trucamos el ultimo soporte para que tenga el resultado");

        System.out.println("¿Es estado final?: "+lg.isFinalState(e));
    }
}
