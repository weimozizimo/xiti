package mhw;

public class Arm implements RandomMHW{

    private static String[] arms = CommonConst.ARMS;

    public String random() {
        int i = ran.nextInt(arms.length);
        return arms[i];
    }
}
