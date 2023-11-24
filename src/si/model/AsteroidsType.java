package si.model;

public enum AsteroidsType {
    L(3, 1, 25), M(2, 2,50), S(1, 4, 100);

    private int weightofscale;
    private int weightofvelocity;
    private int score;

    private AsteroidsType(int ws, int wv, int s) {

        weightofscale = ws;
        weightofvelocity = wv;
        score = s;
    }

    public int getScore() {
        return score;
    }

    public int getWeightofscale() {
        return weightofscale;
    }

    public int getWeightofvelocity() {
        return weightofvelocity;
    }
}
