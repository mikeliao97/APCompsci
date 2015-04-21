//2011 FRQ #1

public in limitAmplittude(int limit){
    int numChanged =  0;

    for(int i =0; i < sample.length; i++){
        int currentNum = sample[i];
        if(currentNum > Math.abs(limit)){
            sample[i] = limit;
            numChanged++;
        }
        else if (currentNum < (Math.abs(limit) * -1)){
            sample[i] =  (Math.abs(limit) * -1);
            numChanged++;
        }<`0`>
    }


    return numChanged;
}

public void trimSilenceFromBeginning(){
    //get the number of 0's in the beginning
    int numZeros = 0;
    int i = 0;
    while(samples[i] = 0){
        numZeros++;
        i++;
    }
    //now we have number of zeros
    //make an array the size of sample minus the numzeros
    int[] newArray = new int[this.sample.length - numZeros];
    for(int i = numZeros; i < sample.length; i++){
        newArray[i - numZeros] = this.sample[i];
    }
    this.sample = newArray;
}



//Problem number 2
public int nextTankToFill(int threshold){
    int currentIndex = filler.getCurrentIndex();
    for(int i =0; i < tank.size(); i++){
        if(tank.get(i).getFuelLevel <= threshold &&
            tank.get(i).getFuelLevel() < this.tanks.get(currentIndex).getFuelIndex()  ){
            return i;
        }
    }
    return currentIndex;
}

public void moveToLocation(int locIndex){
    int currentIndex = filler.getCurrentIndex();
    //the index is to the right of the robot
    if(locIndex >= currentIndex ){
        if(!filler.isFacingRight()){
            filler.changeDirection();
        }
        filler.moveForward(locIndex - currentIndex);
    }
    //this means the robot is the rigth  of the locIndex
    else{
        filler.changeDirection();
        filler.moveForward(currentIndex - locIndex);
    }
}

private void fillBlocks(String str){
    int tracker = 0;
    for(int i = 0; i < numRows; i++){
        for(int j=0; j <numCols; j++){
            if(i * j >= str.length()){
                letterblock[i][j] = "A";

            }
            else{
                letterblock[i][j] = str.substring(tracker, tracker++);
                    tracker++;
            }
        }

    }
}

public String encryptMessage(String message){
    String finalString = "";
    int segment = numRows * numCols;
    int tracker = 0;
    boolean continue = true;

    while(continue){
        String temp = fillBlocks(message.substring(tracker, tracker + segment));
        finalString += temp;

        if(tracker + segment > message.length){
           String temp = fillBlocks(message.substring(tracker, message.length()));
           continue = false;
        }



    finallString += fillBlcoks();
     tracker += segment;


    }
    return finalString;
}
