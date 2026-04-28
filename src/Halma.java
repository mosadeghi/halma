import menus.ActionOption;
import menus.MainMenu;
import menus.Records;
import menus.Settings2PlayerMode;
import menus.Settings4PlayerMode;

public class Halma {
	
	// setting and running 2player mode
	public static void play2Player() {
		gameTwoPlayerMode.gameModel.GameSession sd = new Settings2PlayerMode(new Object()).get();
		
		if (sd != null) {
			gameTwoPlayerMode.gameModel.GameSession thisGame;
			do {
				thisGame = new gameTwoPlayerMode.Game(sd).run();
				if(thisGame.winner != gameTwoPlayerMode.gameModel.Player.EMPTY) {
					String record = "2Player Mode:\n";;
					if (thisGame.winner == gameTwoPlayerMode.gameModel.Player.PLAYER1)
						record += thisGame.playerOneName +": "+thisGame.playerOnePlayedTurns+" Moves\n";
					else
						record += thisGame.playerTwoName +": "+thisGame.playerTwoPlayedTurns+" Moves\n";
					Records.saveRecord(record);
				}
			} while (thisGame.restartGame);
		}
	}


	// setting and running 4player mode
	public static void play4Player() {
		gameFourPlayerMode.gameModel.GameSession sd = new Settings4PlayerMode(new Object()).get();
		
		//if player didnt cancel settings
		if (sd != null) {
			gameFourPlayerMode.gameModel.GameSession thisGame;
			do {
				thisGame = new gameFourPlayerMode.Game(sd).run();
				// if at least one player ended game. saves record
				if(thisGame.ended > 0) {
					int i = 0;
					String record = "4Player Mode:\n";
					// adding record for players who finished
					for (;i<thisGame.ended;i++) {
						record += "#"+(i+1)+" ";
						record += thisGame.getPlayerName(thisGame.winner.get(i));
						record += ": "+thisGame.getMove(thisGame.winner.get(i))+" Moves\n";
					}
					//adding record for players who did not
					for(;i<4;i++){
						record += "#? ";
						record += thisGame.getPlayerName(thisGame.winner.get(i));
						record += ": ??? Moves\n";
					}
					// saving records
					Records.saveRecord(record);
				}
			} while (thisGame.restartGame);
		}
	}
	
	public static void main(String[] args) {
		ActionOption whatToDo;
		do {
			whatToDo = new MainMenu(new Object()).getOption();
			switch (whatToDo) {
			case PLAY2:
				play2Player();
				break;
			case PLAY4:
				play4Player();
				break;
			case RECORDS:
				new Records(new Object()).showRecords();
				break;
			default:
			}
		} while (whatToDo != ActionOption.EXIT);
	}
}
