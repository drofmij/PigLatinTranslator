function pigTranslate(original) {
  var translated = false;
  
  var translator = new Translator(original);
  
  translated = translator.translate();
  
  return translated;
}


function Translator(original) {
  this.original = original;
}

Translator.prototype.original = '';

Translator.prototype.translate = function () {
  var translated = '';
  var rawWordArray;
  
  if(typeof this.original === 'string'
          && this.original.length > 0) {
    rawWordArray = this.original.split(" ");
    for(var idx in rawWordArray) {
      var word = splitRawWord(rawWordArray[idx]);
      translated += word.toString();
      if(idx < rawWordArray.length - 1) {
        translated += " ";
      }
    }
  }
  
  return translated;
};


function Word(prefix, stem, suffix, punctuation, shouldCapitalize) {
  if(prefix.substring(0,1).isUppercase) {
    isUpperCase = true;
  }
  this.prefix = prefix.toLowerCase();
  this.stem = stem.toLowerCase();
  this.suffix = suffix.toLowerCase();
  this.punctuation = punctuation.toLowerCase();
  this.shouldCapitalize = shouldCapitalize;
  
}

Word.prototype.prefix = "";
Word.prototype.stem = "";
Word.prototype.suffix = "";
Word.prototype.punctuation = "";
Word.prototype.shouldCapitalize = "";

Word.prototype.toString = function() {
    var translatedWord = "";
		if(this.shouldCapitalize) {
			translatedWord = this.stem.substring(0,1).toUpperCase();
			translatedWord += this.stem.substring(1);
		} else {
			translatedWord = this.stem;
		}
		
		translatedWord += this.prefix + this.suffix + this.punctuation;
		
		return translatedWord;
}

function splitRawWord(rawWord) {
  var word = null;
  var inPrefix = true;
  var foundConsonant = true;
  var prefix = "";
  var stem = "" ;
  var suffix = "ay";
  var punctuation = "";
  var currentLetter;
  if(typeof rawWord !== 'undefined'
        && rawWord.length !== 0) {
    if(!isLetter(rawWord.substring(rawWord.length - 1, rawWord.length))) {
      punctuation = rawWord.substring(rawWord.length - 1, rawWord.length);
    }
    for(var idx = 0; idx < rawWord.length; idx++) {
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


function isVowel(letter) {
  var isVowel = false;
  if(letter.toLowerCase().match("[aeiouy]")) { 
    isVowel = true;
  }
  return isVowel;
}
	
function isUpperCase(letter) {
  var isUpper = false;
  if(letter.substring(0,1).match("[A-Z]")) {
    isUpper = true;
  }
  return isUpper;
}
	
function isLetter(character) {
  var isLetter = false;
  if(character.substring(0,1).match("[a-zA-Z]")) {
    isLetter = true;
  }
  return isLetter;
}

