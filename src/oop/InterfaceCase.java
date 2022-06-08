package oop;

//case 1 : interface
interface Skill{

    int MAX_SKILL_LEVEL = 3;

    void useSkillQ();
    void useSkillW();
    void useSkillE();
    void useSkillR();

}

//case 2 : Default keyword
interface Attack{

    default void attack(){
        System.out.println("attack!");
    }

}

//case 3 : Multi-implement
public class InterfaceCase implements Skill, Attack{

    @Override
    public void useSkillQ() {}

    @Override
    public void useSkillW() {}

    @Override
    public void useSkillE() {}

    @Override
    public void useSkillR() {}

}

//case 4 : How to use interface 1
interface Champion{

    int MAX_LEVEL = 18;

    void useSkillQ();
    void useSkillW();
    void useSkillE();
    void useSkillR();

    default void attack(){
        System.out.println("attack!");
    }

}

//case 5 : How to use interface 2
class UnitA implements Champion{

    @Override
    public void useSkillQ() { System.out.println("A skill Q");}

    @Override
    public void useSkillW() {
        System.out.println("A skill W");
    }

    @Override
    public void useSkillE() { System.out.println("A Skill E");}

    @Override
    public void useSkillR() { System.out.println("A Skill R");}

}

class UnitB implements Champion{

    @Override
    public void useSkillQ() { System.out.println("B skill Q");}

    @Override
    public void useSkillW() {
        System.out.println("B skill W");
    }

    @Override
    public void useSkillE() { System.out.println("B Skill E");}

    @Override
    public void useSkillR() { System.out.println("B Skill R");}

}