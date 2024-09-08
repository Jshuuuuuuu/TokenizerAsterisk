public class Tokenizer {
    public static void main(String[] args) {
        String input = "JoshGabriel164@gmail.com";
        System.out.println("String: "+input+"\n");

        String[] tokens = manualSplit(input, '*', ' ');

        for (String token : tokens) {

            String[] subTokens = splitByPunctuation(token);
            for (String subToken : subTokens) {
                categorizeToken(subToken);
            }
        }
    }

    public static String[] manualSplit(String input, char delimiter, char space) {
        java.util.List<String> tokens = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == delimiter) {
                if (current.length() > 0) {
                    tokens.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            tokens.add(current.toString());
        }

        return tokens.toArray(new String[0]);
    }

    public static String[] splitByPunctuation(String token) {
        java.util.List<String> subTokens = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();

        for (char c : token.toCharArray()) {
            if (isPunctuation(c) | isWhiteSpace(c) | isSpecialCharacter(c)) {
                if (current.length() > 0) {
                    subTokens.add(current.toString());
                    current.setLength(0);
                }
                subTokens.add(Character.toString(c));
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            subTokens.add(current.toString());
        }

        return subTokens.toArray(new String[0]);
    }

    public static void categorizeToken(String token) {
        boolean hasLetter = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : token.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (isSpecialCharacter(c)) {
                hasSpecial = true;
            }
        }

        if (hasLetter && hasDigit) {
            System.out.println("Token: \"" + token + "\" - Type: Alphanumerical");
            for (char b : token.toCharArray()){
                System.out.print(b + ",");
            }
            System.out.println("\n");
        } else if (hasLetter) {
            System.out.println("Token: \"" + token + "\" - Type: Word");
            for (char b : token.toCharArray()){
                System.out.print(b + ",");
            }
            System.out.println("\n");
        } else if (hasDigit) {
            System.out.println("Token: \"" + token + "\" - Type: Number");
            for (char b : token.toCharArray()) {
                System.out.print(b + ",");
            }
            System.out.println("\n");
        } else if (hasSpecial ) {
            System.out.println("Token: \"" + token + "\" - Type: Special Characters");
        } else if (!token.isEmpty() && isPunctuation(token.charAt(0))) {
            System.out.println("Token: \"" + token + "\" - Type: Punctuation");
        }else if (!token.isEmpty() && isWhiteSpace(token.charAt(0))) {
            System.out.println("Token: \"" + token + "\" - Type: White Space");
        }
    }
    public static boolean isPunctuation(char c) {
        return ",.!?:;".indexOf(c) != -1;
    }
    public static boolean isSpecialCharacter(char c) {
        return "@#$%^&(){}[]|<>".indexOf(c) != -1;
    }
    public static boolean isWhiteSpace(char c) {
        return " \t\n".indexOf(c) != -1;
    }

}