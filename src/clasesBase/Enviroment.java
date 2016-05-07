package clasesBase;

import es.deusto.ingenieria.is.search.formulation.State;

import java.util.ArrayList;

/**
 * Created by sergiers on 4/3/16.
 */
public class Enviroment extends State implements Cloneable{

    //el soporte 0 es el resultado
    public ArrayList<Soporte> soportes;

    public Enviroment(int numeroDeDiscos, int numeroDeSoportes){
        if(numeroDeSoportes>2) {
            //comprobar si hay mas de 2 soportes

            soportes = new ArrayList<>(numeroDeSoportes + 1);

            //para el soporte 0 (resultado)
            Soporte s = new Soporte(numeroDeDiscos, 0);
            soportes.add(s);

            //para el soporte 1 (contiene los discos)
            s = new Soporte(numeroDeDiscos, 1);
            soportes.add(s);

            for (int i = 2; i < numeroDeSoportes+1; i++) {
                s = new Soporte(0, i);
                soportes.add(s);
            }
        }else{
            System.err.println("Demasiados pocos soportes");
        }
    }


    @Override
    public String toString() {
        String s = "";

        if(soportes!=null) {
            for (int i = 0; i < soportes.size(); i++) {
                s = s + "soporte numero: " + i + " -> " + soportes.get(i) + " ";
            }
            return s;
        }else {
            return "null";
        }
    }

    @Override
    public boolean equals(Object obj) {

        Boolean eq = true;
        Enviroment e = (Enviroment) obj;
        if (obj != null && e.soportes.size() == this.soportes.size()) {
            for(int i=0; i<this.soportes.size(); i++){
                if(!this.soportes.get(i).torreDeDiscos.equals(e.soportes.get(i).torreDeDiscos)){
                    eq = false;
                }

            }
            return eq;
        } else {
            return false;
        }
    }

    @Override
    public Object clone() {
        Enviroment env = null;
        ArrayList<Soporte> cloneSoportes = new ArrayList<>(this.soportes.size());
        try {
        env = (Enviroment) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("IMPOSIBLE CLONAR ENVIROMENT: ");
            e.printStackTrace();
        }

        for (Soporte s : this.soportes) {
            cloneSoportes.add((Soporte) s.clone());
        }
        env.soportes = cloneSoportes;
        return env;
    }


    public void ponResultadoEnUltimoSoporte(){
        soportes.remove(soportes.size()-1);
        soportes.add(soportes.get(0));
    }





}
