package mhw;

public class Monster implements RandomMHW{

    private String[] monsters = CommonConst.MONSTERS;

    public String random() {
        int i = ran.nextInt(monsters.length);
        return monsters[i];
    }
}
