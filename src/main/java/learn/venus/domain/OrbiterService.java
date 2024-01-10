package learn.venus.domain;

import learn.venus.data.DataAccessException;
import learn.venus.data.OrbiterRepository;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.util.List;

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

        result = validateDomain(orbiter);
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

    private OrbiterResult validateDomain(Orbiter orbiter) throws DataAccessException {
        OrbiterResult result = new OrbiterResult();
        List<Orbiter> allOrbiters=repository.findAll();

        if(orbiter.getType()==OrbiterType.ASTRONAUT || orbiter.getType()==OrbiterType.SHUTTLE){
            int astroCount=0;
            int shuttleCount=0;
            int modCount=0;
            int dockCount=0;

            for(Orbiter o:allOrbiters){
                switch(o.getType()){
                    case MODULE:
                        modCount++;
                        break;
                    case MODULE_WITH_DOCK:
                        dockCount++;
                        break;
                    case ASTRONAUT:
                        astroCount++;
                        break;
                    case SHUTTLE:
                        shuttleCount++;
                        break;
                }
            }
            if(orbiter.getType()==OrbiterType.ASTRONAUT){
                if(astroCount+1>modCount*4 + dockCount*2){
                    result.addErrorMessage("No room for an astronaut.");
                }
            }

            if(orbiter.getType()==OrbiterType.SHUTTLE){
                if(astroCount+1>dockCount){
                    result.addErrorMessage("No room for an astronaut.");
                }
            }
        }

        return result;
    }
}
