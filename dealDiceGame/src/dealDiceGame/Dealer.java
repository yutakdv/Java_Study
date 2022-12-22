package dealDiceGame;

import javax.swing.JOptionPane;

public class Dealer {

    public void dealDiceGame(Player p1, Player p2, GameBoard b, Registrar r) {
    	Dice d_p1 = new Dice();
    	Dice d_p2 = new Dice();
    	
        if (p1.wins() == true) {
            p1.play(d_p1);
            p2.play(d_p2);
        } else {
            p2.play(d_p2);
            p1.play(d_p1);
        }
//        System.out.println("p1 boolean check : " + p1.wins());
//        System.out.println("p2 boolean check : "  +p2.wins());
//        
//        System.out.println("p1 쌍둥이 여부 : " + p1.rolled().twin());
//        System.out.println("p2 쌍둥이 여부 : " + p2.rolled().twin());
//        
//        System.out.println("p1 합 : " + p1.rolled().sum());
//        System.out.println("p2 합 : " + p2.rolled().sum());
        
        if (p1.rolled().twin() == true && p2.rolled().twin() == false) // p1이 쌍둥이 숫자인 경우
        { 
            p1.receivePoint();
        } 
        else if (p1.rolled().twin() == false && p2.rolled().twin() == true) // p2가 쌍둥이 숫자인 경우
        { 
            p2.receivePoint();
        }
        else if (p1.rolled().twin() == true && p2.rolled().twin() == true) // p1, p2 모두 쌍둥이 일 경우
        {
            if (p1.rolled().sum() > p2.rolled().sum()) // p1 쌍둥이 합이 더 클 경우
            {
                p1.receivePoint();
            } 
            else if (p1.rolled().sum() < p2.rolled().sum()) // p2 쌍둥이 합이 더 클 경우
            {
                p2.receivePoint();
            } 
            else // p1, p2 쌍둥이 합이 모두 같을 경우
            {
               p1.reset();
               p2.reset();
            }
        }
        else // 둘 다 쌍둥이가 아닌 경우
        {
            if (p1.rolled().sum() > p2.rolled().sum())
            {
                p1.receivePoint();
            }
            else if (p1.rolled().sum() < p2.rolled().sum()) 
            {
                p2.receivePoint();
            }
            else  // 둘 다 쌍둥이가 아닌 경우 + 두 수의 합이 같을 경 / 두 수 사이의 차를 통해 비
            {
                if (p1.rolled().difference() < p2.rolled().difference()) 
                {
                    p1.receivePoint();
                }
                else if (p1.rolled().difference() > p2.rolled().difference()) 
                {
                    p2.receivePoint();
                }
                else 
                {
                   p1.reset();
                   p2.reset();
                }
            }
        }
        
        int choose = r.wantToContinue();
        if (choose == 0) {
        	p1.reset();
        	p2.reset();
        	this.dealDiceGame(p1, p2, b, r);
        }
        else if (choose == 1) {
            p1.reset();
            p2.reset();
            System.exit(0);
        }

    }
}