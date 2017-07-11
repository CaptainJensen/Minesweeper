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

package Mine;

import java.io.*;
import java.util.Properties;

/**
 * Created by Jensen on 7/9/17.
 */
public class directorySearch {

    private String user = "";
    private String mainDirectory = "";
    private String logsDirectory = "";
    private String scoresDirectory = "";
    private String settingsDirectory = "";
    private String screenshotsDirectory = "";
    private static String easyScoresPath = "";
    private static String medScoresPath = "";
    private static String hardScoresPath = "";
    private static String settingsPath = "";
    private static String splashtextpath = "";

    //Initialising an in and outputStream for working with the file
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;


    //TODO: ADD QUOTES TO MAIN FILE EG A SPLASH TEXT THAT LOADS FROM EXTRA FILES 22


    public directorySearch() {
        user = System.getenv("USER");
        mainDirectory = "/Users/" + user + "/Library/Application Support/Minesweeper";
        logsDirectory = mainDirectory + "/Logs";
        scoresDirectory = mainDirectory + "/Scores";
        settingsDirectory = mainDirectory + "/Settings";
        screenshotsDirectory = mainDirectory + "/Screenshots";

    }


    /**
     * Creates the app directory in the appsupport directory and supporting files
     */
    public void createDirectories() {
        // Create a directory; all non-existent ancestor directories are automatically created
        File main = new File(mainDirectory);
        if(!main.exists()) {
            boolean isdirectoryCreated = (main.mkdirs());
            if (!isdirectoryCreated) {
                // Directory creation failed
                System.out.println("Main directory creation failed.");
                System.exit(17);
            }
        }

        File logs = new File(logsDirectory);
        if(!logs.exists()) {
            boolean islogsCreated = (logs.mkdirs());
            if (!islogsCreated) {
                // Directory creation failed
                System.out.println("Logs directory creation failed.");
                System.exit(17);
            }
        }
        File scores = new File(scoresDirectory);
        if(!scores.exists()) {
            boolean isscoresCreated = (scores.mkdirs());
            if (!isscoresCreated) {
                // Directory creation failed
                System.out.println("Scores directory creation failed.");
                System.exit(17);
            }
        }
        File settings = new File(settingsDirectory);
        if(!settings.exists()) {
            boolean issettingsCreated = (settings.mkdirs());
            if (!issettingsCreated) {
                // Directory creation failed
                System.out.println("Settings directory creation failed.");
                System.exit(17);
            }
        }

        File screenshots = new File(screenshotsDirectory);
        if(!screenshots.exists()) {
            boolean isscreenshotsCreated = (screenshots.mkdirs());
            if (!isscreenshotsCreated) {
                // Directory creation failed
                System.out.println("Screenshots directory creation failed.");
                System.exit(17);
            }
        }

        createFiles();
    }


    /**
     * Creates all files needed for the game to run. Then sets the file paths for reference
     */
    private void createFiles() {
        //TODO: Set up logger here. EX] Logger.createFile

        File easyScoresFile = new File(scoresDirectory + "/easyscores.dat");
        easyScoresPath = easyScoresFile.getAbsolutePath();

        if(!easyScoresFile.exists()) {
            try {
                easyScoresFile.createNewFile();
                outputStream = new ObjectOutputStream(new FileOutputStream(easyScoresPath));
            } catch (IOException e) {
                System.out.println("[Log]: Easy Scores file could not be created");
                e.printStackTrace();
            }
        }


        File medScoresFile = new File(scoresDirectory + "/medscores.dat");
        medScoresPath = medScoresFile.getAbsolutePath();
        if(!medScoresFile.exists()) {
            try {
                medScoresFile.createNewFile();
                outputStream = new ObjectOutputStream(new FileOutputStream(medScoresPath));
            } catch (IOException e) {
                System.out.println("[Log]: Med Scores file could not be created");
                e.printStackTrace();
            }
        }


        File hardScoresFile = new File(scoresDirectory + "/hardscores.dat");
        hardScoresPath = hardScoresFile.getAbsolutePath();
        if(!hardScoresFile.exists()) {
            try {
                hardScoresFile.createNewFile();
                outputStream = new ObjectOutputStream(new FileOutputStream(hardScoresPath));
            } catch (IOException e) {
                System.out.println("[Log]: Hard Scores file could not be created");
                e.printStackTrace();
            }
        }


        File settingsFile = new File(settingsDirectory + "/settings.properties");
        settingsPath = settingsFile.getAbsolutePath();
        if(!settingsFile.exists()) {
            try {

                settingsFile.createNewFile();

                Properties properties = new Properties();
                try (OutputStream output = new FileOutputStream(settingsFile.getAbsoluteFile())) {

                    properties.setProperty("easyToggle", String.valueOf(false));
                    properties.setProperty("medToggle", String.valueOf(true));
                    properties.setProperty("hardToggle", String.valueOf(false));
                    properties.setProperty("username", System.getenv("LOGNAME"));

                    properties.store(output, "Created Settings File");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                System.out.println("[Log]: Settings file could not be created");
                e.printStackTrace();
            }

        }

        File splashFile = new File(mainDirectory + "/splash.txt");
         splashtextpath = splashFile.getAbsolutePath();
        if(!splashFile.exists()) {

            BufferedWriter bw = null;
            FileWriter fw = null;

            try {

                final String[] SPLASH_TEXT = { //Max of 22 Chars
                        "Have a wonderful day",
                        "99 Bombs on the wall..",
                        "5871 lines of code!",
                        "Have fun!",
                        "The cake is a lie!",
                        "Doug Rattman",
                        "GLaDOS",
                        "Cheat codes!",
                        "Koalas are cool",
                        "Life = 42",
                        "Luna",
                        "Boom!",
                        "1990's",
                        "!Turing complete",
                        "Lots of glass!",
                        "⌘+S to take a selfie",
                        "Curt Johnson",
                        "Removed in v8",
                        "8x8 -> 10x10",
                        "Hello, " + System.getenv("LOGNAME"),
                        "Hello",
                        "22 characters is long ",
                        "Minesweeper",
                        "01001101 01010011",
                        String.valueOf(System.currentTimeMillis()),
                        "This message will not be part of the splash text, Why is that?",
                        "Thor",
                        "You got this!",
                        "You are amazing",
                        "Win Win Win!",
                        "Morning cup of java",
                        "Magic 8 ball",
                };

                fw = new FileWriter(splashFile.getAbsoluteFile());
                bw = new BufferedWriter(fw);
                for (int s = 0; s < SPLASH_TEXT.length; s++) {
                    if(SPLASH_TEXT[s].length() <= 22) {
                        bw.write(SPLASH_TEXT[s]);
                        bw.newLine();
                    }

                }


            } catch (IOException e) {
                System.out.println("[Log]: could not create splash file");
                e.printStackTrace();

            } finally {

                try {

                    if (bw != null)
                        bw.close();

                    if (fw != null)
                        fw.close();

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }
        }



    }



    public String getEasyScoresPath() { return easyScoresPath; }
    public String getMedScoresPath() { return medScoresPath; }
    public String getHardScoresPath() { return hardScoresPath; }
    public String getSettingsPath() { return settingsPath; }
    public String getScreenshotsDirectory() { return screenshotsDirectory; }
    public String getLogsDirectory() { return logsDirectory; }
    public String getSplashtextpath() { return splashtextpath; }






}
