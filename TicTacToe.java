import java.util.Scanner;

public class TicTacToe {
//  x- 1
//  0- 2
//  empty- 0
	static int n;	
	static int[][] board;
	static Scanner sc = new Scanner(System.in);
	static Integer player = 1;
	static Integer winner = 0;
	static int moveCount = 0;
	
	public static void main(String[] args) {
		
		System.out.println("Enter value of n to create n x n board: ");
		n=sc.nextInt();
		board= new int[n][n];
		System.out.println(board.length);
		while (winner == 0) {
			System.out.println("Player " + player + ", give your next move ");
			System.out.println("Input the next move row");
			int row = sc.nextInt();
			System.out.println("Input the next move column");
			int col = sc.nextInt();
			int moveStatus= makeMove(row, col);
			if (moveStatus == 0)
				continue;
			else if (moveStatus == 2) {
				System.out.println("All moves exhausted. The Game has ended and is a draw.");
				break;
			}
		}
	}

	private static int makeMove(int row, int col) {
		if (checkMoveValid(row, col) == true) {
			board[row][col] = player;
			moveCount++;
		} else {
			System.out.println("The place is already marked, choose another position");
			return 0;
		}
		printBoard();
		int winnerStatus = checkWinner(row, col);
		if (winnerStatus == 2) {
			return 2;
		}
		if (winnerStatus == 0) {
			togglePlayer();
		}
		return 1;
	}

	private static boolean checkMoveValid(int row, int col) {
		if (board[row][col] == 0)
			return true;
		return false;
	}

	private static void togglePlayer() {
		if (player == 1)
			player = 2;
		else
			player = 1;
	}

	private static int checkWinner(int row, int col) {
		int rowScore = 0, colScore = 0, diagScore = 0, crossDiagScore = 0;
		// check the winner
		for (int i = 0; i < board[0].length; i++) {
			if (board[row][i] == player)
				rowScore++;
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == player)
				colScore++;
		}
		for (int i = 0, j = 0; i < board.length && j < board[0].length; i++, j++) {
			if (board[i][j] == player)
				diagScore++;
		}
		for (int i = 0, j = board[0].length - 1; i < board.length && j >= 0; i++, j--) {
			if (board[i][j] == player)
				crossDiagScore++;
		}
		if (rowScore == n || colScore == n || diagScore == n || crossDiagScore == n) {
			winner = player;
			System.out.println("The Game has ended. The winner is " + player);
			return 1;
		} else if (moveCount == (n*n)) {
			return 2;
		}
		return 0;

	}

	private static void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

}
