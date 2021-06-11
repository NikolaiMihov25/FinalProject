package war;

import java.util.LinkedList;

public class NotEnoughCardsException extends Exception{
	private int numberOfCards;
	public NotEnoughCardsException(int numberOfCards) {
		super("Not enough cards " + numberOfCards);
		this.numberOfCards = numberOfCards;
		System.out.print("NO   ");
	}
	public int getNumberOfCards() {
		return numberOfCards;
	}
}
