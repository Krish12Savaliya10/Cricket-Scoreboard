import java.util.*;
class NmaeException extends Exception{
    public NmaeException(String message) {
        super(message);
    }
}
class InvalidTypeException extends Exception{
    public InvalidTypeException(String message) {
        super(message);
    }
}
class InvalidTossException extends Exception{
    public InvalidTossException(String message) {
        super(message);
    }
}
class InvalidoverException extends Exception{
    public InvalidoverException(String message) {
        super(message);
    }
}
class InvalidChoiceException extends Exception{
    public InvalidChoiceException(String message) {
        super(message);
    }
}
class InvalidExtraRunException extends Exception{
    public InvalidExtraRunException(String message) {
        super(message);
    }
}



class players {
    Scanner sc = new Scanner(System.in);
    String PlayerName;
    int runs = 0;
    int balls = 0;
    int overs = 0;
    int wicket = 0;
    int Bowlerrun = 0;
    int six=0;
    int four=0;
    char strick = '*';
    int allreadyOut=0;


    players(int i) {
        get(i);
    }

    void get(int i) {
        System.out.print("Enter player " + i + " name: ");
        boolean NameCheck=true;
        while(NameCheck) {
            try {
                PlayerName = sc.nextLine().toUpperCase();
                if(PlayerName.trim().isEmpty())
                    throw new NmaeException("Enter name!!");
                else if (PlayerName.length()>16)
                  throw new NmaeException("Cherecer must be less then 16");
                NameCheck=false;
            }
            catch (NmaeException e){
                System.out.println(e.getMessage());
            }
        }
    }

}

class Batsman extends players {
    Batsman(int i) {
        super(i);

    }
}

class Bowler extends players {
    Bowler(int i) {
        super(i);
    }
}

class Allrounder extends players {
    Allrounder(int i) {
        super(i);
    }
}

class Team {
    Scanner sc = new Scanner(System.in);
    int totalrun = 0;
    int overplayed = 0;
    int wicketdown = 0;
    String teamName = "";
    int extrarun;


