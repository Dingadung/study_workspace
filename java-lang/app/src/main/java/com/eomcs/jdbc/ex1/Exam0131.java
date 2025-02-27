// JDBC 드라이버 준비 - 드라이버 클래스 로딩과 등록
package com.eomcs.jdbc.ex1;

import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Properties;

public class Exam0131 {

  public static void main(String[] args) {
    try {
      Properties props = new Properties();
      props.load(new FileReader("./jdbc-driver.properties")); // 나중에 DBMS가 바뀌었을 때 코딩 변경을 안하기 위한 코드 / 소스 코드가 아닌, jdbc-driver.properties 파일만 변경하면 된다!
      System.out.println(props.getProperty("jdbc.driverClassName"));
      System.out.println(props.getProperty("jdbc.url"));

      // 위의 방식을 사용하면 다음과 같이, 
      // Driver 구현체를 소스 파일에 직접 명시할 필요가 없다.
      Class.forName(props.getProperty("jdbc.driverClassName"));
      java.sql.Driver driver = DriverManager.getDriver(props.getProperty("jdbc.url"));
      System.out.println(driver);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


