package mohsabde;

import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParseurDescendant {
    static final Map<String, List<String>> grammaire = new HashMap<>();
    private TokenManager tokenManager;

    static {
        grammaire.put("articles", Arrays.asList("le", "la", "les", "une", "un", "des"));
        grammaire.put("noms", Arrays.asList("chat", "fromage", "téléphone", "chien", "souris", "matin", "nuit"));
        grammaire.put("verbes", Arrays.asList("mange", "mangent", "charge", "sonne", "dort", "joue"));
        grammaire.put("adjectifs", Arrays.asList("petit", "grand", "rouge", "rapide", "lent"));
        grammaire.put("adverbes", Arrays.asList("rapidement", "lentement", "avec appétit", "silencieusement"));
        grammaire.put("prepositions", Arrays.asList("avec", "dans", "sur", "sous"));
        grammaire.put("conjonctions", Arrays.asList("et", "ou"));
    }

    public ParseurDescendant(String phrase) {
        this.tokenManager = new TokenManager(phrase);
    }

    public boolean analyser() {
        return phrase() && tokenManager.isAtEnd();
    }

    private boolean phrase() {
        if (!proposition()) return false;
        while (tokenManager.match("conjonctions")) {
            if (!proposition()) return false;
        }
        return true;
    }

    private boolean proposition() {
        return sujet() && verbe() && (complement() || true) && (adverbe() || true);
    }

    private boolean sujet() {
        return article() && (adjectif() || true) && nom();
    }

    private boolean complement() {
        return (article() && (adjectif() || true) && nom()) || groupePrepositionnel();
    }

    private boolean groupePrepositionnel() {
        return tokenManager.match("prepositions") && article() && nom();
    }

    private boolean verbe() {
        return tokenManager.match("verbes");
    }

    private boolean adjectif() {
        return tokenManager.match("adjectifs");
    }

    private boolean adverbe() {
        return tokenManager.match("adverbes");
    }

    private boolean article() {
        return tokenManager.match("articles");
    }

    private boolean nom() {
        return tokenManager.match("noms");
    }
}
