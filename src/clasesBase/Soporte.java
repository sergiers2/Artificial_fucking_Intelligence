package clasesBase;

import java.util.ArrayList;

/**
 * Created by sergiers on 3/3/16.
 */
public class Soporte implements Cloneable{
    ////////////
    //atributes
    ///////////
    public int idDeSoporte = 0;
    public ArrayList<Integer> torreDeDiscos = new ArrayList<>();

    //////////////
    //constructors
    //////////////
    public Soporte(int num, int id){
        for(int i=num; i>0; i--){
         torreDeDiscos.add(i);
        }
        idDeSoporte = id;
    }


    //////////
    //methods
    //////////
    public String toString(){
        return "Soporte contiene la torre con discos: " + torreDeDiscos.toString();
    }

    public boolean equals(Soporte compare) {
       return this.torreDeDiscos.equals(compare.torreDeDiscos);
    }

    public int getTamUltimoDisco(){
        if(torreDeDiscos.size()!=0) {
            return torreDeDiscos.get(torreDeDiscos.size() - 1);
        }else{
            return 0;
        }
    }

    public Object clone() {
        Soporte soporteClon = null;

        try {
            soporteClon = (Soporte) super.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("IMPOSILE CLONAR SOPORTE");
        }

        ArrayList<Integer> cloneTorreDeDiscos = new ArrayList<>(this.torreDeDiscos.size());


        for(Integer i: torreDeDiscos){
            cloneTorreDeDiscos.add(i);
        }
        soporteClon.torreDeDiscos = cloneTorreDeDiscos;
        return soporteClon;
    }

}
