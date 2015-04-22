// First FRQ

public static void reverseArray(int[] arr){
    int[] temp = new int[arr.length];
    for(int i = 0; i < arr.length; i++){
        temp[i] = arr[arr.length- i];
    }

    //copy it back
    for(int j = 0; j < arr.length; j++){
        arr[j] = temp[j];
    }
}

public void reverseAllRows() {

    for(int i = 0; i < mat.length; i++){
        revereAllArray(mat[i]);
    }
}

public void reverseAMatrix() {
    reverseMatrix();

    //now you just have to flip the vertical rows
    int[][] temp = new int[3][2];
    for(int a = 0; a < mat[0].length; a++){
        for(int b = 0; b < mat.length; b++){
            temp[a][b] = arr[a][mat.length - b];
        }
    }

}

//FRQ Number 2
public List<Integer> getBlankPositions() {
    List<Integer> blankSpaces = new List();
    for(int i = 0; i < sentence.length(); i++){
        if(sentence.charAt(i).equals(" ")) {
            blankSpaces.add(i);
        }
    }
    return blankSpaces;
}


public int countWords(){
    return getBlankPositions().length() + 1;
}

public String[] getWords() {
    String[] words = new String[countWords()];
    ArrayList<Integer> wordList = getBlankPositions();

    //if there is only one word
    if(countWords()= 1){
        words[0] = sentence;
        return words;
    }
    else{
        words[0] = sentence.substring(0, wordList.get(0)); //get the first word
    }

    //get the words between the first word and lats word
    for(int i =1; i < countWords(); i++){
        word[i] = sentence.substring(wordList.get(i - 1), sentence.substring(i));
    }

    //get the last word
    words[countWords() - 1] = sentence.substring(wordList.get(wordList.size()), sentence.length());


    //return the wor
    return words;
}


//FRQ number 3

public Player requestSlot(String playerName) {
    //check empty
    for(int i = 0; i < slots.length; i++){
        if(slots[i] = null ) {
            player temp = new Player(playerName, i);
            slots[i] = temp;
            return temp;
        }
    }

    waitingList.add(playerName);
}

public Player cancelAndReassignSlot(Player p){
    int player = p.getPlayerNumber();
    if(waitingList.size() != 0){
        slots[i] = new Player(waitingList.get(0), i);
        waitingList.remove(0);
    }
    else{
        slots[i] = null;
    }

    return slots[i];
}

//FRQ number 4
//
/public void reset() {
    if(armisFacingRight()){
        arm.changeDirection();
    }
    arm.moveForward(arm.getCurrentIndex());
    arm.changeDirection();

}

public int mostAcidic() {

    int mostAcidic = 100000;
    int startingPoint = arm.currentIndex;
    int mostAcidicPos = arm.currentIndex;

    //two differnt if statemnts for the direction
    if(arm.isFacingRight()){
        //its facing right so just check from the postion tot he rigth
        for(i = arm.currentIndex; i < solutions.size(); i++){
            int acidity = List.get(i).getPH();
            if(acidity < mostAcidic){
                mostAcidic = acidity;
                mostAcidicPos = i;

            }
        }
    }
    //this means its facing left
    else {
        for(i = arm.currentIndex; i > 0; i--){
            int acidity = List.get(i).getPH();
            if(acidity < mostAcidic){
                mostAcidic = acidity;
                mostAcidicPos = i;
            }
        }
    }
    arm.moveForward(Math.abs(mostAcidicPos - startingPoint));
    if(!arm.isFacingRight()){
        arm.changeDirection();
    }

    return mostAcidic;:if expand("%") == ""|browse confirm w|else|confirm w|endif

}


