/*
    
    IMenu interface.  
    
    This interface is the blueprint for the Menu.  Only Menu needs to see these
    methods, and this is everything Menu needs to run
 */

public interface IMenu {

    public void SetMenuItems(String x);

    public void PrintMainMenu();

    public String runMenu();

    boolean isSelectionGood(String x);

}
