package learn.venus.domain;

import learn.venus.models.Orbiter;

public class OrbiterService {
//    add
//    cant be null; name required; modules can hold 4 astronaut
//    module with docker==4 astronaut, 1 shuttle

    public OrbiterResult add(Orbiter orbiter){
        OrbiterResult result=validateInputs(orbiter);
        if(!result.isSuccess()){
            return result;
        }
        return null;
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
}
