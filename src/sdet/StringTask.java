package sdet;

public class StringTask {

    public static void main(String[] args) {
        String str = "Hello. My Dear. Developer.";

        String output = reverseString(str);
        System.out.println(output);  // Output: .Developer .Dear My .Hello



    }

    private static String reverseString(String str) {
        StringBuilder result = new StringBuilder();
        String[] words = str.trim().split("\\s+");

        for (int i = words.length - 1; i >= 0; i--) {

            String word = words[i];
            if(word.contains("e")){
                int idx = word.indexOf("e");
                String findWord = word.substring(idx+1);
                String reverseWord = reverseWord(findWord);
                word = word.substring(0, idx+1) + reverseWord;
            }
            result.append(word).append(" ");
//            result.append(words[i]).append(" ");
        }

        return result.toString().trim();
    }

//    private static String reverseString(String str) {
//        StringBuilder result = new StringBuilder();
//        String[] words = str.trim().split("\\s+"); // split by spaces
//
//        for (int i = words.length - 1; i >= 0; i--) {
//            String word = words[i];
//            if (word.contains("e")) {
//                int idx = word.indexOf("e");
//                String findWord = word.substring(idx + 1);
//                String reverseWord = reverseWord(findWord);
//                word = word.substring(0, idx + 1) + reverseWord; // include 'e' part
//            }
//            result.append(word).append(" ");
//        }
//
//        return result.toString().trim();
//    }


    private static String reverseWord(String word){
        StringBuilder result = new StringBuilder();

        char[] strArray = word.toCharArray();
        int i=0;
        int j=strArray.length-1;

        while( i < j){
            char ch = strArray[i];
            strArray[i] = strArray[j];
            strArray[j] = ch;
            i++;
            j--;
        }

        return new String(strArray);
    }



//    private static String reverseWord(String word) {
//        char[] strArray = word.toCharArray();
//        int i = 0;
//        int j = strArray.length - 1;
//
//        while (i < j) {
//            char temp = strArray[i];
//            strArray[i] = strArray[j];
//            strArray[j] = temp;
//            i++;
//            j--;
//        }
//
//        return new String(strArray); // correct way to return string
//    }

}
