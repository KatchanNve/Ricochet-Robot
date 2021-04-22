package games;

import graphics.AbstractModelListener;
import graphics.ModelListener;

public class Main{

    public static void main(String[] args) {
        Game game = new Game();
        game.addListener(
                new ModelListener(){
                    @Override
                    public void modelChange(Object source){
                        System.out.println("Changement de l'état du feu");
                    }
                }
        );

        System.out.println(game);
    }

}
