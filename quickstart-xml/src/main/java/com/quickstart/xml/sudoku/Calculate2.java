/**
 * 项目名称：quickstart-xmltest 
 * 文件名：Calculate.java
 * 版本信息：
 * 日期：2016年12月16日
 * Copyright asiainfo Corporation 2016
 * 版权所有 *
 */
package com.quickstart.xml.sudoku;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Calculate
 * 
 * @author：yangzl@asiainfo.com
 * @2016年12月16日 上午9:34:47
 * @version 1.0
 */
class Calculate2 implements Runnable {
    public static boolean[][] boo = new boolean[9][9];
    public static int upRow = 0;
    public static int upColumn = 0;
    public static int[][] b = new int[9][9];

    public static void flyBack(boolean[][] judge, int row, int column) {}

    public static void arrayAdd(ArrayList<Integer> array, TreeSet<Integer> tree) {
        for (int z = 1; z < 10; z++) {
            boolean flag3 = true;
            Iterator<Integer> it = tree.iterator();
            while (it.hasNext()) {// 10
                int b = it.next().intValue();
                if (z == b) {
                    flag3 = false;
                    break;
                }
            }
            if (flag3) {
                array.add(new Integer(z));
            }
            flag3 = true;
        }
    }

    public static ArrayList<Integer> assume(int row, int column) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        TreeSet<Integer> tree = new TreeSet<Integer>();
        if (0 <= row && row <= 2 && 0 <= column && column <= 2) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 0; a2 < 3; a2++) {
                for (int b4 = 0; b4 < 3; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (0 <= row && row <= 2 && 3 <= column && column <= 5) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 0; a2 < 3; a2++) {
                for (int b4 = 3; b4 < 6; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (0 <= row && row <= 2 && 6 <= column && column <= 8) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 0; a2 < 3; a2++) {
                for (int b4 = 6; b4 < 9; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (3 <= row && row <= 5 && 0 <= column && column <= 2) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 3; a2 < 6; a2++) {
                for (int b4 = 0; b4 < 3; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (3 <= row && row <= 5 && 3 <= column && column <= 5) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 3; a2 < 6; a2++) {
                for (int b4 = 3; b4 < 6; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (3 <= row && row <= 5 && 6 <= column && column <= 8) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 3; a2 < 6; a2++) {
                for (int b4 = 6; b4 < 9; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (6 <= row && row <= 8 && 0 <= column && column <= 2) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 6; a2 < 9; a2++) {
                for (int b4 = 0; b4 < 3; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (6 <= row && row <= 8 && 3 <= column && column <= 5) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 6; a2 < 9; a2++) {
                for (int b4 = 3; b4 < 6; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        } else if (6 <= row && row <= 8 && 6 <= column && column <= 8) {
            for (int a = 0; a < 9; a++) {
                if (a != column && b[row][a] != 0) {
                    tree.add(new Integer(b[row][a]));
                }
            }
            for (int b1 = 0; b1 < 9; b1++) {
                if (b1 != row && b[b1][column] != 0) {
                    tree.add(new Integer(b[b1][column]));
                }
            }
            for (int a2 = 6; a2 < 9; a2++) {
                for (int b4 = 6; b4 < 9; b4++) {
                    if ((!(a2 == row && b4 == column)) && b[a2][b4] != 0) {
                        tree.add(new Integer(b[a2][b4]));
                    }
                }
            }
            arrayAdd(array, tree);
        }
        return array;
    }

    @Override
    public void run() {

        for (int a = 0; a < 9; a++) {
            for (int b1 = 0; b1 < 9; b1++) {
                if (b[a][b1] != 0) {
                    boo[a][b1] = true;
                } else {
                    boo[a][b1] = false;
                }
            }
        }
        boolean flag = true;
        ArrayList<Integer>[][] utilization = new ArrayList[9][9];
        int row = 0;
        int column = 0;
        while (column < 9) {
            if (flag == true) {
                row = 0;
            }
            while (row < 9) {
                if (b[row][column] == 0) {
                    if (flag) {
                        ArrayList<Integer> list = assume(row, column);
                        utilization[row][column] = list;
                    }
                    if (utilization[row][column].isEmpty()) {
                        flyBack(boo, row, column);
                        row = upRow;
                        column = upColumn;
                        b[row][column] = 0;
                        column--;
                        flag = false;
                        break;
                    } // if(list.isEmpty())
                    else {
                        b[row][column] = utilization[row][column].get(0);
                        utilization[row][column].remove(0);
                        flag = true;
                        boolean r = true;
                        for (int a1 = 0; a1 < 9; a1++) {
                            for (int b1 = 0; b1 < 9; b1++) {
                                if (r == false) {
                                    break;
                                }
                                if (b[a1][b1] == 0) {
                                    r = false;
                                }
                            }
                        }
                        if (r) {
                            for (int a1 = 0; a1 < 9; a1++) {
                                for (int b1 = 0; b1 < 9; b1++) {
                                    System.out.print("b[" + a1 + "][" + b1 + "]" + b[a1][b1] + ",");
                                    Myframe2.filed[a1][b1].setText(b[a1][b1] + "");
                                }
                            }
                        }
                    }
                } // if(int[row][column]==0)
                else {
                    flag = true;
                }
                row++;
            }
            column++;
        }
    }
}
