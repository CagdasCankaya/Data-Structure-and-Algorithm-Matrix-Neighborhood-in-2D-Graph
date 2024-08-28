package org.example;

public class MatrixProblem {
    static int cols;
    static int rows ;
    static int matrix [][] ={
            {9, 4},
            {6, 3},
            {5, 8}};

    MatrixProblem(){
        this(matrix);
    }
    MatrixProblem(int [][] matrix){
        this.matrix = matrix;
    }
    MatrixProblem(int rows, int cols){
        this(matrix);
        MatrixProblem.rows = rows;
        MatrixProblem.cols = cols;
    }


    public void getInputMatrix() {
        for(int i = 0 ; i<matrix.length;i++){
            System.out.print("[");
            for(int j = 0 ; j<matrix[i].length;j++){
                if(j>0)
                    System.out.print(", ");
                System.out.print(matrix[i][j]);
            }

            System.out.println("]");
        }
    }

    public void getOutputMatrix(int [][] outputMatrix){
        System.out.println("\nOutput Array:");
        System.out.print("# ");
        for (int i = 0; i < outputMatrix.length; i++) {
            System.out.print(matrix[i / cols][i % cols] + " ");
        }
        System.out.println();

        for (int i = 0; i < outputMatrix.length; i++) {
            System.out.print(matrix[i / cols][i % cols] + " ");
            for (int j = 0; j < outputMatrix[i].length; j++) {
                System.out.print(outputMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void calculate(){
        int[][] outputMatrix = new int[rows * cols][rows * cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int currentId = getId(r, c );
                System.out.println("currentID : " + currentId);

                // left neighbor
                if (c > 0) {
                    int leftId = getId(r, c - 1);
                    outputMatrix[currentId][leftId] = 1;
                    outputMatrix[leftId][currentId] = 1;
                }

                // right neighbor
                if (c < cols - 1) {
                    int rightId = getId(r, c + 1);
                    outputMatrix[currentId][rightId] = 1;
                    outputMatrix[rightId][currentId] = 1;
                }

                // top neighbor
                if (r > 0) {
                    int topId = getId(r - 1, c);
                    outputMatrix[currentId][topId] = 1;
                    outputMatrix[topId][currentId] = 1;
                }

                // bottom neighbor
                if (r < rows - 1) {
                    int bottomId = getId(r + 1, c);
                    outputMatrix[currentId][bottomId] = 1;
                    outputMatrix[bottomId][currentId] = 1;
                }
            }
        }
        getInputMatrix();
        getOutputMatrix(outputMatrix);
    }

    // One of the most important method:
    //where each element is given an id
    static int getId(int row, int col) {
        return row * cols + col;
    }
    public static void main(String[] args) {
        MatrixProblem.rows = MatrixProblem.matrix.length;
        //cols = array[0].length; //for irregual array
        int maxColumnCount = 0 ;
        for(int i = 0; i<rows;i++){
            for(int j = 0 ; j<MatrixProblem.matrix[i].length;j++){
                if(matrix[i].length>maxColumnCount){
                    maxColumnCount = matrix[i].length;
                }
            }
        }
        MatrixProblem.cols= maxColumnCount;
        MatrixProblem matrixProblem = new MatrixProblem(rows,cols);
        matrixProblem.calculate();
    }


}

      /*
            Input matrix:
            [9,4]
            [6,3]
            [5,8]
        */



    /*  Output matrix:  [rows*cols][rows*cols]
        # 9 4 6 3 5 8
        9 0 1 1 0 0 0
        4 1 0 0 1 0 0
        6 1 0 0 1 1 0
        3 0 1 1 0 0 1
        5 0 0 1 0 0 1
        8 0 0 0 1 1 0  [6][6] */