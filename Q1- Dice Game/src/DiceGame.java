public class DiceGame {
    public static void pointLoader(int[][] allRolls, Gamer[] gamers, String[] args) {
        int currentPlayerIndex = 0;
        int remainingPlayers = gamers.length; // Initialize remaining players
        for (int[] roll : allRolls) {
            int roll1 = roll[0];
            int roll2 = roll[1];

            // Check if the current player is still in the game
            if (gamers[currentPlayerIndex].getScore() != -1) {
                if (roll1 == 1 && roll2 == 1) {
                    String line = (gamers[currentPlayerIndex].getName()+ " threw 1-1. Game over " + gamers[currentPlayerIndex].getName()+"!" );
                    outputwrite.writeToFile(args[1],line,true,true);
                    gamers[currentPlayerIndex].setPoint(-1);
                    remainingPlayers--; // Decrease remaining players count
                } else if (roll1 == 1 || roll2 == 1) {
                    String line =(gamers[currentPlayerIndex].getName() + " threw "
                            + roll[0] + "-" + roll[1]+ " and "+gamers[currentPlayerIndex].getName()
                            + "’s score is " +gamers[currentPlayerIndex].getScore()+"." );
                    outputwrite.writeToFile(args[1],line,true,true);
                } else if (roll1 == 0 && roll2 == 0) {
                    String line = (gamers[currentPlayerIndex].getName() + " skipped the turn and " +
                                    gamers[currentPlayerIndex].getName() +"’s score is "+ (gamers[currentPlayerIndex].getScore()) + ".");
                    outputwrite.writeToFile(args[1],line,true,true);
                    // Player passes
                } else {
                    // Add points to the player's score
                    gamers[currentPlayerIndex].addPoint(roll1 + roll2);
                    String line =(gamers[currentPlayerIndex].getName()+ " threw " + roll1 +"-" + roll2+
                            " and "+ gamers[currentPlayerIndex].getName()+ "’s score is "+ gamers[currentPlayerIndex].getScore() + ".");
                    outputwrite.writeToFile(args[1],line,true,true);
                }
            }

            // Move to the next player, considering only remaining players
            currentPlayerIndex = (currentPlayerIndex + 1) % gamers.length;
            while (gamers[currentPlayerIndex].getScore() == -1) {
                currentPlayerIndex = (currentPlayerIndex + 1) % gamers.length;
            }

            // Check if there's only one player left
            if (remainingPlayers == 1) {
                String line = (gamers[currentPlayerIndex].getName() +
                        " is the winner of the game with the score of "+ gamers[currentPlayerIndex].getScore()+"." +" Congratulations "+ gamers[currentPlayerIndex].getName() + "!");
                outputwrite.writeToFile(args[1],line,true,false);
                break;
            }
        }
    }


    public static void main(String[] args) {
        String[] allcontent = reader.readFile(args[0], false, false);
        String[] playernames = allcontent[1].split(",");
        String[][] allrollscontent = new String[allcontent.length - 2][];
        int[][] allrolls = new int[allcontent.length - 2][];

        for (int i = 2; i < allcontent.length; i++) {
            allrollscontent[i - 2] = allcontent[i].split("-");
            allrolls[i - 2] = new int[allrollscontent[i - 2].length];
            for (int j = 0; j < allrollscontent[i - 2].length; j++) {
                allrolls[i - 2][j] = Integer.valueOf(allrollscontent[i - 2][j]);
            }
        }

        Gamer[] gamers = new Gamer[playernames.length];
        for (int i = 0; i < playernames.length; i++) {
            gamers[i] = new Gamer(playernames[i]);
        }

        outputwrite.writeToFile(args[1],"", false,false);
        pointLoader(allrolls, gamers, args);
    }
}
