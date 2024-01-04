package learn.venus.data;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrbiterFileRepository {
    private final String filePath;

    public OrbiterFileRepository(String filePath){
        this.filePath=filePath;
    }

    public List<Orbiter> findAll() throws DataAccessException {
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
        }catch(FileNotFoundException ex){
//            okay to ignore
        }catch(IOException ex){
            throw new DataAccessException(ex.getMessage(),ex);
        }
        return result;
    }

    public Orbiter findById(int orbiterId) throws DataAccessException {
        for(Orbiter orbiter: findAll()){
            if(orbiter.getOrbiterId()==orbiterId){
                return orbiter;
            }
        }
        return null;
    }

    public List<Orbiter> findByType(OrbiterType type) throws DataAccessException {
        ArrayList<Orbiter> res=new ArrayList<>();
        for(Orbiter orbiter:findAll()){
            if(orbiter.getType()==type){
                res.add(orbiter);
            }
        }
        return res;
    }

//    add
    public Orbiter add(Orbiter orbiter) throws DataAccessException {
        List<Orbiter> allOrbiters=findAll();
        int nextId=0;
        for(Orbiter o:allOrbiters){
            nextId=Math.max(nextId,o.getOrbiterId());
        }
        nextId++;
        orbiter.setOrbiterId(nextId);

        allOrbiters.add(orbiter);

        writeAll(allOrbiters);
        return orbiter;
    }

    public boolean update(Orbiter orbiter) throws DataAccessException {
        List<Orbiter> all=findAll();
        for(int i=0;i<all.size();i++){
            if(all.get(i).getOrbiterId()==orbiter.getOrbiterId()){
                all.set(i,orbiter);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    public boolean deleteById(int orbiterId) throws DataAccessException {
        List<Orbiter> all=findAll();
        for(int i=0;i<all.size();i++){
            if(all.get(i).getOrbiterId()==orbiterId){
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    private void writeAll(List<Orbiter> orbiters) throws DataAccessException {
        try(PrintWriter writer=new PrintWriter(filePath)){
            writer.println("orbiterId, name, type, sponsor");
            for(Orbiter orbiter: orbiters){
                writer.println(serialize(orbiter));
            }
        }catch(IOException ex){
            throw new DataAccessException(ex.getMessage(),ex);
        }
    }

    private String serialize(Orbiter orbiter){
        return String.format("%s,%s,%s,%s",
                orbiter.getOrbiterId(),
                orbiter.getName(),
                orbiter.getType(),
                orbiter.getSponsor()

                );
    }
}
