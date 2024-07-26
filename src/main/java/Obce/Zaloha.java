
package Obce;

import Struktury.AbstrDoubleList;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Zaloha {
    
    public static void ulozTxt(String jmenoSouboru, AbstrDoubleList seznam) throws FileNotFoundException, IOException{
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(jmenoSouboru, false));
            
            seznam.zpristupniPrvni();
            for(int i = 0; i < seznam.velikost(); i++){
                
                
                writer.write(seznam.zpristupniAktualni().toString());
                writer.newLine();
                if(i+1 < seznam.velikost()){
                    seznam.zpristupniNaslednika();
                }
                
                
                
            }
            writer.flush();
            writer.close();
        }finally{
            
        }
        
    }
    
}
