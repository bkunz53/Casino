import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Casino
{
	public static void main(String[] args)
	{
		int input=0;
		boolean gameEnd=false;
		do{
			System.out.println("Welcome to our casino");
			System.out.println("Heres Our Games:");
			System.out.println("#1 Roulette");
			System.out.println("#2 War");
			System.out.println("#3 Slots");
			System.out.println("Which one would you like to play?(say \"0\" to leave)");
			input= readName();
			if(input==0){
				gameEnd=true;
				System.out.println("Thanks for playing with us!");
			}
			else if(input==1){
				playRoulette();
			}
			else if(input==2){
				playWar();
			}
			else if(input==3){
				playSlots();
			}
		}while(gameEnd==false);

	}
	static int readInput()
	{
		int enteredNumber = 0;
		Scanner myScanner = new Scanner(System.in);
		boolean numberError = false;
		String enteredString = "";
		do {
			try {
				enteredString = myScanner.next();
				enteredNumber = Integer.parseInt(enteredString.trim());
				numberError = false;
				if(enteredNumber<0) {
					System.out.print("You entered a negative number... Please try again: ");
					numberError=true;
				}
				if(enteredNumber==0) {
					numberError=true;
					System.out.print("Your entry: \"" + enteredString + "\" is invalid...Please try again: ");
				}
			} 
			catch(Exception e) {
				System.out.print("Your entry: \"" + enteredString + "\" is invalid...Please try again: ");
				numberError = true;
			}
		} while (numberError == true );
		return enteredNumber;
	}
	static int readName()
	{
		int enteredNumber = 0;
		Scanner myScanner = new Scanner(System.in);
		boolean numberError = false;
		String enteredString = "";
		do {
			try {
				enteredString = myScanner.next();
				enteredNumber = Integer.parseInt(enteredString.trim());
				numberError = false;
				if(enteredNumber<0) {
					System.out.print("You entered a negative number... Please try again: ");
					numberError=true;
				}
				if(enteredNumber==0) {
					numberError=false;
				}

			} 
			catch(Exception e) {
				System.out.print("Your entry: \"" + enteredString + "\" is invalid...Please try again: ");
				numberError = true;
			}
		} while (numberError == true );
		return enteredNumber;
	}
	static String readString()
	{
		Scanner myScanner = new Scanner(System.in);
		boolean stringError = false;
		String enteredString = "";
		do {
			try {
				System.out.print("Do you want to play again? Please enter Yes or No: ");
				enteredString = myScanner.next();
				enteredString = enteredString.toLowerCase();
				if(enteredString.equals("yes")||enteredString.equals("no")) {
					stringError=false;
				}
				else{
					System.out.println("** \""+enteredString+"\" is an Invalid Response**");
					stringError=true;
				}
			} 
			catch(Exception e) {
				System.out.println("Your entry: \"" + enteredString + "\" is invalid...Please try again");
				stringError = true;
			}
		} while (stringError == true );
		return enteredString;
	}
	static void playRoulette(){
		boolean end=false;
		int playNum=0;
		boolean invalid=true;
		boolean hh=false;
		int count=0;
		String input="No";
		System.out.println("Welcome to the Roulette Game!");
		buffReader reader= new buffReader();
		ArrayList <String> buffArr= reader.getData();
		reader.readData();
		Scanner scanner = new Scanner(System.in);
		buffWriter writer = new buffWriter();
		ArrayList<Player> players = new ArrayList<Player>();
		int numOfPlayers;
		int playerArray= 0;
		System.out.println("Please enter the number of players for this round:");
		numOfPlayers = readInput();
		for(int i = 0; i < numOfPlayers; i++)
		{
			if(buffArr.isEmpty()==true){
				playNum=-1;
			}
			else if(buffArr.isEmpty()==false){
				hh=false;
				System.out.println("Who do you want to play from this list?");
				System.out.print("Please enter a player number for Player #"+ (i + 1) + " (or 0 to enter manually): ");
				playNum = readName()-1;
				if(playNum!=-1){
					if(playNum<buffArr.size()){
						if(buffArr.get(playNum).equals("")==true){
							System.out.println("Player Taken");
							i--;
						}
						else{
							int indexName= buffArr.get(playNum).indexOf("Total");
							int indexGuess= buffArr.get(playNum).indexOf("Password");
							int indexGame= buffArr.get(playNum).indexOf("Money")+7;
							int length= buffArr.get(playNum).length();
							do{

								System.out.println("Enter your password, "+buffArr.get(playNum).substring(9, indexName-1));
								String passwordguess= scanner.next();
								if(passwordguess.equals((buffArr.get(playNum).substring(indexGuess+10, length)))==true){
									hh=true;
								}
								else{
									System.out.println("Wrong");
								}
							}while(hh==false);
						}
					}
				}

			}
			if(playNum==-1){
				System.out.println("OK - Let's get your name");
				System.out.print("Please enter a name for Player #"+ (i + 1)+": ");
				String name = scanner.next();
				Player playr = new Player();
				playr.setName(name);
				players.add(playr);
				System.out.print("Please enter a password: ");
				String password= scanner.next();
				players.get(i).setPass(password);
			}
			else if(playNum<buffArr.size()){
				if(buffArr.get(playNum).equals("")==true){
					//System.out.println("Player Taken");
					//i--;
				}
				else{
					Player playr = new Player();
					int indexName= buffArr.get(playNum).indexOf("Total");
					int indexGuess= buffArr.get(playNum).indexOf("Password");
					int indexGame= buffArr.get(playNum).indexOf("Money")+7;
					int length= buffArr.get(playNum).length();
					playr.setName(buffArr.get(playNum).substring(9, indexName-1));
					players.add(playr);
					players.get(i).setTotal(Integer.parseInt(buffArr.get(playNum).substring(indexGame,indexGuess-1)));
					players.get(i).setPass((buffArr.get(playNum).substring(indexGuess+10, length)));
					System.out.println("You chose: "+ players.get(i).getName());
					buffArr.set(playNum,"");
				}
			}
			else{
				System.out.println("invalid input");
				i--;
			}

		}
		for(int j=0; j<numOfPlayers;j++){
			if (j<buffArr.size()){
				if(buffArr.get(j).equals("")==false){
					buffArr.add(j,buffArr.get(j));
				}
			}
		}
		System.out.println("");
		Random rand = new Random();
		int randNum;
		int p;
		randNum= rand.nextInt(36) + 1;
		int amount;
		int choice;
		int number;
		String response = "no";
		do{
			end=false;
			for(int i = 0; i < numOfPlayers; i++){
				do{
					if(players.get(i).getTotal()==0){
						System.out.println("The bank sent you a loan of $500");
						players.get(i).setTotal(players.get(i).getTotal()+500);
					}
					System.out.println("You have: "+players.get(i).getTotal()+" cash remaining");
					System.out.print(players.get(i).getName()+" Enter your bet amount: ");
					amount = readName();
					if(players.get(i).getTotal()>=amount){
						invalid=false;
					}
					else{
						invalid=true;
						System.out.print("Insufficient funds");
					}
				}while(invalid==true);
				players.get(i).setBet(amount);
				System.out.println("0 - Even(1:1 Payout)\n1 - Odd(1:1 Payout)\n2 - Number(35:1 Payout)\n3 - Row(11:1 Payout)\n4 - Column(2:1 Payout)");
				choice = -1;
				int choicex = -1;
				while (choice < 0 || choice > 4)
				{
					System.out.print("Place your bet on: ");
					choice = readName();
					players.get(i).setChoice(choice);
				}
				number = 0;
				if (choice == 2)  
				{
					while (number < 1 || number > 36)
					{
						System.out.print("Place your bet on number(1-36): ");
						number = readName();
						players.get(i).setGuess(number);
					}
				}
				if (choice == 3)  
				{
					while (choicex < 0 || choicex > 11)
					{
						System.out.print("Place your bet on 0 - Row 1(1,2,3)\n1 - Row 2(4,5,6)\n2 - Row 3(7,8,9)\n3 - Row 4(10,11,12)\n4 - Row 5(13,14,15)\n5 - Row 6(16,17,18)\n6 - Row 7(19,20,21)\n7 - Row 8(22,23,24)\n8 - Row 9(25,26,27)\n9 - Row 10(28,29,30)\n10 - Row 11(32,32,33)\n11 - Row 12(34,35,36)");
						choicex = readName();
						players.get(i).setChoicex(choicex);
					}
				}
				if (choice == 4)  
				{
					while (choicex < 0 || choicex > 36)
					{
						System.out.print("Place your bet on 0 - Column 1(1,4,7,10,13,16,19,22,25,28,31,34)\n1 - Column 2(2,5,8,11,14,17,20,23,26,29,32,35)\n2 - Column 3(3,7,9,12,15,18,21,24,27,30,33,36)");
						choicex = readName();
						players.get(i).setChoicex(choicex);
					}
				}
			}
			System.out.println("Roulette number: " + randNum);
			for(int xx = 0; xx < numOfPlayers; xx++){
				if(players.get(xx).getChoice()==0){
					if(randNum%2==0&&players.get(xx).getChoice()==0){
						players.get(xx).setWon(true);
						players.get(xx).setTotal(players.get(xx).getBet()+players.get(xx).getTotal());
					}
				}
				if(players.get(xx).getChoice()==1){
					if(randNum%2!=0){
						players.get(xx).setWon(true);
						players.get(xx).setTotal(players.get(xx).getBet()+players.get(xx).getTotal());
					}
				}
				if(players.get(xx).getChoice()==2){
					if (randNum == players.get(xx).getGuess()){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*36)+players.get(xx).getTotal());
					}
					else{
						players.get(xx).setWon(false);
					}
				}
				if(players.get(xx).getChoice()==4){
					if(players.get(xx).getChoicex()==0&& (randNum==1||randNum==2||randNum==3)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==1&& (randNum==4||randNum==5||randNum==6)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==2&& (randNum==7||randNum==8||randNum==9)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==3&& (randNum==10||randNum==11||randNum==12)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==4&& (randNum==13||randNum==14||randNum==15)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==5&& (randNum==16||randNum==17||randNum==18)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==6&& (randNum==19||randNum==20||randNum==21)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==7&& (randNum==22||randNum==23||randNum==24)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==8&& (randNum==25||randNum==26||randNum==27)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==9&& (randNum==28||randNum==29||randNum==30)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==10&& (randNum==31||randNum==32||randNum==33)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==11&& (randNum==34||randNum==35||randNum==36)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*12)+players.get(xx).getTotal());
					}
				}
				if(players.get(xx).getChoice()==3){
					if(players.get(xx).getChoicex()==0 && (randNum==1||randNum==4||randNum==7||randNum==10||randNum==13||randNum==16||randNum==19||randNum==22||randNum==25||randNum==28||randNum==31||randNum==34)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*3)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==1 &&(randNum==2||randNum==5||randNum==8||randNum==11||randNum==14||randNum==17||randNum==20||randNum==23||randNum==26||randNum==29||randNum==32||randNum==35)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*3)+players.get(xx).getTotal());
					}
					else if(players.get(xx).getChoicex()==2 &&(randNum==3||randNum==7||randNum==9||randNum==12||randNum==15||randNum==18||randNum==21||randNum==24||randNum==27||randNum==30||randNum==33||randNum==36)){
						players.get(xx).setWon(true);
						players.get(xx).setTotal((players.get(xx).getBet()*3)+players.get(xx).getTotal());
					}
				}
			}
			/*for(int g=0; g<numOfPlayers;g++){
				if(g<buffArr.size()){
					players.get(g).numGames();
					buffArr.set(g,"#"+playerArray+++" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
				}
				else{
					players.get(g).numGames();
					buffArr.add(g,"");
					buffArr.set(g,"#"+playerArray+++" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
				}
			}*/

			for(int yy=0;yy<buffArr.size();yy++){
				if(buffArr.get(yy).equals("")==false){
					if(yy<buffArr.size()){
						buffArr.set(yy, buffArr.get(yy).replace(buffArr.get(yy).substring(0,2), "#"+(count+1)));
						count++;
					}
				}	
			}
			for(int y = 0; y < numOfPlayers; y++){
				//Print out game result, win/lose amount
				if (players.get(y).getWon()==true)
				{
					System.out.println("Congratulatons!!!"+players.get(y).getName()+" You win!");
					System.out.println("You have "+players.get(y).getTotal()+" remaining");
				}
				else
				{
					System.out.println(players.get(y).getName()+" you lose. Better luck next time!");
					p=(players.get(y).getTotal()-players.get(y).getBet());
					players.get(y).setTotal(p);
					System.out.println("You have "+players.get(y).getTotal()+" remaining");
				}
			}
			for(int g=0; g<numOfPlayers;g++){
				if(g<buffArr.size()){
					players.get(g).numGames();
					buffArr.set(g,"#"+(playerArray+1)+" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
					playerArray++;
				}
				else{
					players.get(g).numGames();
					buffArr.add(g,"");
					buffArr.set(g,"#"+playerArray+++" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
				}
			}
			for(int y = 0; y < numOfPlayers; y++){

				writer.writeArrs(buffArr);
				if(y==numOfPlayers-1){
					response = readString();
					if (response.equals("yes")) {
						writer.writeArrs(buffArr);
						playRoulette();
					}
					if(response.equals("no")) {
						System.out.println("Thanks for playing  have a great day!!");
						end=true;
					}
					System.out.println("Saving Data File:");
					System.out.println("Players Data saved");
					reader.readData();
					return;
				}
			}

		}while( end==false);

	}
	static void playWar(){
		boolean invalid=true;
		int amount;
		int playNum=0;
		int playerArray= 0;
		int count=0;
		boolean hh=false;
		System.out.println("Welcome to the Roulette Game!");
		buffReader reader= new buffReader();
		ArrayList <String> buffArr= reader.getData();
		reader.readData();
		Scanner userScanner = new Scanner(System.in);
		buffWriter writer = new buffWriter();
		ArrayList<Player> players = new ArrayList<Player>();
		int numOfPlayers;
		System.out.println("Please enter the number of players for this round:");
		numOfPlayers = readInput();
		for(int i = 0; i < numOfPlayers; i++)
		{
			if(buffArr.isEmpty()==true){
				playNum=-1;
			}
			else if(buffArr.isEmpty()==false){
				hh=false;
				System.out.println("Who do you want to play from this list?");
				System.out.print("Please enter a player number for Player #"+ (i + 1) + " (or 0 to enter manually): ");
				playNum = readName()-1;
				if(playNum!=-1){
					if(playNum<buffArr.size()){
						if(buffArr.get(playNum).equals("")==true){
							System.out.println("Player Taken");
							i--;
						}
						else {
							int indexName= buffArr.get(playNum).indexOf("Total");
							int indexGuess= buffArr.get(playNum).indexOf("Password");
							int indexGame= buffArr.get(playNum).indexOf("Money")+7;
							int length= buffArr.get(playNum).length();
							do{

								System.out.println("Enter your password, "+buffArr.get(playNum).substring(9, indexName-1));
								String passwordguess= userScanner.next();
								if(passwordguess.equals((buffArr.get(playNum).substring(indexGuess+10, length)))==true){
									hh=true;
								}
								else{
									System.out.println("Wrong");
								}
							}while(hh==false);
						}
					}
				}

			}
			if(playNum==-1){
				System.out.println("OK - Let's get your name");
				System.out.print("Please enter a name for Player #"+ (i + 1)+": ");
				String name = userScanner.next();
				Player playr = new Player();
				playr.setName(name);
				players.add(playr);
				System.out.print("Please enter a password: ");
				String password= userScanner.next();
				players.get(i).setPass(password);
			}
			else if(playNum<buffArr.size()){
				if(buffArr.get(playNum).equals("")==true){
					//System.out.println("Player Taken");
					//i--;
				}
				else{
					Player playr = new Player();
					int indexName= buffArr.get(playNum).indexOf("Total");
					int indexGuess= buffArr.get(playNum).indexOf("Password");
					int indexGame= buffArr.get(playNum).indexOf("Money")+7;
					int length= buffArr.get(playNum).length();
					playr.setName(buffArr.get(playNum).substring(9, indexName-1));
					players.add(playr);
					players.get(i).setTotal(Integer.parseInt(buffArr.get(playNum).substring(indexGame,indexGuess-1)));
					players.get(i).setPass((buffArr.get(playNum).substring(indexGuess+10, length)));
					System.out.println("You chose: "+ players.get(i).getName());
					buffArr.set(playNum,"");
				}
			}
			else{
				System.out.println("invalid input");
				i--;
			}

		}

		for(int xx=0; xx<numOfPlayers;xx++){
			if (xx<buffArr.size()){
				if(buffArr.get(xx).equals("")==false){
					buffArr.add(xx,buffArr.get(xx));
				}
			}
		}
		for(int i = 0; i < numOfPlayers; i++){
			do{
				if(players.get(i).getTotal()==0){
					System.out.println("The bank sent you a loan of $500");
					players.get(i).setTotal(players.get(i).getTotal()+500);
				}
				System.out.println("You have: "+players.get(i).getTotal()+" cash remaining");
				System.out.println(players.get(i).getName()+" Enter your bet amount: ");
				amount = readName();
				if(players.get(i).getTotal()>=amount){
					invalid=false;
				}
				else{
					invalid=true;
					System.out.println("Insufficient funds");
				}
			}while(invalid==true);;
			players.get(i).setBet(amount);
		}
		System.out.println("");
		String response = "no";

		int i = 1; 
		int dealer = 0;
		//ArrayList numPlayers = new ArrayList();
		while (i != 0)   {
			System.out.println("Round " + i + " of The War");
			dealer = (int)(Math.random() * 12) + 2; 
			for (int xx = 0; xx < numOfPlayers; xx++) {
				players.get(xx).setRandx();

			}



			for (int x = 0; x < numOfPlayers; x++) {
				if(players.get(x).getRandx() == 11) {
					System.out.println(players.get(x).getName() + " draws: Jack");
				}
				else if(players.get(x).getRandx() == 12) {
					System.out.println(players.get(x).getName() + " draws: Queen");
				}
				else if(players.get(x).getRandx() == 13) {
					System.out.println(players.get(x).getName()+ " draws: King");
				}
				else if(players.get(x).getRandx() == 14) {
					System.out.println(players.get(x).getName() + " draws: Ace");
				}
				else {
					System.out.println(players.get(x).getName() + " draws: " + players.get(x).getRandx());
				}
			}
			if(dealer == 11) {
				System.out.println("Dealer draws: Jack");
			}
			else if(dealer == 12) {
				System.out.println("Dealer draws: Queen");
			}
			else if(dealer == 13) {
				System.out.println("Dealer draws: King");
			}
			else if(dealer == 14) {
				System.out.println("Dealer draws: Ace");
			}
			else {
				System.out.println("Dealer draws: " + dealer);
			}
			for(int yy=0;yy<buffArr.size();yy++){
				if(buffArr.get(yy).equals("")==false){
					if(yy<buffArr.size()){
						buffArr.set(yy, buffArr.get(yy).replace(buffArr.get(yy).substring(0,2), "#"+(count+1)));
						count++;
					}
				}	
			}
			for (int z = 0; z < numOfPlayers; z++) {
				if (dealer == players.get(z).getRandx()) {
					System.out.println(players.get(z).getName()+": The War Is A Tie");
					System.out.println(players.get(z).getTotal()+" Total Cash");
				}
				if (dealer > players.get(z).getRandx()) {
					System.out.println(players.get(z).getName()+ ": Dealer Wins The War");
					players.get(z).setTotal(players.get(z).getTotal()-players.get(z).getBet());
					System.out.println(players.get(z).getTotal()+" Total Cash Remaining");
				}
				if (players.get(z).getRandx() > dealer) {
					System.out.println(players.get(z).getName()+" Wins The War");
					players.get(z).setTotal(players.get(z).getTotal()+players.get(z).getBet());
					System.out.println(players.get(z).getTotal()+" Total Cash Remaining");
				}
			}
			for(int g=0; g<numOfPlayers;g++){
				if(g<buffArr.size()){
					buffArr.set(g,"#"+(playerArray+1)+" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
					playerArray++;
				}
				else{
					buffArr.add(g,"");
					buffArr.set(g,"#"+playerArray+++" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
				}
			}
			for(int y = 0; y < numOfPlayers; y++){
				writer.writeArrs(buffArr);
				if(y==numOfPlayers-1){
					response = readString();
					if (response.equals("yes")) {
						writer.writeArrs(buffArr);
						playWar();

					}
					if(response.equals("no")) {
						System.out.println("Thanks for playing  have a great day!!");
						System.out.println("Saving Data File:");
						System.out.println(count+" players to be saved");
						reader.readData();
						return;		
					}

					return;
				}
			}
			System.out.println("Saving Data File:");
			System.out.println("Player data saved");
			reader.readData();

		}
	} 
	static void playSlots(){
		boolean end=false;
		int playNum=0;
		boolean invalid=true;
		boolean hh=false;
		String input="No";
		boolean run = true;
		boolean lost;
		Random rand = new Random();
		System.out.println("Welcome to the Slot Machine Game!");
		System.out.println("Get three 7s to hit the jackpot");
		System.out.println("Garaunteed money as long as you get one 7");
		buffReader reader= new buffReader();
		String response = "no";
		ArrayList <String> buffArr= reader.getData();
		reader.readData();
		int amount;
		Scanner scanner = new Scanner(System.in);
		buffWriter writer = new buffWriter();
		ArrayList<Player> players = new ArrayList<Player>();
		int numOfPlayers=1;
		int playerArray= 0;
		for(int i = 0; i < numOfPlayers; i++)
		{
			if(buffArr.isEmpty()==true){
				playNum=-1;
			}
			else if(buffArr.isEmpty()==false){
				hh=false;
				System.out.println("Who do you want to play from this list?");
				System.out.print("Please enter a player number (or 0 to enter manually): ");
				playNum = readName()-1;
				if(playNum!=-1){
					if(playNum<buffArr.size()){
						if(buffArr.get(playNum).equals("")==true){
							System.out.println("Player Taken");
							i--;
						}
						else{
							int indexName= buffArr.get(playNum).indexOf("Total");
							int indexGuess= buffArr.get(playNum).indexOf("Password");
							int indexGame= buffArr.get(playNum).indexOf("Money")+7;
							int length= buffArr.get(playNum).length();
							do{

								System.out.println("Enter your password, "+buffArr.get(playNum).substring(9, indexName-1));
								String passwordguess= scanner.next();
								if(passwordguess.equals((buffArr.get(playNum).substring(indexGuess+10, length)))==true){
									hh=true;
								}
								else{
									System.out.println("Wrong");
								}
							}while(hh==false);
						}
					}
				}

			}
			if(playNum==-1){
				System.out.println("OK - Let's get your name");
				System.out.print("Please enter a name: ");
				String name = scanner.next();
				Player playr = new Player();
				playr.setName(name);
				players.add(playr);
				System.out.print("Please enter a password: ");
				String password= scanner.next();
				players.get(i).setPass(password);
			}
			else if(playNum<buffArr.size()){
				if(buffArr.get(playNum).equals("")==true){
					//System.out.println("Player Taken");
					//i--;
				}
				else{
					Player playr = new Player();
					int indexName= buffArr.get(playNum).indexOf("Total");
					int indexGuess= buffArr.get(playNum).indexOf("Password");
					int indexGame= buffArr.get(playNum).indexOf("Money")+7;
					int length= buffArr.get(playNum).length();
					playr.setName(buffArr.get(playNum).substring(9, indexName-1));
					players.add(playr);
					players.get(i).setTotal(Integer.parseInt(buffArr.get(playNum).substring(indexGame,indexGuess-1)));
					players.get(i).setPass((buffArr.get(playNum).substring(indexGuess+10, length)));
					System.out.println("You chose: "+ players.get(i).getName());
					buffArr.set(playNum,"");
				}
			}
			else{
				System.out.println("invalid input");
				i--;
			}

		}
		for(int j=0; j<numOfPlayers;j++){
			if (j<buffArr.size()){
				if(buffArr.get(j).equals("")==false){
					buffArr.add(j,buffArr.get(j));
				}
			}
		}
		for(int i = 0; i < numOfPlayers; i++){
			do{
				if(players.get(i).getTotal()==0){
					System.out.println("The bank sent you a loan of $500");
					players.get(i).setTotal(players.get(i).getTotal()+500);
				}
				System.out.println("You have: "+players.get(i).getTotal()+" cash remaining");
				System.out.println(players.get(i).getName()+" Enter your bet amount: ");
				amount = readName();
				if(players.get(i).getTotal()>=amount){
					invalid=false;
				}
				else{
					invalid=true;
					System.out.println("Insufficient funds");
				}
			}while(invalid==true);;
			players.get(i).setBet(amount);
		}
		do{
			int rNum1 = rand.nextInt(7)+1;
			int rNum2 = rand.nextInt(7)+1;
			int rNum3 = rand.nextInt(7)+1;

			System.out.println(rNum1 + "|" + rNum2 + "|" + rNum3);

			//User hits three sevens
			if (rNum1 == 7 && rNum2 == 7 && rNum3 == 7) {
				System.out.println("You hit the jackpot and have won the round!!!");
				System.out.println("You have won " + (players.get(0).getBet()*6) + " credits");
				players.get(0).setTotal(players.get(0).getTotal()+players.get(0).getBet()*6);
				System.out.println("You have "+players.get(0).getTotal() +" credits remaining");
				//p.credits += p.bet * 6;
				//System.out.println("You have won " + (p.bet * 6) + " credits");
				//p.numTimesWon++;
				lost = false;
			}
			//User hits two sevens
			else if ((rNum1 == 7 && rNum2 == 7) ||
					(rNum2 == 7 && rNum3 == 7) ||
					(rNum3 == 7 && rNum1 == 7)) {
				System.out.println("You only hit two numbers for a minor payout!!");
				System.out.println("You have won " + (players.get(0).getBet()*4) + " credits");
				players.get(0).setTotal(players.get(0).getTotal()+players.get(0).getBet()*4);
				System.out.println("You have "+players.get(0).getTotal() +" credits remaining");
				//p.credits += p.bet *4;
				//System.out.println("You have won " + (p.bet * 4) + " credits");
				//p.numTimesWon++;
				lost = false;
			}
			//User hit one seven
			else if (rNum1 == 7 || rNum2 == 7 || rNum3 == 7) {
				System.out.println("You won a few credits!");
				System.out.println("You have won " + (players.get(0).getBet()*2) + " credits");
				players.get(0).setTotal(players.get(0).getTotal()+players.get(0).getBet()*2);
				System.out.println("You have "+players.get(0).getTotal() +" credits remaining");
				//p.credits += p.bet * 2;
				//System.out.println("You have won " + (p.bet * 2) + " credits");
				//p.numTimesWon++;
				lost = false;
			}
			else {
				System.out.println("You did not win anything.");
				System.out.println("You have lost " + (players.get(0).getBet()) + " credits");
				players.get(0).setTotal(players.get(0).getTotal()-players.get(0).getBet());
				System.out.println("You have "+players.get(0).getTotal() +" credits remaining");
				//p.credits -=p.bet;
				//p.numTimesLost++;
				lost = true;
			}
			for(int g=0; g<numOfPlayers;g++){
				if(g<buffArr.size()){
					players.get(g).numGames();
					buffArr.set(g,"#"+(playerArray+1)+" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
					playerArray++;
				}
				else{
					players.get(g).numGames();
					buffArr.add(g,"");
					buffArr.set(g,"#"+playerArray+++" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
				}
			}
			for(int g=0; g<numOfPlayers;g++){
				if(g<buffArr.size()){
					players.get(g).numGames();
					buffArr.set(g,"#"+(playerArray+1)+" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
					playerArray++;
				}
				else{
					players.get(g).numGames();
					buffArr.add(g,"");
					buffArr.set(g,"#"+playerArray+++" Name: "+players.get(g).getName()+" Total Money: "+
							players.get(g).getTotal()+" Password: "+players.get(g).getPass());
				}
			}
			for(int y = 0; y < numOfPlayers; y++){

				writer.writeArrs(buffArr);
				if(y==numOfPlayers-1){
					response = readString();
					if (response.equals("yes")) {
						writer.writeArrs(buffArr);
						playSlots();
					}
					if(response.equals("no")) {
						System.out.println("Thanks for playing  have a great day!!");
						end=true;
					}
					System.out.println("Saving Data File:");
					System.out.println("Players Data saved");
					reader.readData();
					return;
				}
			}
		}while(run == true);

	}
}


