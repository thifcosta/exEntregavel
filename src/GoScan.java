import java.util.Scanner;

public class GoScan {

    public String validString(String userInput){
        Scanner scn = new Scanner (System.in);
        String theStr = scn.nextLine().trim();
        while(theStr.length()==0){
            System.out.println("O campo \""+userInput+"\" não deve ficar em branco. Digite novamente:");
            theStr = scn.nextLine().trim();
        }
        return theStr;
    }

    public Integer validInt(String userInput) {
        boolean isParse = false;
        String str = "";
        while (!isParse) {
            str = validString(userInput);
            try {
                Integer.parseInt(str);
                isParse = true;
            } catch (Exception e) {
                System.out.println("O campo \"" + userInput + "\" só recebe números inteiros. Digite novamente:");
            }
        }
        return Integer.parseInt(str);
    }

    public float validFloat(String userInput) {
        boolean isParse = false;
        String str = "";
        while (!isParse) {
            str = validString(userInput);
            try {
                Float.parseFloat(str);
                isParse = true;
            } catch (Exception e) {
                System.out.println("O campo \"" + userInput + "\" só recebe números.\nObs: Para valores quebrados, use o ponto (exemplo: \"29.90\").\nDigite novamente:");
            }
        }
        return Float.parseFloat(str);
    }
}
