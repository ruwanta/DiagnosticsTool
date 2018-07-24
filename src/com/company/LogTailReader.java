package com.company;


import java.io.*;

public class LogTailReader{
    //this class used to read carbon log file



    //Initiate Log file
    private RandomAccessFile LogFile;
    //Initiate Log file path
    private String LogFilePath;
    //Initiate Logfile length
    private Long LogFileLength;
    //Initiate Char int
    private int charInt;
    //Initiate Char;
    private Character character;


    //constructor - set initial Lof file length 0 and set Log file path;
    public LogTailReader() {
        LogFilePath=new XmlHelper().getLogFilePath();
        LogFileLength=0L;
        charInt=0;
    }



    public void tailfile() {
        //Method used to tail the log file
        try{
            if(LogFilePath!=null){
                LogFile= new RandomAccessFile(LogFilePath,"r");
                LogFileLength= LogFile.length();
                System.out.println(LogFile.length());

                while(true){
                    Thread.sleep(1000);
                    // Checking the Log file for every 1 second.
                    if(LogFile.length()>LogFileLength){
                        //Lines added.

                        LogFile.seek(LogFileLength);
                        //skip for last read position


                        while((charInt=LogFile.read())!= -1){
                            String  str= String.valueOf((char) charInt);
                            if(str.compareTo("\n")==0){
                                System.out.println("\n");
                            }
                            System.out.print((char) charInt);
                        }
                        //read Log file


                        LogFileLength=LogFile.length();
                        //update Log file Length

                    }else if (LogFile.length()<LogFileLength){
                        //file lines deleted need to reset.


                        LogFileLength=0L;
                        LogFile.seek(LogFileLength);
                        //skip to initial positoin

                        while((charInt=LogFile.read())!= -1){
                            System.out.print((char) charInt);
                        }
                        //read Log File

                        LogFileLength=LogFile.length();
                        //update current Log file Length
                    }
                }
            }
        } catch (FileNotFoundException e){
            System.out.print("File Not Found. Please Check the path");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.print("IO error occurred.");
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





}