package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrbiterFileRepository {
    private final String filePath;

    public OrbiterFileRepository(String filePath){
        this.filePath=filePath;
    }

    public List<Orbiter> findAll(){
        ArrayList<Orbiter> result=new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader(filePath))){

//            skip header
            reader.readLine();
            for(String line=reader.readLine(); line!=null; line=reader.readLine()){
                String[] fields=line.split(",",-1);
                if(fields.length==4){
                    Orbiter orbiter = new Orbiter();
                    orbiter.setOrbiterId(Integer.parseInt(fields[0]));
                    orbiter.setName(fields[1]);
                    orbiter.setType(OrbiterType.valueOf(fields[2]));
                    orbiter.setSponsor(fields[3]);
                    result.add(orbiter);
                }
            }
        }catch(IOException ex){
//do nothing?
        }
        return result;
    }


}
