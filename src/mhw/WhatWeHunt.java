package mhw;

public class WhatWeHunt {

    private Monster monster;

    private Arm arm;

    public WhatWeHunt(Monster monster, Arm arm) {
        this.monster = monster;
        this.arm = arm;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void getWhatWeHunt(int count) {
        for (int i = 0 ; i < count ; i++ ){
            System.out.println("第"+(i+1)+"次,我们用"+arm.random()+"打"+monster.random());
        }
    }
}
