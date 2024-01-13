package learn.venus.ui;

import learn.venus.data.DataAccessException;
import learn.venus.domain.OrbiterService;
import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.util.List;

public class Controller {

    private final OrbiterService service;
    private final View view;

    public Controller(OrbiterService service, View view) {
        this.service = service;
        this.view = view;
    }


    public void run(){
        try{
            runMenu();
        }catch(DataAccessException ex){
            view.printHeader("Fatal err: "+ex);
        }
    }
    private void runMenu() throws DataAccessException {
        MenuOption option;

        do {
            option=view.displayMenuAndSelect();

            switch (option){
                case EXIT:
                    view.printHeader("Bye");
                    break;
                case DISPLAY_ORBITERS:
                    displayOrbiters();
                    break;
                case CREATE_ORBITERS:
                    createOrbiters();
                    break;
                case UPDATE_ORBITERS:
                    updateOrbiters();
                    break;
                case DELETE_ORBITERS:
                    deleteOrbiters();
                    break;
            }
        }while(option!=MenuOption.EXIT);
    }

    private void displayOrbiters() throws DataAccessException {
        view.printHeader(MenuOption.DISPLAY_ORBITERS.getTitle());
        OrbiterType type=view.readOrbiterType();
        List<Orbiter> orbiters=service.findByType(type);
    }

    private void createOrbiters() {
        view.printHeader(MenuOption.CREATE_ORBITERS.getTitle());
    }

    private void updateOrbiters() {
        view.printHeader(MenuOption.UPDATE_ORBITERS.getTitle());
    }

    private void deleteOrbiters() {
        view.printHeader(MenuOption.DELETE_ORBITERS.getTitle());
    }
}

