package learn.venus.domain;

import learn.venus.models.Orbiter;

import java.util.ArrayList;

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
}
