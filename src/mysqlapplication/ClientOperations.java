package mysqlapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class ClientOperations extends JFrame {
    private boolean isRunning = true;

    public static void main(String[] args) {
        ClientOperations window = new ClientOperations();
        window.run();
    }

    public void run() {
        setVisible(true);
        while (isRunning) {
            // Здесь можно вставить код, но все все равно обрабатывается через события
        }
    }

    public ClientOperations() {
        setTitle("Окно выбора операций");
        setSize(300, 200);
        setLayout(new FlowLayout());

        JButton addButton = new JButton("Добавить");
        JButton editButton = new JButton("Изменить");
        JButton deleteButton = new JButton("Удалить");
        JButton selectButton = new JButton("Читать");
        JButton exitButton = new JButton("Завершить работу");

        addButton.addActionListener(add);
        editButton.addActionListener(change);
        deleteButton.addActionListener(delete);
        selectButton.addActionListener(select);
        exitButton.addActionListener(exit);

        add(addButton);
        add(editButton);
        add(deleteButton);
        add(selectButton);
        add(exitButton);

        Color backgroundColor = new Color(253, 188, 180);
        getContentPane().setBackground(backgroundColor);
    }

    ActionListener add = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            SystemWindows c = new SystemWindows();
            boolean isChange = false;
            try {
                c.addNoteForm(isChange);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    };

    ActionListener change = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            SystemWindows c = new SystemWindows();
            boolean isChange = true;
            c.deleteNoteForm(isChange);
        }
    };

    ActionListener delete = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            SystemWindows c = new SystemWindows();
            boolean isChange = false;
            c.deleteNoteForm(isChange);
        }
    };

    ActionListener select = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            SystemWindows c = new SystemWindows();
            c.displayStudentData();
        }
    };

    ActionListener exit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            isRunning = false;
            System.exit(0);
        }
    };
}