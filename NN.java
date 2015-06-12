
/********************************************************************************
 *  Compilation:  javac NN.java
 *  Execution:    java NN 
 *
 *  Author: Muhammad Suleman 
 *  Date: 01/12/2014
 *
 *  User inputs upon execution
 *  --------------------------
 *  Inputs,HiddenLayer,Outputs,examples,LearningRate,momentum 2,2,3,1,1.0,0
 *  x,y 1,0,0  1,1,1
 *  weights 0,0,0,0,0.3,0.1,0,-0.1,0,0,-0.3,0.1,-0.2,0.2,0.2
 *
 *  Limitations
 *  -----------
 *   - Does not work if anything other than integers and doubles are not entered
 *  
 *********************************************************************************/

import java.util.*; 
public class NN {
  public static void main(String [] args) {

    //scanner fo user input
    Scanner scan = new Scanner(System.in); 
    //variables
    int inputs, hiddenLayers, outputs, examples, counter=1;
    double learningRate, momentum;
    //getting the relevant information from the user
    System.out.println("please enter the number of inputs");
    inputs = scan.nextInt();

    System.out.println("please enter the number of hidden layers");
    hiddenLayers = scan.nextInt();

    System.out.println("please enter the number of outputs");
    outputs = scan.nextInt();

    System.out.println("please enter the number of examples");
    examples = scan.nextInt();

    System.out.println("please enter the learning rate");
    learningRate = scan.nextDouble();

    System.out.println("please enter Momentum");
    momentum = scan.nextDouble();
    // using a multidimensional array to store user input 
    System.out.println("Please enter the X and Y inputs");
    int [][] xy = new int [examples+1][inputs+outputs+1]; 
    //for loop to get each input
    for (int i = 1; i <= examples; i++) {
      for (int j = 1; j <= inputs+outputs; j++) { 
        if (j <= inputs) {
          System.out.print("X" + j + " example " + i + "=");
        }
        else {
          System.out.print("Y for example " + i + "= ");
        }
        xy[i][j] = scan.nextInt();
      }
    }
    // multidimensional array for the weights
    double [][] weights = new double 
      [inputs+hiddenLayers+1][hiddenLayers+outputs]; 
    System.out.println("What are the weights?");
    // getting user input and adding it to the array - from inputs to hidden units
    for (int i = 0; i <= inputs; i++) { 
      for (int j = 0; j <= hiddenLayers; j++) {
        System.out.print("Weight W" + i + " out " + j + "= "); 
        weights[i][j] = scan.nextDouble();
      }
    }
    //weights from the hidden units to the output
    for (int j = 1; j <= hiddenLayers; j++) { 
      for (int k = 0; k < outputs; k++) {
        System.out.print("Hidden Node " + j + " to Output " + k + "= ");
        weights[j+inputs][k] = scan.nextDouble();
      }
    }
    System.out.println("All user inputs taken");
    // while loop for the for executing all the examples
    while (counter <= examples) {
      //to store out values from inputs to hidden layers
      double [] out = new double [outputs + hiddenLayers]; 
      //to store beta values 
      double [] beta = new double [outputs + hiddenLayers]; 
      //to store delta values
      double [][] delta = new double [inputs+hiddenLayers+1][hiddenLayers+outputs]; 


      //hidden node out values
      for (int i = 0; i <= inputs; i++) {
        for (int j = 1; j <= hiddenLayers; j++) {
          out[j] += weights[i][j]*xy[counter][i];
        }
      }
      //performing the sigmoid function on the out values and rounding it to 4 decimal places
      for (int j = 1; j <= hiddenLayers; j++) {
        out[j] = 1/(1+Math.exp(-out[j]));
        out[j] = Math.round(out[j] * 10000.0) / 10000.0;
        System.out.println("hidden node " + j + " = " + out[j]);
      }

      // values for the out values for the output nodes
      for (int j = 0; j <= inputs+hiddenLayers; j++) {
        for (int k = 0; k < outputs; k++) {
          if (j <= inputs) {
            out[k] += weights[j][k]*xy[counter][j];
          }
          else {
            out[k] += weights[j][k]*out[j-hiddenLayers+1];
          }
        }
      }
      for (int k = 0; k < outputs; k++) {
        out[k] = Math.round(out[k] * 10000.0) / 10000.0;
        System.out.println(" Output " + k + " = " + out[k]);
      }
      System.out.println(" End of forward pass");
      counter++;
    }
  }
}





















