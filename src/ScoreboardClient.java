import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Collect all the Scoreboard Client classes in this file. Note that this file
 * MUST BE called ScoreboardClient.java
 */
public class ScoreboardClient implements Runnable {
    int gameChoice;
    private BufferedReader input;
    private PrintWriter output;
    private ScoreboardClient master;
    //Interact with the questions from the challenge Response
    private ArrayList<ChallengeResponseGame> games;

    ScoreboardClient(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    void registerCallback(ScoreboardClient c) {
        this.master = c;
    }

    public void run() {
        int port = 4001;
        try {

            //Creates a server socket, bound to the specified port.
            //should connect to the localhost on port 4001
            this.master.output.println("User Name Selected");
            //Username Declariation
            String UserName = this.master.input.readLine();
            //Add to array of people
            this.master.output.println("User Name Selected");
            while (true) {
                String userInput = this.master.input.readLine().toLowerCase();
                switch (userInput) {
                    //Or this may have to be done with an IF Else statements which might
                    //be a cleaner solution
                    //I moved most of this code to Client since it is interactive while
                    //Server is not
                    case "help":
                        this.master.output.println("Other Commands");
                        this.master.output.println("Expected Inputs: Help, Game, Question, Scoreboard, Random");
                        break;
                    case "game":
                        this.master.output.println("Choose game Crypto or Networking");
                        //The available games
                        this.master.output.println("0:Crypto \n 1:Networking");
                        //Choosing the which game to play
                        gameChoice = Integer.parseInt(this.master.input.readLine());
                        //Converts the input to the index of game where
                        gameChoice = games.indexOf(gameChoice);
                        //the currentGame is created with the games array at that array
                        ChallengeResponseGame currentGame = games.get(gameChoice);
                        //The available Questions
                        this.master.output.println("Question 1 or Question 2");
                        //Choosing the which question to play
                        int qChoice = Integer.parseInt(this.master.input.readLine());
                        //The question is choosen
                        String question = currentGame.getQuestions().get(qChoice).getQuestion();
                        //UserAnswer for the question
                        String userAnswer = this.master.input.readLine();
                        //checking the answer for the question of the current game by this.UserName
                        currentGame.answer(UserName, question, userAnswer);
                        break;
                    case "scoreboard":
                        this.master.output.println("Print ScareBird");

                        //Converts the input to the index of game where
                        int gameScareBird = games.indexOf(gameChoice);
                        //Should get the game at the choice of the user and return the names and scores
                        games.get(gameScareBird).getScores();
                        break;
                    case "random":
                        this.master.output.println("Choosing Random Game");
                        //Random number creation for games
                        Random generator = new Random();
                        //Apply the number to the list of games
                        ChallengeResponseGame currentGameRandom = games.get(generator.nextInt(1));
                        //add the username to the current game
                        currentGameRandom.addPlayer(UserName);
                        //Question selection is done by random choice
                        //Store the question for the current game by getting the questions
                        //then index of the next random number to get the single question
                        String questionRandom = currentGameRandom.getQuestions().get(generator.nextInt(1)).getQuestion();
                        //UserAnswer for the question
                        this.master.output.println(questionRandom);
                        String userAnswerRandom = this.master.input.readLine();
                        //checking the answer for the question of the current game by this.UserName
                        this.master.output.println(currentGameRandom.answer(UserName, questionRandom, userAnswerRandom));
                        break;
                    default:
                        this.master.output.println("Unexpected Input Try Again!");
                }
            }
        } catch (IOException ex) {
            System.err.println(String.format("Unable to connect to port %d", port));
        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {
            System.err.println("Shit went to far");
        } catch (java.lang.NullPointerException ex) {
            System.err.println("Noll Ponter");
        }
    }
}
