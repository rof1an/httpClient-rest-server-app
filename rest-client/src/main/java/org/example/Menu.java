package org.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Data
@RequiredArgsConstructor
public class Menu {
    private String userName;
    private final HttpChat httpChat = new HttpChat();
    private static Scanner scan = new Scanner(System.in);

    public void start() {
        System.out.print("Введите ваш ник: ");
        userName = scan.nextLine();

        new Thread(() -> {
            asyncUpdateMessages();
        }).start();

        while (true) {
            waitAndSendMessage();
        }
    }

    private void asyncUpdateMessages() {
        while (true) {
            httpChat.getNewMessages(userName).forEach(System.out::println);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void waitAndSendMessage() {
        System.out.println("Введите ваше сообщение: ");
        String textMessage = scan.nextLine();
        httpChat.sendMessage(new Message(userName, textMessage));
    }
}
