# CodeBreaking

Code Breaking Game!
By: David Zaldivar


Files: 

    Included file to encode: 
        rs2.txt

    Included Frequency File:
        Frequency.txt
    
    Included Subkey file:
        Subkey.txt
    
    Included Dictionary:    
        Dict.txt



Purpose:  
    This program encodes a text file submitted by the user, based on a submitted subkey file.
    Once encoded, frequency analysis of the encoded file will be computed, and the user must choose how many 
    characters (with the highest frequency) to substitute (decode), following the included Frequency file.  

    Once the user has at least one substitution in place, they will have the option to attempt to decode 
    the file using the 'dictionary breaking menu.'  This will scan all words of the encoded file, and display
    a list of any where the first letter is solved. Once the user selects one of these words, possible matches
    from the dictionary will be displayed, and the user must select one, and decide if the changes it would make
    to the encoded file makes sense.  

    Once a single possible match is found for an encoded word in the dictionary, an 'Auto-solve' menu item pops up. 
    The user can choose to keep or discard these matches, as they decode the file.
    
    Encoded characters appear in lowercase
    Decoded characters appear in uppercase

   
To Fix: 
    1.  The frequency Analysis substitution is cheating, by using the SubKey to draw replacement 
        letters from, rather than the computed frequency.  
        Location: EncodedFile.SubstituteTopLetters

    2.  The very last "solved with: " word is incorrect, and showing for the word following the 
        one that actually solved it.  


    3. When not solved, 'You solved it!' is still popping up.  May stay as a morale boost.  
