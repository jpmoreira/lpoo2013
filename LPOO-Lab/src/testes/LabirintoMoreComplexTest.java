package testes;

import static org.junit.Assert.*;
import logica_jogo.Coordinate;
import logica_jogo.Dragon;
import logica_jogo.Tabuleiro;

import org.junit.Test;

import cli.CLI_InputHandler;

public class LabirintoMoreComplexTest {

	@Test
	public void testDragaoNuncaDorme() {
		int[] sleep={1};//always again
		Coordinate[] heroMoves= {new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(1,0)};

		System.out.println("Awake Dragon");
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

		Tabuleiro theTab = new Tabuleiro(1, 1, 0, 0, theLab,3,1);
		
		theTab.makeNonRandom(null, sleep, null);//controling sleep
		theTab.printLayout();
		for(int i=0;i<heroMoves.length;i++){
			theTab.movePlayer(heroMoves[i].getX(), heroMoves[i].getY());
			theTab.printLayout();
			assertEquals(((Dragon)theTab.getDragons()[0]).isSleeping(),false);
		}
		
	}
		
	@Test
	public void testDragaoAdormecido() {
		int[] sleep={0};//always again
		int[] wake={1};//never awake
		Coordinate[] heroMoves= {new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(1,0),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(0,1),new Coordinate(1,0)};

		System.out.println("Sleeping dragon");
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

		Tabuleiro theTab = new Tabuleiro(1, 1, 0, 0, theLab,3,1);
		
		theTab.makeNonRandom(null, sleep, wake);//controling sleep
		theTab.printLayout();
		for(int i=0;i<heroMoves.length;i++){
			theTab.movePlayer(heroMoves[i].getX(), heroMoves[i].getY());
			theTab.printLayout();
			assertEquals(((Dragon)theTab.getDragons()[0]).isSleeping(),true);
		}
		
	}

	@Test
	public void testMultiplosDragoes(){
		
		System.out.println("Multiple Dragons");
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

		Tabuleiro theTab = new Tabuleiro(1, 1, 0, 0, theLab,3,6);
		
		theTab.printLayout();
		int nrDragsOnField=0;
		for(int i=0;i<10;i++){
			for(int f=0;f<10;f++){
				if(theLab[i][f]=='D' || theLab[i][f]=='d'){
					nrDragsOnField++;
				}
			}
		}
		assertEquals(6,nrDragsOnField);
		
		
		
	}

}
