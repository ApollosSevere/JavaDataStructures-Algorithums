package Stacks;

public class IsMatchAlgorithum {
    public static boolean isMatched(String expression) {
        final String opening = "({[";
        final String closing = ")}]";
        Stack<Character> buffer = new LinkedStack<>();

        for (char c : expression.toCharArray()) {
            //If this particular char is part of opening ...
            if (opening.indexOf(c) != -1) {
//                Then add it to the buffer queue boi !
                buffer.push(c);
            }
//            Now if the char is part of the closing ...
            else if (closing.indexOf(c) != -1) {
//                then we have check if buffer is empty, imeaditie fasle...
                if(buffer.isEmpty()) return false;

//                or check if the closing matches the last element put in buffer!
                if (closing.indexOf(c) != opening.indexOf(buffer.pop())) {
                    return false;
                }
            }
        }
        return buffer.isEmpty();
    }
}
