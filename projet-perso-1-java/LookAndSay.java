public class LookAndSay {

    static String nextStepSlow(String s) {
        String result = "";
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int count = 0;
            while (i < s.length() && s.charAt(i) == c) { count++; i++; }
            result += count + "" + c;
        }
        return result;
    }

    static String nextStepFast(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            int count = 0;
            while (i < s.length() && s.charAt(i) == c) { count++; i++; }
            sb.append(count);
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final int DEPTH = 40;
        System.out.println("=== Look and Say – comparaison String vs StringBuilder ===\n");

        long start = System.currentTimeMillis();
        String seq = "1";
        for (int i = 0; i < DEPTH; i++) seq = nextStepFast(seq);
        System.out.printf("[StringBuilder (rapide)] Profondeur %d | Longueur : %d | Temps : %d ms%n",
                DEPTH, seq.length(), System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        seq = "1";
        for (int i = 0; i < DEPTH; i++) seq = nextStepSlow(seq);
        System.out.printf("[String        (lente) ] Profondeur %d | Longueur : %d | Temps : %d ms%n",
                DEPTH, seq.length(), System.currentTimeMillis() - start);
    }
}
