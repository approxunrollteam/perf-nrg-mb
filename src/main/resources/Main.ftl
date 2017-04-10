package fr.inria.approxloop.perfenergy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* Created by elmarce on 28/10/16.
*/
public class Main {
  /**
  * Entry point for the measurements
  */


  public static void main(String[] args) throws Exception {
    new PerformanceRunner().runOperationsPerSecond(5, 30, dbPath);
  }
}
