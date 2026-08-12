void main() {
    IO.println("String: " + buildStringAlphabet());
    IO.println("StringBuilder: " + buildStringBuilderAlphabet());
}

/**
* Memory: (n(n+1))/2 = (n^2+n)/2 = O(n^2)
* @return the String with lower case a through z.
*/
String buildStringAlphabet() {
    String retval = "";
    for (int i=0; i < 26; i++) {
        retval += (char)('a'+i);
    }
    return retval;
}

/**
* Memory: O(n)
* @return the StringBuilder with lower case a through z.
*/
StringBuilder buildStringBuilderAlphabet() {
    var retval = new StringBuilder();
    for (int i=0; i < 26; i++) {
        retval.append((char)('a'+i));
    }
    return retval;
}
