package com.bragalund;

import java.util.Scanner;

public class Input {

    public static boolean yesAnswerFromUser() {
        System.out.println("Answer: Y/N");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        scanner.close();
        return answer.compareToIgnoreCase("y") >= 0;
    }
}
