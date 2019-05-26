import java.util.ArrayList;
import java.util.List;

public class PrintData {

    private String spc = " ";
    private String dash = "-";
    private String div = "|";
    private String corners = "+";
    private String lineCross = "+";
    private String crop = "...";

    //Se o parametro for null, mostrar a String:
    private String naoInf = "Não Informado";

    private int getColSize = 0;
    private int getOffset = 0;
    private int getNumCols = 0;

    private List<String> printList = new ArrayList<>();

    public void printObject(String title, String colTitle, int colSize, int offset) {
        String[] colTitleAry = trimStrAry(colTitle);
        getNumCols = colTitleAry.length;
        getColSize = colSize;
        getOffset = offset;

        String spcD = multi(spc, colSize);
        String dashD = multi(dash, colSize);
        String dashDiv = multi(dash, colSize) + "|";
        String midFix = multi(dashDiv, getNumCols);
//        String returnString = "";

        String topBottomPrint = corners + multi(dashD, getNumCols) + multi(dash, getNumCols - 1) + corners;
        String titleSpcPrint = div + multi(spcD, getNumCols) + multi(spc, getNumCols - 1) + div;
        String titleDivPrint = lineCross + midFix.substring(0, midFix.length() - 1) + lineCross;
//        String columnsPrint = "";

        System.out.println(topBottomPrint + "\n" +
                titleSpcPrint + "\n" +
                centerTitle(title, titleSpcPrint.length()) + "\n" +
                titleSpcPrint + "\n" +
                titleDivPrint + "\n" +
                categoryPrint(colTitleAry) + "\n" +
                titleDivPrint);

        for (String str : printList) {
            String[] strAry = trimStrAry(str);
            System.out.println(categoryPrint(strAry));
        }

        System.out.println(topBottomPrint);
    }

    public void addToList(String whatToPrint){
        printList.add(whatToPrint);
    }

    public void clearList(){
        getColSize = 0;
        getOffset = 0;
        printList = new ArrayList<>();
    }

    private String multi(String theChar, int amount) {
        String theString = "";
        for (int i = 0; i < amount; i++) {
            theString = theString + theChar;
        }
        return theString;
    }

    private String centerTitle(String title, int spcAmt) {
        int tLen = title.length();
        int redAmt = 2+crop.length();
        if(tLen>(spcAmt-redAmt)){
            int reduce = tLen-spcAmt+redAmt;
            title = title.substring(0,title.length()-reduce)+"...";
        }
        int spcR = (spcAmt-2-title.length())/2;
        String theReturn = div+multi(spc,spcR)+title;
        theReturn = theReturn+multi(spc,spcAmt-theReturn.length()-1)+div;
        return theReturn;
    }

    private String categoryPrint(String[] stringAry){
        if(getColSize>3){
            String theReturn = div;
            String theOffset = multi(spc,getOffset);
            String curStr;
            for(int i=0; i<getNumCols; i++){
                try{
                    curStr = theOffset+stringAry[i];
                }catch (Exception e){
                    curStr = theOffset;
                }
                int curCol = getColSize-crop.length();
                int curLen = curStr.length();
                if(curLen>curCol){
                    int reduce = curLen-curCol;
                    try {
                        theReturn = theReturn + curStr.substring(0, curLen - reduce) + crop + div;
                    } catch (Exception e){}
                }else{
                    int remain = getColSize-curLen;
                    theReturn = theReturn+curStr+multi(spc, remain)+div;
                }
            }
            return theReturn;
        }else{
            return ">>>>> AUMENTE O TAMANHO DA COLUNA <<<<<";
        }
    }

    private String[] trimStrAry(String stringAry){
        String[] printObj = stringAry.split(",");
        for(int i=0; i<printObj.length; i++){
            if(printObj[i].equals("null")){
                printObj[i] = naoInf;
            }
            printObj[i] = printObj[i].trim();
        }
        return printObj;
    }
}