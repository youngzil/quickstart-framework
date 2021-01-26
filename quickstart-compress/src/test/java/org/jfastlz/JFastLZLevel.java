/**
 * 
 */
package org.jfastlz;

public enum JFastLZLevel {
  One(1), Two(2);
  private int level;

  JFastLZLevel(int level) {
    this.level = level; 
  }
  public int getLevel() {
    return this.level;
  }

  public static JFastLZLevel evaluateLevel(int level) {
    switch (level) {
    case 1:
      return One;
    case 2:
      return Two;
    default:
      return null;
    }
  }
}
