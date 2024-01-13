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
        MenuOption option;

        do {
            option=view.displayMenuAndSelect();
            System.out.println(option.getTitle());
            switch (option){
                case EXIT:
                    break;
                case DISPLAY_ORBITERS:
                    break;
                case CREATE_ORBITERS:
                    break;
                case UPDATE_ORBITERS:
                    break;
                case DELETE_ORBITERS:
                    break;
            }
        }while(option!=MenuOption.EXIT);
    }
}
