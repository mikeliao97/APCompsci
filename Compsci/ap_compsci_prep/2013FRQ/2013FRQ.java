FRQ #1 

public downLoadInfo getDownloadInfo(String title){
	for(int i=0; i < downLoadList.size(); i++){
		if(downLoadList.get(i).getTitle() == title){
			return downList.get(i);
		}
	}
	return null;
}

public void updateDownloads(List<String> titles){
	for(int i =0; i < titles.size(); i++){
		String currentTitle = titles.get(i);
		//use this title to compare to other titles
		boolean makeNew = true;
		for (int j =0;  j < downloadList.size(); i++) {
			DownloadInfo currentDownload = downloadlist.get(i);
			if(currentDownload.getTitle() == currentTitle){
				currentDownload.incrementDownloads(); //add a download
				makeNew = false;
				break;
			}
		}
		if(makeNew == true){
			downloadlist.add(new downloadInfo(title));
		}

	}
}



FRQ #2 
//this initliazes a board array
public TokenPass(int playerCount){
	board = int[playerCount];
	Random generator = new Random();
	for(int i = 0; i < board.length; i++){
		board[i] = board.nextInt(10);
 	}
}

public distributeCurrentPlayerTokens() {
	int numOfTokens = board[currentPlayer];
	if(numOfTokens >= 1){				
		for(int i = 0; i < numOfTokens; i++){
			board[currentPlayer] -= 1; //Remove a token
			
			//If it overflows
		 	if(currentPlayer + 1 >= board.length){
		 		board[currentPlayer - board.length] += 1;
		 	}
		 	else{
		 		board[currentPlayer] += 1;
		 	}

		}
	}
}

