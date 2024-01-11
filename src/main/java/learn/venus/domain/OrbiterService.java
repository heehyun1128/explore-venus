package learn.venus.domain;

import learn.venus.data.DataAccessException;
import learn.venus.data.OrbiterRepository;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbiterService {

    private final OrbiterRepository repository;

    public OrbiterService(OrbiterRepository repository){
        this.repository=repository;
    }


    public OrbiterResult add(Orbiter orbiter) throws DataAccessException {
        OrbiterResult result=validateInputs(orbiter);
        if(!result.isSuccess()){
            return result;
        }

        Map<OrbiterType,Integer> counts = countTypes();
        counts.put(orbiter.getType(),counts.get(orbiter.getType())+1);
        result = validateDomain(counts);
        if(!result.isSuccess()){
            return result;
        }

        Orbiter o= repository.add(orbiter);
        result.setPayload(o);

        return null;
    }

    public OrbiterResult update(Orbiter orbiter) throws DataAccessException{
        OrbiterResult result = validateInputs(orbiter);
        if(!result.isSuccess()){
            return result;
        }
//        cannot update orbiter type
        Orbiter existing=repository.findById(orbiter.getOrbiterId());
        if(existing==null){
            result.addErrorMessage("Orbiter Id " +orbiter.getOrbiterId() + "not found.");
        }

        if(existing.getType()!=orbiter.getType()){
            result.addErrorMessage("cannot update type.");
            return result;
        }

        boolean success = repository.update(orbiter);
        if(!success){
            result.addErrorMessage("Could not find Orbiter Id: " + orbiter.getOrbiterId());
        }

        return result;
    }

    public OrbiterResult deleteById(int orbiterId) throws DataAccessException{
        return null;
    }

    private Map<OrbiterType,Integer> countTypes(){
        HashMap<OrbiterType,Integer> counts = new HashMap<>();
        counts.put(OrbiterType.MODULE,0);
        counts.put(OrbiterType.MODULE_WITH_DOCK,0);
        counts.put(OrbiterType.ASTRONAUT,0);
        counts.put(OrbiterType.SHUTTLE,0);
        counts.put(OrbiterType.VENUSIAN,0);

        try{
            List<Orbiter> allOrbiters = repository.findAll();
            for(Orbiter o:allOrbiters){
                switch (o.getType()){
                    case MODULE:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.MODULE)+1);
                        break;
                    case MODULE_WITH_DOCK:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.MODULE_WITH_DOCK)+1);
                        break;
                    case ASTRONAUT:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.ASTRONAUT)+1);
                        break;
                    case SHUTTLE:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.SHUTTLE)+1);
                        break;
                    case VENUSIAN:
                        counts.put(OrbiterType.MODULE, counts.get(OrbiterType.VENUSIAN)+1);
                        break;
                }
            }
        }catch(DataAccessException ex){

        }
        return counts;

    }

    private OrbiterResult validateInputs(Orbiter orbiter){
        OrbiterResult result=new OrbiterResult();
        if(orbiter==null){
            result.addErrorMessage("Orbiter cannot be null");
            return result;
        }

        if(orbiter.getName()==null || orbiter.getName().trim().length()==0){
            result.addErrorMessage("Name is required.");
        }
        return result;
    }

    private OrbiterResult validateDomain(Map<OrbiterType,Integer> counts) {
        int astroCount=counts.get(OrbiterType.ASTRONAUT);
        int shuttleCount=counts.get(OrbiterType.SHUTTLE);
        int modCount=counts.get(OrbiterType.MODULE);
        int dockCount=counts.get(OrbiterType.MODULE_WITH_DOCK);


        OrbiterResult result = new OrbiterResult();

        if(astroCount>modCount*4 + dockCount*2){
                    result.addErrorMessage("No room for an astronaut.");
                }

        if(astroCount>dockCount){
                    result.addErrorMessage("No room for an astronaut.");
                }

        return result;
    }
}
