void main(String[] args) {
    long x = Long.parseLong(args[0]);
    IO.println(isPalindrome(x));
}

boolean isPalindrome(long x) {
    if (x < 0) return false; // negative
    if (x % 10 == 0 && x != 0) return false; // ends in zero, (unless it's zero)
                                             // 
    long reverted = 0;
    // while x is larger than reverted pop the last digit off of x and append it to
    // the end of reverted.
    while (x > reverted) {
        reverted = reverted * 10 + x % 10;
        x /= 10;
    }
    // x is no longer larger than reverted, so we see if they're equal (if length of
    // x was even) or if exclude the last digit from reverted in case the length of
    // x is odd.
    return x == reverted || x == reverted / 10;
}