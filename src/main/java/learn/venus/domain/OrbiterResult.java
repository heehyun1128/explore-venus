package learn.venus.domain;

import learn.venus.models.Orbiter;

import java.util.ArrayList;
import java.util.List;

public class OrbiterResult  {
    private ArrayList<String> messages=new ArrayList<>();
    private Orbiter payload;

    public void addErrorMessage(String message){
        messages.add(message);
    }

    public boolean isSuccess(){
        return messages.size()==0;
    }

    public Orbiter getPayload(){
        return payload;
    }

    public void setPayload(Orbiter payload){
        this.payload=payload;
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages); //create a copy of the original list, preventing external code from modifying the internal list directly.
//        The purpose of returning a copy is often to provide a read-only view of the error messages.
//        This helps maintain encapsulation by not exposing the internal state of the class directly.
//        External code can access the error messages without being able to modify the original list.
    }
}
