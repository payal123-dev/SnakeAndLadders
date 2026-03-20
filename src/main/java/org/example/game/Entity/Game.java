package org.example.game.Entity;

import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    Board board;
    Deque<Player> playerDeque;
    Dice dice;
    Player winner;
    public Game(int boardSize,int noOfSnakes,int noOfLadders,int playerCount) {
        initializeGame(boardSize,noOfSnakes,noOfLadders,playerCount);

    }

    public void initializeGame(int boardSize,int noOfSnakes,int noOfLadders,int playerCount)
    {
        this.board = new Board(boardSize,noOfSnakes,noOfLadders);
        this.playerDeque = new LinkedList<>();
        this.dice=new Dice(1);
        addPlayers(playerCount);
    }

    public void addPlayers(int playerCount) {
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<playerCount;i++)
        {
            String playerName=sc.nextLine();
            Player player=new Player(i+1,playerName);
            playerDeque.add(player);
        }
        sc.close();

    }

    public void startGame()
    {
        //boolean isWinner=false;
        while(winner==null)
        {
            Player currentPlayer=playerDeque.poll();
            System.out.println("Player :"+currentPlayer.getPlayerId()+"at current position "+currentPlayer.getCurrentPosition()+" playing");
            int sum=dice.rollDice();
            System.out.println("Rolling Dice: "+sum);
//            if(sum==6)
//            {
//                System.out.println("Player :"+currentPlayer.getPlayerId()+" getting chance again as current dice value is 6");
//                sum+=dice.rollDice();
//            }
            int newPlayerPosition=currentPlayer.getCurrentPosition()+sum;
            Cell cells[][]=board.getCells();
            if(newPlayerPosition>=cells.length*cells.length)
            {
                winner=currentPlayer;
            }
            int row=newPlayerPosition/cells.length;
            int col=newPlayerPosition%cells.length;
            if(cells[row][col].getJump()!=null)
            {
                System.out.println("Encountered "+cells[row][col].getJump().getJumpType()+" at postion: "+(row*cells.length+col));
                currentPlayer.setCurrentPosition(cells[row][col].getJump().getEnd());
            }
            else {
                currentPlayer.setCurrentPosition(newPlayerPosition);
            }
            System.out.println("Player :"+currentPlayer.getPlayerId()+" moving to position: "+currentPlayer.getCurrentPosition());
            playerDeque.offer(currentPlayer);

        }
        System.out.println("Winner is "+winner.getPlayerName());
    }
}
