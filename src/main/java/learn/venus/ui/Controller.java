package learn.venus.ui;

import learn.venus.domain.OrbiterService;

public class Controller {

    private final OrbiterService service;
    private final View view;

    public Controller(OrbiterService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run(){
        System.out.println("works");
    }
}
