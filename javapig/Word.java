package javapig;

public class Word {

	private String prefix = "";
	private String stem = "";
	private String suffix = "";
	private String punctuation = "";
	private boolean shouldCapitalize = false;
	
	public Word(String prefix, String stem, String suffix, String punctuation, boolean shouldCapitalize) {
		if(prefix != null
				&& !prefix.isEmpty()){
			if(Word.isUpperCase(prefix.substring(0,1))) {
				shouldCapitalize = true;
			}
			this.prefix = prefix.toLowerCase();
		}
		if(stem != null
				&& !stem.isEmpty()) {
			this.stem = stem.toLowerCase();
		}
		if(suffix != null
				&& !suffix.isEmpty()) {
			this.suffix = suffix.toLowerCase();
		}
		if(punctuation != null
				&& !punctuation.isEmpty()) {
			this.punctuation = punctuation;
		}
		this.shouldCapitalize = shouldCapitalize;
	}


	@Override
	public String toString() {
		String translatedWord = "";
		
		if(shouldCapitalize) {
			translatedWord = stem.substring(0,1).toUpperCase();
			translatedWord += stem.substring(1);
		} else {
			translatedWord = stem;
		}
		
		translatedWord += prefix + suffix + punctuation;
		
		return translatedWord;
	}


	public static Word splitRawWord(String rawWord) {
		Word word = null;
		boolean inPrefix = true;
		boolean foundConsonant = true;
		String prefix = "";
		String stem = "" ;
		String suffix = "ay";
		String punctuation = "";
		String currentLetter;
		if(rawWord != null
				&& !rawWord.isEmpty()) {
			if(!isLetter(rawWord.substring(rawWord.length() - 1, rawWord.length()))) {
				punctuation = rawWord.substring(rawWord.length() - 1, rawWord.length());
			}
			for(int idx = 0; idx < rawWord.length(); idx++) {
				currentLetter = rawWord.substring(idx, idx + 1);
				if(isLetter(currentLetter)) {
					if(inPrefix) {
						if(isVowel(currentLetter)) {
							inPrefix = false;
							stem += currentLetter.toLowerCase();
						} else {
							prefix += currentLetter.toLowerCase();
							foundConsonant = true;
						}
					} else {
						stem += currentLetter.toLowerCase();
					}
				}
			}
			if(!foundConsonant) {
				suffix = "yay";
			}
			word = new Word(prefix, stem, suffix, punctuation, isUpperCase(rawWord.substring(0,1)));
		}
		
		return word;
	}
	
	private static boolean isVowel(String letter) {
		boolean isVowel = false;
		if(letter.toLowerCase().matches("[aeiouy]")) { 
			isVowel = true;
		}
		return isVowel;
	}
	
	private static boolean isUpperCase(String letter) {
		boolean isUpper = false;
		if(letter.substring(0,1).matches("[A-Z]")) {
			isUpper = true;
		}
		return isUpper;
	}
	
	private static boolean isLetter(String character) {
		boolean isLetter = false;
		if(character.substring(0,1).matches("[a-zA-Z]")) {
			isLetter = true;
		}
		return isLetter;
	}
}
