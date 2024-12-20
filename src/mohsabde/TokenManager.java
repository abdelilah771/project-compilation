package mohsabde;

import java.util.List;

public class TokenManager {
    private String[] tokens;
    private int index;

    public TokenManager(String phrase) {
        this.tokens = phrase.split(" ");
        this.index = 0;
    }

    public boolean match(String category) {
        if (index < tokens.length) {
            String currentToken = tokens[index];
            List<String> validTokens = ParseurDescendant.grammaire.get(category);
            if (validTokens != null && validTokens.contains(currentToken)) {
                index++;
                return true;
            }
        }
        return false;
    }

    public boolean isAtEnd() {
        return index == tokens.length;
    }
}
