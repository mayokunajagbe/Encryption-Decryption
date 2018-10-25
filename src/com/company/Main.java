package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        String Text = "This is some \"really\" great. (Text)!?";
        Scanner input = new Scanner(System.in);
        System.out.println("THIS IS AN ENCRYPTION PROGRAM.");
        System.out.println("----------------------------------------");
        System.out.print("Enter Text To Encrypt: ");
        String MyText = input.nextLine();
        System.out.print("Enter The Shift Value(- Or +)!0: ");
        int ShiftValue = input.nextInt();
        System.out.print("Enter The Grouping Size(!0): ");
        int GroupSize = input.nextInt();
        System.out.println("----------------------------------------");
        System.out.println("Before Encrypting: " + MyText);
        System.out.println("----------------------------------------");
        System.out.println("Begin Encryption........................");
        System.out.println("----------------------------------------");
        System.out.println("After Encrypting: " + encryptString(MyText,ShiftValue, GroupSize));
        System.out.println("----------------------------------------");
        System.out.println("Begin Decryption........................");
        System.out.println("----------------------------------------");
        System.out.println("After Decrypting: " + decryptString(encryptString(MyText,ShiftValue, GroupSize), ShiftValue));
        System.out.println("----------------------------------------");
        System.out.println("-----------------DONE-------------------");
        System.out.println("----------------------------------------");
    }
    //This method takes a string parameter, removes the special specified characters and returns the string in UPPER
    // CASE.
    public static String normalizeText(String text){
        String pattern = "[ .,:;'\"!?()]";
        String RemoveSpecial = text.replaceAll(pattern, "");
        String NormalizingText = "";
        for (int i = 0; i < RemoveSpecial.length(); i++) {
            if ((Character.isLowerCase(RemoveSpecial.charAt(i)))){

                NormalizingText += Character.toUpperCase(RemoveSpecial.charAt(i));
            } else {
                NormalizingText += RemoveSpecial.charAt(i);
            }
        }
        System.out.println("After Normalizing: " + NormalizingText);
        System.out.println("----------------------------------------");
        return NormalizingText;
    }
    //This method takes a string parameter, adds "OB" before all vowel character and 'Y' and return the updated string.
    public static String obifyText(String Text){
        String ObifiedText = "";
        for (int i = 0; i < Text.length(); i++){
            if (Text.charAt(i) == 'A' || Text.charAt(i) == 'E' || Text.charAt(i) == 'I' || Text.charAt(i) == 'O' || Text.charAt(i) == 'U' || Text.charAt(i) == 'Y'){
                ObifiedText += "OB" + Text.charAt(i);
            } else {
                ObifiedText += Text.charAt(i);
            }
        }
        System.out.println("After Obfuscating: " + ObifiedText );
        System.out.println("----------------------------------------");
        return ObifiedText;
    }
    //This string takes a string and an integer parameter then shifts each character forward or backwards as
    // specified in the "key" parameter and returns the updated string.
    public static String ceasarifyText(String Text, int key){
        String CeaserifiedText = "";
        int PositionAtAlpha = 0;

        for (int a = 0; a < Text.length(); a++){
            char temp = Text.charAt(a);
            PositionAtAlpha = getCharPosition(temp);
            CeaserifiedText += getCeaserChar(PositionAtAlpha, key);
        }
        System.out.println("After Ceaserifying: " + CeaserifiedText);
        System.out.println("----------------------------------------");
        return CeaserifiedText;
    }
    //This method takes a character parameter and returns its position in the Universal alphabetical arrangement.
    public static int getCharPosition(char SingleAlpha){
        String Alphabets = "";
        int CharPosition = 0;
        for (char i = 'A'; i <= 'Z'; i++){
            Alphabets +=  i;
        }

        for (int i = 0; i < Alphabets.length(); i++){
            if (Alphabets.charAt(i) == SingleAlpha){
                CharPosition = i;
                break;
            }
        }

        return CharPosition;
    }
    //This method takes takes two integer parameters, first is the position of a character and the next is the
    // direction and number of movement (- or +)x and returns the corresponding character in the shifted alphabetical
    // arrangement.
    public static char getCeaserChar(int position, int keyValue){
        String CeaserAlpha = shiftAlphabet(keyValue);
        int tempPosition = 0;
        for (int i = 0; i < CeaserAlpha.length(); i++){
            if (position == i){
                tempPosition = i;
                break;
            }
        }

        return CeaserAlpha.charAt(tempPosition);
    }
    //This method takes an integer parameter and returns the alphabetical arrangement with its shifted format.
    public static String shiftAlphabet(int shift){
        int start = 0;

        if (shift < 0){
            start = (int) 'Z' + shift + 1;
        } else{
            start = 'A' + shift;
        }

        String ShiftedAlphabet = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar){
            ShiftedAlphabet += currChar;
        }

        if (ShiftedAlphabet.length() < 26){
            for (currChar = 'A'; ShiftedAlphabet.length() < 26; ++currChar){
                ShiftedAlphabet += currChar;
            }
        }

        return ShiftedAlphabet;
    }
    //This method takes a string and an integer parameter and returns the string in the specified number of groupings
    // with  additional 'x' character behind to fill the grouping (if-needed).
    public static String groupifyText(String Text, int numGroups){
        String GroupifiedText = "";
        int countAlpha = 0;
        for (int i = 0; i < Text.length(); i++){
            if (countAlpha == numGroups){
                GroupifiedText += " ";
                GroupifiedText += Text.charAt(i);
                countAlpha = 1;
            } else if (countAlpha < numGroups){
                GroupifiedText += Text.charAt(i);
                countAlpha++;
            }
        }

        int paddingx = numGroups - (Text.length() % numGroups);

        if (!(paddingx == numGroups)){
            for (int i = 1; i <= paddingx; i++){
                GroupifiedText += "x";
            }
        }
        System.out.println("After Groupifying: " + GroupifiedText);
        System.out.println("----------------------------------------");
        return GroupifiedText;
    }
    //This method takes a string and two integers and returns the encrypted format of the string using the
    // alphabetical shifting value and a grouping value.
    public static String encryptString(String Text, int shiftValue, int codeGroupSize){
        if (shiftValue == 0 && codeGroupSize == 0){
            return "Using " + shiftValue + " as the Shift Value Wouldn't Encrypt Your Text.\n\n" +
                    "Using " + codeGroupSize + " As The Grouping Size Would Make The Text Look The Same.";
        } else if (shiftValue == 0){
            return "Using " + shiftValue + " as the Shift Value Wouldn't Encrypt Your Text.\n";
        } else if (codeGroupSize == 0) {
            return "Using " + codeGroupSize + " As The Grouping Size Would Make The Text Look The Same.\n";
        } if (codeGroupSize < 0){
            codeGroupSize = Math.abs(codeGroupSize);
        }
        String EncryptedText = normalizeText(Text);
        EncryptedText = obifyText(EncryptedText);
        EncryptedText = ceasarifyText(EncryptedText, shiftValue);
        EncryptedText = groupifyText(EncryptedText, codeGroupSize);
        return EncryptedText;
    }
    //This method takes a string and an integer parameter and returns the decrypted format of the encrypted string
    // using the "shiftvalue" specified.
    public static String decryptString(String EncryptedText, int ShiftValue){
        String DecryptedText =  ungroupifyText(EncryptedText);
        DecryptedText = unceaserifyText(DecryptedText, ShiftValue);
        DecryptedText = deobifyingText(DecryptedText);
        return DecryptedText;
    }
    //This method takes a string parameter, removes the whitespaces and 'x' character(s) and returns the updated string.
    public static String ungroupifyText(String GroupifiedText){
        String RemovePattern = "[ x]";
        String UngroupifiedText = GroupifiedText.replaceAll(RemovePattern, "");
        System.out.println("After Ungroupifying: " + UngroupifiedText);
        System.out.println("----------------------------------------");
        return UngroupifiedText;
    }
    //This method takes a string and an integer and set each character back to its original state of universal
    // alphabetical character arrangement with the specified shiftvalue.
    public static String unceaserifyText(String UngroupifiedString, int ShiftValue){
        String UnceaserifiedText = "";
        String OriginalShiftedAlphabets = shiftAlphabet(ShiftValue);
        for (int i = 0; i < UngroupifiedString.length(); i++) {
            UnceaserifiedText += getChar(OriginalShiftedAlphabets.indexOf(UngroupifiedString.charAt(i)));
        }
        System.out.println("After Unceaserifying: " + UnceaserifiedText);
        System.out.println("----------------------------------------");
        return UnceaserifiedText;
    }
    //This method takes an integer parameter of the position of a character and returns the corresponding character
    // in the alphabetical arrangement.
    public static char getChar(int ceaserAlphaPosition){
        String allAphabets = "";
        int tempPosition = 0;
        for (char i = 'A'; i <= 'Z'; i++){
            allAphabets += i;
        }
        for (int i = 0; i < allAphabets.length(); i++){
            if (ceaserAlphaPosition == i){
                tempPosition = i;
                break;
            }
        }
        return allAphabets.charAt(tempPosition);
    }
    //This method takes a string, rempves all/any "OB" string and returns the updated string.
    public static String deobifyingText(String unceaserifiedString){
        return unceaserifiedString.replaceAll("OB", "");
    }
}