    void get(int i) {
        System.out.println("Enter team " + i + " name: ");
        boolean NameCheck=true;
        while(NameCheck) {
            try {
                teamName = sc.nextLine().toUpperCase();
                if(teamName.trim().isEmpty())
                    throw new NmaeException("Enter name!!");
                else if (teamName.length()>16)
                    throw new NmaeException("Cherecer must be less then 16");
                NameCheck=false;
            }
            catch (NmaeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    int extra(){
        System.out.println("Enter Extra runs");
        boolean checkExtra=true;
        while (checkExtra) {
            try {
                extrarun = sc.nextInt();
               if(extrarun<0 || extrarun>6 || extrarun==5) {
                   throw new InvalidExtraRunException("Entre NUMERIC value 0,1,2,3,4,6 only");
               }
                checkExtra=false;
            }
            catch (InvalidExtraRunException e) {
                System.out.println();
            } catch (RuntimeException e) {
                System.out.println("Enter NUMERIC value only!!");
            }
        }
        return extrarun;
    }
}

class runcount {
    Team team1, team2;
    players[] team1Players, team2Players;
    void startMatch() {
        int i , j = 0;
        int s , k , b ;
        int choice=0;
        String curantBowler ;
        String newBatsman ;
        int extraruns;
        Scanner sc = new Scanner(System.in);

        int type=0;

        team1 = new Team();
        team1.get(1);
        team2 = new Team();
        team2.get(2);


        team1Players = new players[11];
        System.out.println("Enter " + team1.teamName + " players details:");
        for (int x = 0; x < 11; x++) {
            boolean typeCheck=true;

            while (typeCheck) {
                System.out.println("Enter player type (1: Batsman, 2: Bowler, 3: Allrounder): ");
                try {
                    type = sc.nextInt();
                    if(type<1 || type>3){
                        throw new InvalidTypeException("Plese Enter NUMERIC value 1/2/3 only!!");
                    }
                    typeCheck=false;
                }
                catch (InvalidTypeException e){
                    System.out.println(e.getMessage());
                }
                catch (RuntimeException e) {
                    System.out.println("Plese Enter NUMERIC value 1/2/3 only!!");
                }
                sc.nextLine();
            }

            switch (type) {
                case 1:
                    team1Players[x] = new Batsman(x+1);
                    break;

                case 2:
                    team1Players[x] = new Bowler(x+1);
                    break;

                case 3:
                    team1Players[x] = new Allrounder(x+1);
                    break;

                default:
                    team1Players[x] = new Batsman(x+1);
            }
            System.out.println("---------*---------");
        }

        team2Players = new players[11];
        System.out.println("Enter " + team2.teamName + " players details:");
        for (int x = 0; x < 11; x++) {
            boolean typeCheck=true;

            while (typeCheck) {
                System.out.println("Enter player type (1: Batsman, 2: Bowler, 3: Allrounder): ");
                try {
                    type = sc.nextInt();
                    if(type<1 || type>3){
                        throw new InvalidTypeException("Plese Enter NUMERIC value 1/2/3 only!!");
                    }
                    typeCheck=false;
                }
                catch (InvalidTypeException e){
                    System.out.println(e.getMessage());
                }
                catch (RuntimeException e) {
                    System.out.println("Plese Enter NUMERIC value 1/2/3 only!!");
                }
                sc.nextLine();
            }
            switch (type) {
                case 1: team2Players[x] = new Batsman(x+1);
                break;

                case 2: team2Players[x] = new Bowler(x+1);
                break;

                case 3: team2Players[x] = new Allrounder(x+1);
                break;

                default:
                    team2Players[x] = new Batsman(x+1);
            }
            System.out.println("---------*---------");
        }

        players Batsman1 = null;
        players Batsman2 = null;
        players Bowler = null;
        Team playingTeam ;

        System.out.println("------" + team1.teamName + " Vs " + team2.teamName + "------");
        CricketMatch.print("Toss time");
        System.out.println(team1.teamName+" Enter 0 for HEAD and 1 for TAIL: ");

        int Toss = -1;
        boolean tossCheck=true;
       while(tossCheck){
           try{
               Toss=sc.nextInt();

               if(Toss !=0 && Toss !=1) {
                   throw new InvalidTossException("Enter only NUMERIC value 0 for HEAD or 1 for TAIL");
               }
               tossCheck=false;
           }
           catch (InvalidTossException e){
               System.out.println(e.getMessage());
           } catch (RuntimeException e) {
               System.out.println("Enter only NUMERIC value 0 for HEAD or 1 for TAIL");
           }
           sc.nextLine();
       }


        if (Toss == 0) {
            System.out.println(team1.teamName + " call for HEAD");
        } else {
            System.out.println(team1.teamName + " call for TAIL");
        }

        int Coin = (int) (Math.random()*2+1);
        boolean tossvalidetion=true;
        if (Toss == Coin) {
            CricketMatch.print(team1.teamName + " won the toss");
            System.out.println("What would you decide [BAT/BALL]?");
            System.out.println("Enter 1 for BAT and 2 for BALL");
            while(tossvalidetion){
                try {
                    choice = sc.nextInt();
                    if (choice == 1){
                        System.out.println(team1.teamName + " decided to BAT first");
                        tossvalidetion=false;
                    }
                    else if(choice==2){
                        System.out.println(team1.teamName + " decided to BALL first");
                        tossvalidetion=false;
                    }
                    else
                        throw new InvalidChoiceException("Enter NUMERIC value 1 or 2!!");
                }
                catch (InvalidChoiceException e){
                    System.out.println(e.getMessage());
                }
                catch (RuntimeException e){
                    System.out.println("Enter NUMERIC value 1/2 only");
                }
                sc.nextLine();

            }
        }

        else {
            CricketMatch.print(team2.teamName + " won the toss");
            System.out.println("What would you decide [BAT/BALL]?");
            System.out.print("Enter 1 for BAT and 2 for BALL :");
            while(tossvalidetion){
                try {
                    choice = sc.nextInt();
                    if (choice == 1){
                        System.out.println(team1.teamName + " decided to BAT first");
                        tossvalidetion=false;
                    }
                    else if(choice==2){
                        System.out.println(team1.teamName + " decided to BALL first");
                        tossvalidetion=false;
                    }
                    else
                        throw new InvalidChoiceException("Enter NUMERIC value 1 or 2!!");
                }
                catch (InvalidChoiceException e){
                    System.out.println(e.getMessage());
                }
                catch (RuntimeException e){
                    System.out.println("Enter NUMERIC value 1/2 only");
                }
                sc.nextLine();

            }

        }
        System.out.println();
        int over = 0;
        boolean checkOver=true;
        System.out.println("Enter number of over");
        while(checkOver) {
            try {
                over = sc.nextInt();
                if (over < 5 || over > 50)
                    throw new InvalidoverException("Over must be in range of(5 to 50)!!");
                checkOver = false;
            } catch (InvalidoverException e) {
                System.out.println(e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Enter only NUMERIC value between 5 to 50!!");
            }
            sc.nextLine();
        }
        boolean TossChoice=((Toss==Coin) && (choice==1)) || ((Toss!=Coin)&&(choice!=1));

        if(TossChoice) {
            System.out.println("Batting team is "+team1.teamName);
            System.out.println("Balling team is "+team2.teamName);


            while (Batsman1 == null) {
                System.out.print("Enter opener 1 name: ");
                String select = sc.nextLine().toUpperCase();
                for (s = 0; s < 11; s++) {
                    if (team1Players[s].PlayerName.equals(select)) {
                        Batsman1 = team1Players[s];
                        break;
                    }
                }
                if (Batsman1 == null) {
                    System.out.println("Player not found. Please try again.");
                }
            }


            while (Batsman2 == null) {
                System.out.print("Enter opener 2 name: ");
                String Select = sc.nextLine().toUpperCase();
                for (k = 0; k < 11; k++) {
                    if (team1Players[k].PlayerName.equals(Select)) {
                        Batsman2 = team1Players[k];
                        Batsman2.strick = '-';
                        break;
                    }
                }
                if (Batsman2 == null) {
                    System.out.println("Player not found. Please try again.");
                }
            }


            while (Bowler == null) {
                System.out.print("Enter Bowler name: ");
                curantBowler = sc.nextLine().toUpperCase();
                for (b = 0; b < 11; b++) {
                    if (team2Players[b].PlayerName.equals(curantBowler)) {
                        Bowler = team2Players[b];
                        break;
                    }
                }
                if (Bowler == null) {
                    System.out.println("Player not found. Please try again.");
                }
            }
            playingTeam = team1;

        }
        else {
            System.out.println("Batting team is "+team2.teamName);
            System.out.println("Balling team is "+team1.teamName);


            while (Batsman1 == null) {
                System.out.print("Enter opener 1 name: ");
                String select = sc.nextLine().toUpperCase();
                for (s = 0; s < 11; s++) {
                    if (team2Players[s].PlayerName.equals(select)) {
                        Batsman1 = team2Players[s];
                        break;
                    }
                }
                if (Batsman1 == null) {
                    System.out.println("Player not found. Please try again.");
                }
            }


            while (Batsman2 == null) {
                System.out.print("Enter opener 2 name: ");
                String Select = sc.nextLine().toUpperCase();
                for (k = 0; k < 11; k++) {
                    if (team2Players[k].PlayerName.equals(Select)) {
                        Batsman2 = team2Players[k];
                        Batsman2.strick = '-';
                        break;
                    }
                }
                if (Batsman2 == null) {
                    System.out.println("Player not found. Please try again.");
                }
            }


            while (Bowler == null) {
                System.out.print("Enter Bowler name: ");
                curantBowler = sc.nextLine().toUpperCase();
                for (b = 0; b < 11; b++) {
                    if (team1Players[b].PlayerName.equals(curantBowler)) {
                        Bowler = team1Players[b];
                        break;
                    }
                }
                if (Bowler == null) {
                    System.out.println("Player not found. Please try again.");
                }
            }
            playingTeam = team2;
        }

        char temp = '*';
        int inning = 0;
        do {
            String run="";
            inning++;
            outerloop:
            for (i = 0; i < over; i++) {
                for (j = 0; j <= 5; j++) {
                    System.out.println("-----------------");
                    System.out.println(playingTeam.teamName);
                    System.out.println("("+playingTeam.totalrun+"/"+playingTeam.wicketdown+")          OVER: ("+i+"."+j+")");
                    if (i == 0 && j == 0) {
                        System.out.println("CRR: 0.0");
                    } else {
                        System.out.println("CRR: "+String.format("%.2f", (playingTeam.totalrun * 6.0) / (i * 6 + j)));
                    }
                    System.out.println();
                    System.out.println("Batter");
                    if (Batsman1.strick == '*') {
                        System.out.println(Batsman1.PlayerName+"*         "+Batsman1.runs+"("+Batsman1.balls+")     4's("+Batsman1.four+")"+"   6's("+Batsman1.six+")");
                        System.out.println(Batsman2.PlayerName+"          "+Batsman2.runs+"("+Batsman2.balls+")     4's("+Batsman2.four+")"+"   6's("+Batsman2.six+")");
                    } else {
                        System.out.println(Batsman1.PlayerName+"          "+Batsman1.runs+"("+Batsman1.balls+")     4's("+Batsman1.four+")"+"   6's("+Batsman1.six+")");
                        System.out.println(Batsman2.PlayerName+"*         "+Batsman2.runs+"("+Batsman2.balls+")     4's("+Batsman2.four+")"+"   6's("+Batsman2.six+")");
                    }
                    System.out.println();
                    System.out.println("Bowler");
                    System.out.println(Bowler.PlayerName+"          "+Bowler.wicket+"-"+Bowler.Bowlerrun+" ("+Bowler.overs+"."+j+")");

                    System.out.println("-----------------\n");
                    System.out.println("Enter run");
                    System.out.print("0 1 2 3 4 6 \nEnter N/n for <noball> W/w for <wide> and O/o for <out> Enter e/E for Extrarun\nEnter: ");
                    run = sc.nextLine().toUpperCase();
                    System.out.println();
                    switch (run) {
                        case "0":
                            if (Batsman1.strick == '*') {
                                Batsman1.balls++;
                            }
                            else {
                                Batsman2.balls++;
                            }
                            break;
                        case "1":
                            playingTeam.totalrun++;
                            Bowler.Bowlerrun++;
                            if (Batsman1.strick == '*') {
                                Batsman1.runs++;
                                Batsman1.balls++;
                            } else {
                                Batsman2.runs++;
                                Batsman2.balls++;
                            }
                            char tempStrick = Batsman1.strick;
                            Batsman1.strick = Batsman2.strick;
                            Batsman2.strick = tempStrick;
                            break;
                        case "2":


                            playingTeam.totalrun += 2;
                            Bowler.Bowlerrun += 2;
                            if (Batsman1.strick == '*') {
                                Batsman1.runs += 2;
                                Batsman1.balls++;
                            } else {
                                Batsman2.runs += 2;
                                Batsman2.balls++;
                            }
                            break;
                        case "3":
                            playingTeam.totalrun += 3;
                            Bowler.Bowlerrun += 3;
                            if (Batsman1.strick == '*') {
                                Batsman1.runs += 3;
                                Batsman1.balls++;
                            } else {
                                Batsman2.runs += 3;
                                Batsman2.balls++;
                            }
                            temp = Batsman1.strick;
                            Batsman1.strick = Batsman2.strick;
                            Batsman2.strick = temp;
                            break;
                        case "4":
                            playingTeam.totalrun += 4;
                            Bowler.Bowlerrun += 4;
                            if (Batsman1.strick == '*') {
                                Batsman1.four ++;
                                Batsman1.runs += 4;
                                Batsman1.balls++;
                            } else {
                                Batsman2.runs += 4;
                                Batsman2.four ++;
                                Batsman2.balls++;
                            }
                            break;
                        case "6":
                            playingTeam.totalrun += 6;
                            Bowler.Bowlerrun += 6;
                            if (Batsman1.strick == '*') {
                                Batsman1.runs += 6;
                                Batsman1.six ++;
                                Batsman1.balls++;
                            } else {
                                Batsman2.runs += 6;
                                Batsman2.six ++;
                                Batsman2.balls++;
                            }
                            break;
                        case "N":
                            System.out.println("No Ball!");
                            System.out.println("Enter run in noball: ");
                            int noball = sc.nextInt();
                            sc.nextLine();
                            playingTeam.totalrun += (1 + noball);
                            Bowler.Bowlerrun += (1 + noball);
                            if (Batsman1.strick == '*') {
                                Batsman1.runs += noball;
                                Batsman1.balls++;
                                if(noball==6)
                                    Batsman1.six ++;
                                else if(noball==4)
                                    Batsman1.four ++;
                            } else {
                                Batsman2.runs += noball;
                                Batsman2.balls++;
                                if(noball==6)
                                    Batsman2.six ++;
                                else if(noball==4)
                                    Batsman2.four ++;
                            }
                            if (noball % 2 == 1) {
                                temp = Batsman1.strick;
                                Batsman1.strick = Batsman2.strick;
                                Batsman2.strick = temp;
                            }
                            j--;
                            System.out.println("Free Hit! Free Hit! do not enter o/O");
                            break;

                        case "W":
                            System.out.println("Wide!");
                            System.out.println("Enter e/E for extra run in wide otherwise enter n/N");
                            char wideExtras = Character.toUpperCase(sc.next().charAt(0));
                            if(wideExtras=='E'){
                                extraruns=playingTeam.extra();
                                if (extraruns % 2 == 1) {
                                    temp = Batsman1.strick;
                                    Batsman1.strick = Batsman2.strick;
                                    Batsman2.strick = temp;
                                }
                            }
                            else
                                extraruns=0;
                            playingTeam.totalrun += (1+extraruns);
                            Bowler.Bowlerrun+=(1+extraruns);
                            playingTeam.extrarun += extraruns;
                            j--;
                            break;

                        case "O":
                            System.out.println("Wicket!");
                            playingTeam.wicketdown++;
                            Bowler.wicket++;
                            if (playingTeam.wicketdown == 10) {
                                break outerloop;
                            }
                            sc.nextLine();
                            if (Batsman1.strick == '*')
                                Batsman1.allreadyOut++;

                            else
                                Batsman2.allreadyOut++;
                            boolean validPlayer = false;
                            while (!validPlayer) {
                                System.out.println("Enter new Player Name:");
                                newBatsman = sc.nextLine().toUpperCase();
                                players[] playersList = (playingTeam == team1) ? team1Players : team2Players;
                                for (s = 0; s < 11; s++) {
                                    if (playersList[s].PlayerName.equals(newBatsman)) {
                                        if(playersList[s].allreadyOut ==0){
                                            if (Batsman1.strick == '*') {
                                                Batsman1 = playersList[s];
                                            } else {
                                                Batsman2 = playersList[s];
                                            }
                                            validPlayer = true;
                                            break;
                                        }
                                    }
                                    else
                                        System.out.println("Player already out.");

                                }
                                if (!validPlayer) {
                                    System.out.println("Player not found! Please enter a valid player name.");
                                }
                            }
                            break;

                        case "E":
                            extraruns=playingTeam.extra();
                            playingTeam.extrarun +=extraruns;
                            playingTeam.totalrun += extraruns;
                            Bowler.Bowlerrun += extraruns;
                            if (extraruns % 2 == 1) {
                                temp = Batsman1.strick;
                                Batsman1.strick = Batsman2.strick;
                                Batsman2.strick = temp;
                            }
                            break;

                        default:
                            System.out.println("Enter valid input");
                            j--;
                            break;
                    }

                    if (inning == 2) {
                        if (playingTeam == team2 && playingTeam.totalrun > team1.totalrun) {
                            break outerloop;
                        } else if (playingTeam == team1 && playingTeam.totalrun > team2.totalrun) {
                            break outerloop;
                        }
                    }
                }

                temp = Batsman1.strick;
                Batsman1.strick = Batsman2.strick;
                Batsman2.strick = temp;
                Bowler.overs++;
                if(!run.equals("0")){
                    sc.nextLine();
                }

                if (i != over - 1) {
                    System.out.println("Enter new Bowler Name:");

                    boolean validBowler = false;
                    while (!validBowler) {
                        curantBowler = sc.nextLine().toUpperCase();
                        if (playingTeam == team1) {
                            for (b = 0; b < 11; b++) {
                                if (team2Players[b].PlayerName.equals(curantBowler)) {
                                    if(team2Players[b]!=Bowler){
                                        Bowler = team2Players[b];
                                        validBowler = true;
                                        break;
                                    }
                                    else
                                        System.out.println("Plese change the curant bowler!");
                                }
                                else
                                    System.out.println("Bowler not found!");
                            }
                        }
                        else {
                            for (b = 0; b < 11; b++) {
                                if (team1Players[b].PlayerName.equals(curantBowler)) {
                                    if(team1Players[b]!=Bowler){
                                        Bowler = team1Players[b];
                                        validBowler = true;
                                        break;
                                    }
                                    else
                                        System.out.print("Plese change the curant bowler!");
                                }
                                else
                                    System.out.print("Bowler not found!");
                            }
                        }
                        if (!validBowler) {
                            System.out.println("Please enter a valid bowler name.");
                            curantBowler = sc.nextLine().toUpperCase();
                        }
                    }
                }

                playingTeam.overplayed++;
            }
            System.out.println("------Inning complet------");
            System.out.println(playingTeam.teamName);
            System.out.println("("+playingTeam.totalrun+"/"+playingTeam.wicketdown+")          OVER:("+playingTeam.overplayed+".0)");
            System.out.println("CRR:"+(playingTeam.totalrun/over));
            System.out.println();
            System.out.println("Batter");
            if (Batsman1.strick == '*') {
                System.out.println(Batsman1.PlayerName+"*         "+Batsman1.runs+"("+Batsman1.balls+")     4's("+Batsman1.four+")"+"   6's("+Batsman1.six+")");
                System.out.println(Batsman2.PlayerName+"          "+Batsman2.runs+"("+Batsman2.balls+")     4's("+Batsman2.four+")"+"   6's("+Batsman2.six+")");
            }
            else {
                System.out.println(Batsman1.PlayerName+"          "+Batsman1.runs+"("+Batsman1.balls+")     4's("+Batsman1.four+")"+"   6's("+Batsman1.six+")");
                System.out.println(Batsman2.PlayerName+"*         "+Batsman2.runs+"("+Batsman2.balls+")     4's("+Batsman2.four+")"+"   6's("+Batsman2.six+")");
            }
            System.out.println();
            System.out.println("Bowler");
            System.out.println(Bowler.PlayerName+"          "+Bowler.wicket+"-"+Bowler.Bowlerrun+" ("+Bowler.overs+"."+j+")");


            if(inning<2){
                System.out.println();
                System.out.println("------Lat's Start next inning------");
                System.out.println("Target : "+(playingTeam.totalrun+1));
                if(playingTeam==team1){
                    team1=playingTeam;
                    playingTeam=team2;
                }
                else{
                    team2=playingTeam;
                    playingTeam=team1;
                }
                if(playingTeam==team1){
                    System.out.println("Batting team is "+team1.teamName);
                    System.out.println("Ballingteam is "+team2.teamName);
                }
                else{
                    System.out.println("Batting team is "+team2.teamName);
                    System.out.println("Ballingteam is "+team1.teamName);
                }

                if(TossChoice)
                {
                    sc.nextLine();

                    System.out.print("enter opener 1 name:");
                    String select=sc.nextLine();
                    for(s=0;s<11;s++){
                        if(team2Players[s].PlayerName.equals(select.toUpperCase())){
                            Batsman1=team2Players[s];

                        }
                    }
                    System.out.print("enter opener 2 name:");
                    String Select=sc.nextLine();
                    for(k=0;k<11;k++){
                        if(team2Players[k].PlayerName.equals(Select.toUpperCase())){
                            Batsman2=team2Players[k];
                            Batsman2.strick='-';
                        }
                    }

                    System.out.print("enter Bowler name:");
                    curantBowler=sc.nextLine().toUpperCase();
                    for(b=0;b<11;b++){
                        if(team1Players[b].PlayerName.equals(curantBowler.toUpperCase())){
                            Bowler=team1Players[b];
                        }
                    }

                }
                else{
                    sc.nextLine();
                    System.out.print("enter opener 1 name");
                    String select=sc.nextLine();
                    for(s=0;s<11;s++){
                        if(team1Players[s].PlayerName.equals(select.toUpperCase())){
                            Batsman1=team1Players[s];

                        }
                    }
                    System.out.print("enter opener 2 name");
                    String Select=sc.nextLine();
                    for(k=0;k<11;k++){
                        if(team1Players[k].PlayerName.equals(Select.toUpperCase())){
                            Batsman2=team1Players[k];
                            Batsman2.strick='-';
                        }

                    }
                    System.out.print("enter Bowler name:");
                    curantBowler=sc.nextLine().toUpperCase();
                    for(b=0;b<11;b++){
                        if(team2Players[b].PlayerName.equals(curantBowler.toUpperCase())){
                            Bowler=team2Players[b];
                        }
                    }


                }

            }

        }while(inning<2);
        System.out.println();
        System.out.println("------------");
        System.out.println("Match result:");
        if(playingTeam==team2){
            if(team1.totalrun < playingTeam.totalrun)
                CricketMatch.print(team2.teamName+" is won dy "+(10-playingTeam.wicketdown)+" wicket");
            else if(team1.totalrun == playingTeam.totalrun)
                CricketMatch.print("Match tie");
            else
                CricketMatch.print(team1.teamName+" is won dy "+(team1.totalrun-playingTeam.totalrun)+" runs");
        }
        else{
            if(team2.totalrun < playingTeam.totalrun)
                CricketMatch.print(team1.teamName+" is won dy "+(10-playingTeam.wicketdown)+" wicket");

            else if(team1.totalrun == playingTeam.totalrun)
                CricketMatch.print("Match tie");
            else{
                CricketMatch.print(team2.teamName+" is won dy "+(team1.totalrun-playingTeam.totalrun)+" runs");
            }
        }


    }

    void searchPlayer(String teamName, String playerName) {
        players[] playersList = teamName.equalsIgnoreCase(team1.teamName) ? team1Players : team2Players;
        for (players player : playersList) {

            if (player.PlayerName.equalsIgnoreCase(playerName)) {
                System.out.println("---------------------");
                System.out.println("Player Name: " + player.PlayerName);
                System.out.println("Runs: " + player.runs);
                System.out.println("Balls: " + player.balls);
                System.out.println("Strike Rate: " + (player.runs * 100.0 / player.balls));
                System.out.println("Wicket: "+player.wicket);
                System.out.println("Overs: "+player.overs);
                System.out.println("---------------------");
                return;
            }
        }
        System.out.println("Player not found!");
    }

    void displayPlayersSortedByRuns(String teamName) {

        players[] playersList = teamName.equalsIgnoreCase(team1.teamName) ? team1Players : team2Players;
        players[] sortedPlayers = Arrays.copyOf(playersList, playersList.length);

        for (int i = 0; i < sortedPlayers.length - 1; i++) {
            for (int j = 0; j < sortedPlayers.length - i - 1; j++) {
                if (sortedPlayers[j].runs < sortedPlayers[j + 1].runs) {
                    players temp = sortedPlayers[j];
                    sortedPlayers[j] = sortedPlayers[j + 1];
                    sortedPlayers[j + 1] = temp;
                }
            }
        }

        System.out.println("Players sorted by runs:");
        System.out.println("---------------------");
        for (players player : sortedPlayers) {
            System.out.println(player.PlayerName + " - " + player.runs+"("+player.balls + ") runs");

        }
    }

    void displayBatsmen(String teamName) {
        players[] playersList = teamName.equalsIgnoreCase(team1.teamName) ? team1Players : team2Players;
        System.out.println("Batsmen:");
        System.out.println("---------------------");
        for (players p : playersList) {
            if (p instanceof Batsman) {
                System.out.println(p.PlayerName + " - Runs: " + p.runs + " Balls: " + p.balls + " SR: " + (p.runs*100.0/p.balls));
            }
        }
    }

    void displayBowlers(String teamName) {
        players[] playersList = teamName.equalsIgnoreCase(team1.teamName) ? team1Players : team2Players;
        System.out.println("Bowlers:");
        System.out.println("---------------------");
        for (players p : playersList) {
            if (p instanceof Bowler) {
                if(p.overs>0)
                    System.out.println(p.PlayerName + " - Wickets: " + p.wicket + " Overs: " + p.overs + " Econ: " + (p.Bowlerrun/p.overs));
                else
                    System.out.println(p.PlayerName + " - Wickets: " + p.wicket + " Overs: " + p.overs + " Econ: " + (p.Bowlerrun/(p.overs+1)));

            }
        }
    }

    void displayAllrounders(String teamName) {
        players[] playersList = teamName.equalsIgnoreCase(team1.teamName) ? team1Players : team2Players;
        System.out.println("Allrounders:");
        System.out.println("---------------------");

        for (players p : playersList) {
            if (p instanceof Allrounder) {
                System.out.println(p.PlayerName + " - Runs: " + p.runs + " Wickets: " + p.wicket);
            }
        }
    }
}

class CricketMatch {
    static void print(String s) {
        System.out.print("+");
        for (int i = 0; i < s.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println("");
        System.out.println("|  " + s + "  |");
        System.out.print("+");
        for (int i = 0; i < s.length() + 4; i++) {
            System.out.print("-");
        }
        System.out.print("+");
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        runcount r = new runcount();
        r.startMatch();

        boolean b = true;
        do {
            System.out.println("Enter 1 for print Serch player");
            System.out.println("Enter 2 for print shortlist of players according to runs");
            System.out.println("Enter 3 for print Batsmen");
            System.out.println("Enter 4 for print Bowlers");
            System.out.println("Enter 5 for print Allrounders");
            System.out.println("Enter 6 for start new match");
            System.out.println("Enter 7 for exit");

            int Switch = sc.nextInt();


            switch (Switch) {
                case 1:
                    System.out.println("Enter team name of player:");
                    String team = sc.nextLine();
                    System.out.println("Enter name of player:");
                    String searchPlayer = sc.nextLine();
                    r.searchPlayer(team, searchPlayer);
                    break;

                case 2:
                    System.out.println("E snter team name to display players sorted by runs:");
                    String teamName = sc.nextLine();
                    r.displayPlayersSortedByRuns(teamName);
                    break;

                case 3:
                    System.out.println("Enter team name:");
                    String tBat = sc.nextLine();
                    r.displayBatsmen(tBat);
                    break;

                case 4:
                    System.out.println("Enter team name:");
                    String tBall = sc.nextLine();
                    r.displayBowlers(tBall);
                    break;

                case 5:
                    System.out.println("Enter team name:");
                    String tAll = sc.nextLine();
                    r.displayAllrounders(tAll);
                    break;

                case 6:
                    r.startMatch();
                    break;

                case 7:
                    b = false;
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }
        } while (b);
    }
}