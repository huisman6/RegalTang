package com.lianjia.sh.se.config.realtang.config;

enum InterestPerson {
  有效出租方(1002),
  有效描述方(1003),
  信息方(1004),
  独家出租方(1005),
  独家出售方(1006);
  private int code;

  private InterestPerson(int code) {
    this.code = code;
  }

  /**
   * @return the code
   */
  public int getCode() {
    return this.code;
  }
  
  
}
