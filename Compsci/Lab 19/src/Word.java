
public class Word implements Comparable<Word> {
	String word;
	int freq;
	
	public Word(int freq, String word){
		this.word = word;
		this.freq = freq;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	public int compareTo(Word example) {
		return this.getFreq() - example.getFreq();
	} 
}
