/*******************************************************************************
 * MIT License
 *
 * Copyright (c) 2017 Hunter Jensen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/


/*

NOTES: Thanks to https://github.com/AlqasimO for help on the easyScores file readings

 */
package Mine;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jensen on 7/3/17.
 */
public class Scoreshandeler {

    private ArrayList<Score> medScores;
    private ArrayList<Score> hardScores;
    private ArrayList<Score> easyScores;

    private String errorMessage;

    private directorySearch directorySearch = new directorySearch();


    //Initialising an in and outputStream for working with the file
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;

    public Scoreshandeler() {
        easyScores = new ArrayList<Score>();
        medScores = new ArrayList<Score>();
        hardScores = new ArrayList<Score>();

        errorMessage = "";
    }

    public ArrayList<Score> getEasyScores() {
        loadScoreFile(Difficulty.EASY);
        sort(Difficulty.EASY);
        return easyScores;
    }
    public ArrayList<Score> getMedScores() {
        loadScoreFile(Difficulty.MEDIUM);
        sort(Difficulty.MEDIUM);
        return medScores;
    }
    public ArrayList<Score> getHardScores() {
        loadScoreFile(Difficulty.HARD);
        sort(Difficulty.HARD);
        return hardScores;
    }

    private void sort(Difficulty difficulty) {
        ScoreComparator comparator = new ScoreComparator();
        if(difficulty == Difficulty.EASY) {
            Collections.sort(easyScores, comparator);
        } else if(difficulty == Difficulty.MEDIUM) {
            Collections.sort(medScores, comparator);
        } else if(difficulty == Difficulty.HARD) {
            Collections.sort(hardScores, comparator);
        } else return;

    }
    public void addScore(String name, double score, Difficulty difficulty) {
        loadScoreFile(difficulty);
        if(difficulty == Difficulty.EASY) {
            easyScores.add(new Score(name, score));
        } else if(difficulty == Difficulty.MEDIUM) {
            medScores.add(new Score(name, score));
        } else if(difficulty == Difficulty.HARD) {
            hardScores.add(new Score(name, score));
        } else return;

        updateScoreFile(difficulty);
    }
    private void loadScoreFile(Difficulty difficulty) {
        try {
            if (difficulty == Difficulty.EASY) {
                inputStream = new ObjectInputStream(new FileInputStream(directorySearch.getEasyScoresPath()));
                easyScores = (ArrayList<Score>) inputStream.readObject();
            }
            else if (difficulty == Difficulty.MEDIUM) {
                inputStream = new ObjectInputStream(new FileInputStream(directorySearch.getMedScoresPath()));
                medScores = (ArrayList<Score>) inputStream.readObject();
            }
            else if (difficulty == Difficulty.HARD) {
                inputStream = new ObjectInputStream(new FileInputStream(directorySearch.getHardScoresPath()));
                hardScores = (ArrayList<Score>) inputStream.readObject();
            } else {
                return;
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("[Log] File not Found Error: " + e.getMessage());
            errorMessage = "Scores file not found, check Files!";
        }
        catch (IOException e) {
            System.out.println("[Log] IO Error: " + e.getMessage());
            errorMessage = "";
            //errorMessage = "No Scores to display"; //Error message when first creation of files loads. Error message is when files have not yet been initalized;
            deleteScores(); //RESET scores to initialize files
        }
        catch (ClassNotFoundException e) {
            System.out.println("[Log] CNF Error: " + e.getMessage());
            errorMessage = "Class not found Error";
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            }
            catch (IOException e) {
                System.out.println("[Log] IO Error: " + e.getMessage());
                errorMessage = "Scores file not found TWO, check Files!";
            }
        }
    }
    private void updateScoreFile(Difficulty difficulty) {
        try {
            if (difficulty == Difficulty.EASY) {
                outputStream = new ObjectOutputStream(new FileOutputStream(directorySearch.getEasyScoresPath()));
                outputStream.writeObject(easyScores);
            }
            else if (difficulty == Difficulty.MEDIUM) {
                outputStream = new ObjectOutputStream(new FileOutputStream(directorySearch.getMedScoresPath()));
                outputStream.writeObject(medScores);
            }
            else if (difficulty == Difficulty.HARD) {
                outputStream = new ObjectOutputStream(new FileOutputStream(directorySearch.getHardScoresPath()));
                outputStream.writeObject(hardScores);
            } else return;
        }
        catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
            errorMessage = "Scores file not found, check Files!";
        }
        catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
            errorMessage = "No Scores to display";
        }
        finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            }
            catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
                errorMessage = "Error in loading scores";
            }
        }
    }
    public void deleteScores(){
        easyScores.clear();
        medScores.clear();
        hardScores.clear();
        updateScoreFile(Difficulty.EASY);
        updateScoreFile(Difficulty.MEDIUM);
        updateScoreFile(Difficulty.HARD);


    }

    public ArrayList<Score> getScoreArray(Difficulty difficulty) {   //<-- Defines what HighscoreString is (ie the outpurt stuff shown)
        int max = 20; //<-- Number of max highscores to show

        ArrayList<Score> scores = null;

        if (difficulty == Difficulty.EASY) {
            scores = getEasyScores();
        }
        else if(difficulty == Difficulty.MEDIUM) {
            scores = getMedScores();
        }
        else if(difficulty == Difficulty.HARD) {
            scores = getHardScores();
        }
        else {
            //scores = getEasyScores();
        }

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            scores.set(i,new Score((i + 1) + ")" + scores.get(i).getName(), scores.get(i).getScore()));
            i++;
        }
        return scores;
    }

    public String getErrorMessage() { return errorMessage; }



}
