package testes;

import static org.junit.Assert.*;
import logica_jogo.Coordinate;
import logica_jogo.LabGenerator;
import logica_jogo.Tabuleiro;

import org.junit.Test;

public class LabirintoTestSimples {

	static char[][] predefinedLab = {
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, };
	
	@Test
	public void testPossibleMove() {

		System.out.println("Possible Move");
		Coordinate.setBounds(9, 9);
		
		char[][] theLab = {
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, };

		Tabuleiro theTab = new Tabuleiro(1, 1, 0, 0, theLab, 1, 1);
		theTab.printLayout();
		theTab.movePlayer(1, 0);
		assertEquals(2, theTab.getHero().getX());
		assertEquals(1, theTab.getHero().getY());
		theTab.printLayout();

	}

	@Test
	public void testImpossibleMove() {
		
		System.out.println("Impossible Move");
		
		Coordinate.setBounds(9, 9);
		char[][] theLab = {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, };
		
		Tabuleiro theTab = new Tabuleiro(1, 1, 0, 0, theLab, 1, 1);
		theTab.printLayout();
		theTab.movePlayer(0, -1);
		assertEquals(1, theTab.getHero().getX());
		assertEquals(1, theTab.getHero().getY());
		theTab.printLayout();

	}
	
	@Test
	public void testApanhaEspada(){
		
		System.out.println("Test apanha espada");
		char[][] theLab = {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'S' },
				{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, };
		
		Tabuleiro theTab = new Tabuleiro(1, 1, 2, 1, theLab, 1, 1);
		theTab.printLayout();
		theTab.movePlayer(1, 0);
		assertEquals(2, theTab.getHero().getX());
		assertEquals(1, theTab.getHero().getY());
		assertEquals('A', theTab.getHero().getPlaceHolder());
		theTab.printLayout();
		
	}

	@Test
	public void mataDragao(){
		System.out.println("Test mataDragao");
		char[][] theLab = {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'S' },
				{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, };
		
		Tabuleiro theTab = new Tabuleiro(1, 1, 2, 1, theLab, 1, 1);
		theTab.placeDragonAt(5, 1);
		theTab.printLayout();
		Coordinate[] heroMoves= {new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0)};
		assertEquals(theTab.getDragons()[0].isPlaying(), true);
		
		for(int i=0;i<heroMoves.length;i++){
			theTab.movePlayer(heroMoves[i].getX(), heroMoves[i].getY());
			theTab.printLayout();
		}
		
		assertEquals(theTab.getDragons()[0].isPlaying(), false);
		
	}
	
	@Test
	public void chegaAoFim(){
		System.out.println("Test chega ao fim");
		char[][] theLab = {
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
				{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'S' },
				{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
				{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' }, };
		
		Tabuleiro theTab = new Tabuleiro(1, 1, 2, 1, theLab, 1, 1);
		theTab.placeDragonAt(5, 1);//ensure dragon is at position (5,1);
		theTab.printLayout();//print maze to see whats happening
		theTab.debugOn();//turn on debbug mode
		Coordinate[] heroMoves= {new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(1,0)};
		assertEquals(theTab.getDragons()[0].isPlaying(), true);
		assertEquals(theTab.isFinished(), false);
		for(int i=0;i<heroMoves.length;i++){
			theTab.movePlayer(heroMoves[i].getX(), heroMoves[i].getY());
			theTab.printLayout();
		}
		
		assertEquals(theTab.getDragons()[0].isPlaying(), false);
		assertEquals(theTab.isFinished(),true);
	}
}
