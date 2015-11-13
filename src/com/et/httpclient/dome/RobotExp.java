package com.et.httpclient.dome;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RobotExp {
   private Map<String, Integer> letterMap = new HashMap<String, Integer>();
   {
      letterMap.put("0", 0x30);
      letterMap.put("1", 0x31);
      letterMap.put("2", 0x32);
      letterMap.put("3", 0x33);
      letterMap.put("4", 0x34);
      letterMap.put("5", 0x35);
      letterMap.put("6", 0x36);
      letterMap.put("7", 0x37);
      letterMap.put("8", 0x38);
      letterMap.put("9", 0x39);

      letterMap.put("a", 0x41);
      letterMap.put("b", 0x42);
      letterMap.put("c", 0x43);
      letterMap.put("d", 0x44);
      letterMap.put("e", 0x45);
      letterMap.put("f", 0x46);
      letterMap.put("g", 0x47);
      letterMap.put("h", 0x48);
      letterMap.put("i", 0x49);
      letterMap.put("j", 0x4A);
      letterMap.put("k", 0x4B);
      letterMap.put("l", 0x4C);
      letterMap.put("m", 0x4D);
      letterMap.put("n", 0x4E);
      letterMap.put("o", 0x4F);
      letterMap.put("p", 0x50);
      letterMap.put("q", 0x51);
      letterMap.put("r", 0x52);
      letterMap.put("s", 0x53);
      letterMap.put("t", 0x54);
      letterMap.put("u", 0x55);
      letterMap.put("v", 0x56);
      letterMap.put("w", 0x57);
      letterMap.put("x", 0x58);
      letterMap.put("y", 0x59);
      letterMap.put("z", 0x5A);
   }

   public static void main(String[] args) throws IOException {
      RobotExp robotExp = new RobotExp();
      // inputNumber(null, "13145860701");
      try {
         int waitTime = 8000;
         Robot robot = new Robot();
         // 启动浏览器
         Runtime runtime = Runtime.getRuntime();
         runtime.exec("D:\\liebao\\liebao.exe");
         robot.delay(10000);
         // 在输入框输入“小米”
         robot.mouseMove(444, 209);// 鼠标移动坐标
         robotExp.input(robot, "xiaomi");
         robot.delay(1000);
         robotExp.input(robot, "1");// 选择1
         robot.delay(1000);
         // 点击搜索
         click(robot);
         robot.mouseMove(924, 208);// 鼠标移动坐标
         click(robot);
         // 点击小米官网
         robot.delay(waitTime);
         robot.mouseMove(218, 215);// 鼠标移动坐标
         click(robot);
         // 点击到登录界面
         robot.delay(waitTime);
         robot.mouseMove(1228, 84);
         click(robot);
         robot.delay(waitTime);
         // 输入账号
         robot.mouseMove(544, 356);
         click(robot);
         robot.delay(waitTime);
         robotExp.input(robot, "13145860701");
         // 输入密码
         robot.mouseMove(544, 409);
         click(robot);
         robot.keyPress(KeyEvent.VK_CAPS_LOCK);
         robotExp.input(robot, "liang995396426");
         robot.keyPress(KeyEvent.VK_CAPS_LOCK);
         robot.delay(1000);
         // 点击登录
         robot.mouseMove(544, 470);
         click(robot);
      } catch (AWTException e) {
         e.printStackTrace();
      }
   }

   /**
    * 输入
    * @param robot
    * @param numbers
    */
   private void input(Robot robot, String numbers) {
      String[] numberArray = numbers.split("");
      for (String key : numberArray) {
         if (key == null || key.trim().length() == 0) {
            continue;
         }
         robot.keyPress(letterMap.get(key));
      }
   }

   private static void click(Robot robot) {
      robot.mousePress(KeyEvent.BUTTON1_MASK);// 按下左键
      robot.mouseRelease(KeyEvent.BUTTON1_MASK);// 释放左键
   }
}