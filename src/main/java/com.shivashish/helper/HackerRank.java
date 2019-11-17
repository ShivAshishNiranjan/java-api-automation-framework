package com.shivashish.helper;


import java.util.Scanner;

class UsernameValidator {
    /*
     * Write regular expression here.
     */
    public static final String regularExpression = "";
}


public class HackerRank {


    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int n = Integer.parseInt(scan.nextLine());
        while (n-- != 0) {
            String userName = scan.nextLine();

            if (userName.matches(UsernameValidator.regularExpression)) {
                System.out.println("Valid");
            } else {
                System.out.println("Invalid");
            }
        }
    }
}

