package testes;

import static org.junit.Assert.*;
import logica_jogo.Coordinate;
import logica_jogo.Tabuleiro;

import org.junit.Test;

public class LabirintoTestEagle {

	@Test
	public void testEagle() {
	Coordinate.setBounds(9, 9);
		
		char[][] theLab = {
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'S' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }
		};
		Tabuleiro theTab = new Tabuleiro(1, 1, 4, 1, theLab, 1, 1);
		theTab.printLayout();
		Coordinate swordpos =new Coordinate(theTab.getSword().getPosition());
		System.out.println("pos x="+swordpos.getX()+" y= "+swordpos.getY());
		theTab.getEagle().StartEagle(theTab.getHero(), theTab.getSword());
		theTab.movePlayer(0, 0);
		theTab.movePlayer(0, 0);
		theTab.movePlayer(0, 0);;
		int x1=swordpos.getX();
		int x2=theTab.getEagle().getPosition().getX();
		
		if(x1==x2){
			System.out.println("equal");
		}
		assertEquals(swordpos.getX(),theTab.getEagle().getPosition().getX());
		
		
	}
	

}
