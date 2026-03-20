package org.example.game.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private Cell cells[][];
    private final int noOfSnakes;
    private final int noOfLadders;
    List<Jump> snakesList=new ArrayList<>();
    List<Jump> laddersList=new ArrayList<>();
    private final int n;

    public Board(int n,int noOfSnakes,int noOfLadders) {
        this.n=n;
        this.noOfSnakes=noOfSnakes;
        this.noOfLadders=noOfLadders;
        cells=new Cell[n][n];
        createCells(cells);
        setUpSnakes(noOfSnakes);
        setUpLadders(noOfLadders);
    }

    private void createCells(Cell[][] cells) {
        for(int i=0;i<cells.length;i++)
        {
            for(int j=0;j<cells.length;j++)
            {
                cells[i][j]=new Cell();
            }
        }
    }

    private void setUpSnakes(int noOfSnakes) {

        for(int i=0;i<noOfSnakes;i++)
        {
            int start= ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
            int end= ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);

            while(!checkSnakeValidations(start,end))
            {
                System.out.println("Snake setup validation Error trying again");
                start= ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
                end= ThreadLocalRandom.current().nextInt(1,cells.length*cells.length);
            }

            Jump jump=new Jump(start,end,JumpType.SNAKE);
            int row=start/cells.length;
            int col=start%cells.length;
            int rowEnd=end/cells.length;
            int colEnd=end%cells.length;
            cells[row][col].setJump(jump);
            System.out.println("Snake at position: "+(row*cells.length+col));
            System.out.println("Moving to position: "+(rowEnd*cells.length+colEnd));
            snakesList.add(jump);
        }
    }

    private boolean checkSnakeValidations(int start,int end)
    {
        for(Jump jump:snakesList)
        {
            if(start<=end || end==jump.getStart() || start==jump.getEnd() || start==jump.getStart())
                return false;
        }

        return true;
    }

    private void setUpLadders(int noOfLadders) {
        for(int i=0;i<noOfLadders;i++)
        {
            int start= ThreadLocalRandom.current().nextInt(0,cells.length*cells.length);
            int end= ThreadLocalRandom.current().nextInt(0,cells.length*cells.length);

            while(!checkLadderValidations(start,end))
            {
                System.out.println("ladder setup validation Error trying again");
                start= ThreadLocalRandom.current().nextInt(0,cells.length*cells.length);
                end= ThreadLocalRandom.current().nextInt(0,cells.length*cells.length);
            }

            Jump jump=new Jump(start,end,JumpType.LADDER);
            int row=start/cells.length;
            int col=start%cells.length;
            int rowEnd=end/cells.length;
            int colEnd=end%cells.length;
            cells[row][col].setJump(jump);
            System.out.println("Ladder at position: "+(row*cells.length+col));
            System.out.println("Moving to position: "+(rowEnd*cells.length+colEnd));
            laddersList.add(jump);
        }
    }

    private boolean checkLadderValidations(int start, int end) {

        for(Jump jump:snakesList)
        {
            if(end==jump.getStart() || start==jump.getEnd() || start==jump.getStart())
                return false;
        }

        for(Jump jump:laddersList)
        {
            if(end<=start || end==jump.getEnd() || start== jump.getStart() || start==jump.getEnd())
                return false;
        }

        return true;
    }

    public Cell[][] getCells() {
        return cells;
    }


}
