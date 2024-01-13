package learn.venus.ui;

import learn.venus.models.Orbiter;
import learn.venus.models.OrbiterType;

import java.util.Scanner;

public class View {

    private final Scanner console=new Scanner(System.in);

    public MenuOption displayMenuAndSelect(){
        MenuOption[] values=MenuOption.values();
        printHeader("Main Menu");
        for(int i=0;i<values.length;i++){
            System.out.printf("%s. %s%n",i,values[i].getTitle());
        }
        int index=readInt("Select [0-4: ", 0, 4);
        return values[index];
    }

    public void printHeader(String message){
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public OrbiterType readOrbiterType(){
        System.out.println("Types: ");
        OrbiterType[] values=OrbiterType.values();
        for(int i=0;i<values.length;i++){
            System.out.printf("%s. %s%n", i, values[i]);
        }
        int index=readInt("Select [0-4]: ", 0,4);
        return values[index];
    }

    private String readString(String prompt){
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt){
        String result=null;
        do{
            result=readString(prompt).trim();
            if(result.length()==0){
                System.out.println("Value is required!");
            }
        }while(result.length()==0);
        return result;
    }

    private int readInt(String prompt){
        int result=0;
        boolean isValid=false;

        do {
            String value=readRequiredString(prompt);
            try{
                result=Integer.parseInt(value);
                isValid=true;
            }catch(NumberFormatException ex){
                System.out.println("Value must be a number.");
            }
        }while (!isValid);
        return result;
    }

    private int readInt(String prompt, int min, int max){
        int result=0;

        do{
            result=readInt(prompt);
            if(result<min || result>max){
                System.out.printf("Value must be between %s and %s.%n", min, max);
            }
        }while(result<min || result>max);

        return result;
    }
}
