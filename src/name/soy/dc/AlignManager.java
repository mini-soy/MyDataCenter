package name.soy.dc;

import name.soy.dc.annotation.Manager;
import name.soy.dc.tasks.Aligns;

import java.util.HashMap;

@Manager
public class AlignManager {
    HashMap<String, Aligns> aligns=new HashMap<>();

    public Aligns<Integer> getIntAlign(String v){
        Aligns a = aligns.get(v);
        if(a.getTypename().equals("int")){
            return a;
        }
        return null;
    }
    public Aligns<Double> getDoubleAlign(String v){
        Aligns a = aligns.get(v);
        if(a.getTypename().equals("int")){
            return a;
        }
        return null;
    }
    public Aligns<String> getStringAlign(String v){
        Aligns a = aligns.get(v);
        if(a.getTypename().equals("string")){
            return a;
        }
        return null;
    }
}
