import ui.ModelChoices;

import java.util.logging.Logger;

class Main{
    public static void main(String[] args){
        Logger.getLogger("org.mongodb.driver").setLevel(java.util.logging.Level.OFF);
        ModelChoices modelChoices= new ModelChoices();
        modelChoices.displayModelMenu();
    }
}