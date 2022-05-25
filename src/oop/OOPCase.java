package oop;

//Case 1 : Abstract
class Character1{

    /** 데이터 추상화 */
    private int hp;
    private int mp;
    private int attack;

    Character1(int hp, int mp, int attack) {
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
    }

    /** 제어 추상화 */
    public double getOverallStatusRate(){
        double rate = hp * mp * attack * 0.5;
        return rate;
    }

}

//Case 2 : Encapsulation
interface Moving{
    void move(int x, int y);
}

class Character2 implements Moving{
    private int x;
    private int y;

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }
}


//Case 3 : Inheritance
class Character3{

    private int hp;
    private int mp;

    public Character3(int hp, int mp) {
        this.hp = hp;
        this.mp = mp;
    }

}

class Wizard1 extends Character3{

    public Wizard1(int hp, int mp) {
        super(hp, mp);
    }

}

class Warrior1 extends Character3{

    public Warrior1(int hp, int mp) {
        super(hp, mp);
    }

}

//Case 4 : Polymorphism
interface Character4{
    void levelUp();
}

class Wizard2 implements Character4{

    private int hp, mp;

    @Override
    public void levelUp() { hp += 3; }

    public void drinkPotion(int hp){
        this.hp += hp;
    }

    public void drinkPotion(int hp, int mp){
        this.hp += hp;
        this.mp += mp;
    }

}

class Warrior2 implements Character4{

    private int hp;

    @Override
    public void levelUp() { hp += 10; }
}


public class OOPCase {}


