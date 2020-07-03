INTRODUCTION
------------
Trigrams can be used to mutate text into new, surreal, forms. 
But what heuristics do we apply to get a reasonable result?

Trigram analysis is very simple. Look at each set of three adjacent words in a document.
Use the first two words of the set as a key, and remember the fact that the third word 
followed that key. Once you’ve finished, you know the list of individual words that can 
follow each two word sequence in the document. 

	* For more information, visit the below page:
      http://codekata.com/kata/kata14-tom-swift-under-the-milkwood/

trigrams is a program to generate mutate text into new forms.

REQUIREMENTS
------------
This program requires Java 8 version to execute.

Executing
------------
	* Program takes three parameters 
		- input file path
		- 1 or 0 (1 to generate new text with with all special characters with line breaks
		          0 to generate new text with no special characters)
		- output file path
	  ex: java Trigram c:\\inputfile.txt 1 c:\\outputfolder	
