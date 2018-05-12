import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


//Must support mutlple clients at the same time  Some version of a Player Handler
//Questions are only specify to a game: Keep track of players  Player handler
//Authenticate players by name
//PLayer handler is how the player(Client) communicates with the server and the server communicates with the player(Client)
/**
 * Collect all the Scoreboard Server classes in this file. Note that this file
 * MUST BE called ScoreboardServer.java
 */
//middle man between client and game
class ScoreboardServer{
    private ArrayList<ChallengeResponseGame> games;
    private ArrayList<ScoreboardClient> Players;

    public void startServer(int sslPort){

        //Creates a server socket, bound to the specified port.
        ServerSocket server = null;
        try {
            server = new ServerSocket(sslPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //listens for activity on the server
        while (true) {
            Socket client = null;
            try {
                if (server != null) {
                    client = server.accept();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader input = null;
            try {
                if (client != null) {
                    input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter output = null;
            try {
                if (client != null) {
                    output = new PrintWriter(client.getOutputStream(),true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            // WRITE ME: accept connection, extract streams and start thread
            ScoreboardClient clientThread = new ScoreboardClient(input, output, games);
            Thread t = new Thread(clientThread);
            t.start();//memory error
            // register callback
            clientThread.registerCallback(clientThread);

        }

    }

    //Arraylist to hold the list of ChallengeResponseGame
    ScoreboardServer(ArrayList<ChallengeResponseGame> games){
        this.games = games;
    }

    //setup for the ssl Connection
    //This will use the SSL with keystore and pasword
    //This will be ssl
    public void setupSSL(String s, String password){

    }
    //Start the server itself
}
/*
 ***ERRORS***
User Name Selected
Print Scoredboard
User Name Selected
Choosing Random Game
What block cipher uses a block size of 128 bits?
User Name Selected
 ***User Name is not being Saved Assume it is just a matter of program going over again***
User Name Selected
Choosing Random Game
What block cipher uses a block size of 128 bits?
0
User Name Selected
Choosing Random Game
What block cipher uses a block size of 128 bits?
0
 ***Randoming is not working or I am very Unlucky***
User Name Selected
Other Commands
Expected Inputs: Help, Game, Question, Scoreboard, Random
User Name Selected ***Keeps going back to the The top since while True?***
Choose game Crypto or Networking
0:Crypto /n1:Networking
void Help(){//temp methods just for ease of understanding
System.out.println("Expected Inputs: Help, Game, Question, Scoreboard, Random");
}
void randomGame(){ //temp methods just for ease of understanding
//Random number creation for games
Random generator = new Random();
//Apply the number to the list of games
ChallengeResponseGame currentGame=games.get(generator.nextInt(1));
//add the username to the current game
currentGame.addPlayer(UserName);
//Question selection is done by random choice
//Store the question for the current game by getting the questions
//then index of the next random number to get the single question
String question = currentGame.getQuestions().get(generator.nextInt(1)).getQuestion();
//UserAnswer for the question
String userAnswer = input.readLine();
//checking the answer for the question of the current game by this.UserName
currentGame.answer(UserName, question, userAnswer);
}
void Game(){//temp methods just for ease of understanding
//The available games
System.out.println("0:Crypto /n1:Networking");
//Choosing the which game to play
int gameChoice = input.readLine();
//the currentGame is created with the games array at that array
ChallengeResponseGame currentGame=games.get(gameChoice);
//The available Questions
System.out.println("Q1 or Q2");
//Choosing the which question to play
int gameChoice2 = input.readLine();
//The question is choosen
String question = currentGame.getQuestions().get(gameChoice2).getQuestion();
//UserAnswer for the question
String userAnswer = input.readLine();
//checking the answer for the question of the current game by this.UserName
currentGame.answer(UserName, question, userAnswer);
}
void Scoreboard(){//temp methods just for ease of understanding
int gameChoice3 = input.readLine();
//Should get the game at the choice of the user and return the names and scores
games.get(gameChoice).getScores();
}
 */
