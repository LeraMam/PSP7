package mysqlapplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.Color;
import java.util.List;
import java.awt.event.WindowAdapter;
import javax.swing.*;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import static mysqlapplication.MySQLService.selectNotes;

public class SystemWindows extends JFrame implements ActionListener {
    private JTextField textFieldLastName, textFieldFirstName, textFieldMiddleName;
    private JTextField textFieldPhone, textFieldAddress;
    private JTextField textFieldBirthDate, textFieldEducationPlace, textFieldSearch;
    private JComboBox<String> choiceFaculty;
    private JRadioButton radioButtonMale, radioButtonFemale;
    private JButton buttonSubmit, buttonDelete, buttonSearch;
    private java.util.List<String> searchParams;
    private java.util.List<String> addParams;
    private java.util.List<String> deleteParams;
    private static JFrame frame;
    private static JTable table;

    public SystemWindows() {
    }

    public void addNoteForm(boolean isChange) throws ParseException {
        setTitle("Форма для заполнения данных абитуриента");
        setLayout(null);
        textFieldLastName = new JTextField();
        textFieldFirstName = new JTextField();
        textFieldMiddleName = new JTextField();
        textFieldPhone = new JTextField();
        textFieldAddress = new JTextField();
        textFieldEducationPlace = new JTextField();
        textFieldBirthDate = new JTextField();
        choiceFaculty = new JComboBox<>();
        radioButtonMale = new JRadioButton("Мужской");
        radioButtonFemale = new JRadioButton("Женский");
        ButtonGroup genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(radioButtonMale);
        genderButtonGroup.add(radioButtonFemale);
        MaskFormatter phoneMask = new MaskFormatter("+(###)##-###-##-##");
        JFormattedTextField textFieldPhone = new JFormattedTextField(phoneMask);
        MaskFormatter dateMask = new MaskFormatter("####-##-##");
        JFormattedTextField textFieldBirthDate = new JFormattedTextField(dateMask);
        textFieldAddress.setToolTipText("1990-10-11");
        JLabel labelLastName = new JLabel("Фамилия:");
        JLabel labelFirstName = new JLabel("Имя:");
        JLabel labelMiddleName = new JLabel("Отчество:");
        JLabel labelGender = new JLabel("Пол:");
        JLabel labelBirthDate = new JLabel("Дата рождения:");
        JLabel labelPhone = new JLabel("Номер телефона:");
        JLabel labelAddress = new JLabel("Адрес:");
        JLabel labelEducationPlace = new JLabel("Школа/ВУЗ:");
        JLabel labelFaculty = new JLabel("Факультет:");

        labelLastName.setBounds(50, 50, 60, 25);
        textFieldLastName.setBounds(120, 50, 100, 25);
        labelFirstName.setBounds(250, 50, 60, 25);
        textFieldFirstName.setBounds(320, 50, 100, 25);
        labelMiddleName.setBounds(450, 50, 60, 25);
        textFieldMiddleName.setBounds(520, 50, 100, 25);

        labelGender.setBounds(50, 100, 60, 25);
        radioButtonMale.setBounds(120, 100, 100, 25);
        radioButtonFemale.setBounds(230, 100, 100, 25);
        labelBirthDate.setBounds(350, 100, 100, 25);
        textFieldBirthDate.setBounds(450, 100, 150, 25);

        labelPhone.setBounds(50, 150, 110, 25);
        textFieldPhone.setBounds(170, 150, 150, 25);
        labelAddress.setBounds(340, 150, 60, 25);
        textFieldAddress.setBounds(400, 150, 200, 25);

        labelEducationPlace.setBounds(50, 200, 100, 25);
        textFieldEducationPlace.setBounds(160, 200, 200, 25);
        labelFaculty.setBounds(380, 200, 100, 25);
        choiceFaculty.setBounds(480, 200, 100, 25);

        add(labelLastName);
        add(textFieldLastName);
        add(labelFirstName);
        add(textFieldFirstName);
        add(labelMiddleName);
        add(textFieldMiddleName);
        add(labelGender);
        add(radioButtonMale);
        add(radioButtonFemale);
        add(labelBirthDate);
        add(textFieldBirthDate);
        add(labelPhone);
        add(textFieldPhone);
        add(labelAddress);
        add(textFieldAddress);
        add(labelEducationPlace);
        add(textFieldEducationPlace);
        add(labelFaculty);
        choiceFaculty.addItem("ФКП");
        choiceFaculty.addItem("ФИТУ");
        choiceFaculty.addItem("ФКСиС");
        choiceFaculty.addItem("ФИБ");
        choiceFaculty.addItem("ИЭФ");
        choiceFaculty.addItem("ВФ");
        add(choiceFaculty);
        buttonSubmit = new JButton("Отправить");
        buttonSubmit.setBounds(300, 250, 100, 30);
        add(buttonSubmit);
        if (!isChange) buttonSubmit.addActionListener(this);
        else buttonSubmit.addActionListener(change);
        setSize(700, 350);
        Color backgroundColor = new Color(253, 188, 180);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    public void deleteNoteForm(boolean isChange) {
        setTitle("Форма для операций с записью абитуриента");
        setLayout(null);
        textFieldLastName = new JTextField();
        textFieldFirstName = new JTextField();
        textFieldMiddleName = new JTextField();

        JLabel labelLastName = new JLabel("Фамилия:");
        JLabel labelFirstName = new JLabel("Имя:");
        JLabel labelMiddleName = new JLabel("Отчество:");

        labelLastName.setBounds(50, 50, 60, 25);
        textFieldLastName.setBounds(120, 50, 100, 25);
        labelFirstName.setBounds(250, 50, 60, 25);
        textFieldFirstName.setBounds(320, 50, 100, 25);
        labelMiddleName.setBounds(450, 50, 60, 25);
        textFieldMiddleName.setBounds(520, 50, 100, 25);

        add(labelLastName);
        add(textFieldLastName);
        add(labelFirstName);
        add(textFieldFirstName);
        add(labelMiddleName);
        add(textFieldMiddleName);

        buttonDelete = new JButton("Найти");
        buttonDelete.setBounds(300, 150, 100, 30);
        add(buttonDelete);
        if(!isChange) buttonDelete.addActionListener(delete);
        else buttonDelete.addActionListener(check);
        setSize(700, 250);
        Color backgroundColor = new Color(253, 188, 180);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    public void searchForm(boolean isName) {
        setTitle("Форма для поиска");
        setLayout(null);
        textFieldSearch = new JTextField();
        JLabel labelSearch = new JLabel("Ключевое слово:");
        labelSearch.setBounds(50, 50, 110, 25);
        textFieldSearch.setBounds(170, 50, 100, 25);
        add(labelSearch);
        add(textFieldSearch);

        buttonSearch = new JButton("Найти");
        buttonSearch.setBounds(100, 100, 100, 30);
        add(buttonSearch);
        if(isName) buttonSearch.addActionListener(searchName);
        else buttonSearch.addActionListener(searchFaculty);
        setSize(300, 200);
        Color backgroundColor = new Color(253, 188, 180);
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    public void ErrorWindow() {
        setTitle("Ошибка");
        setLayout(null);
        JLabel labelError1 = new JLabel("Приносим свои извинения");
        JLabel labelError2 = new JLabel("Данные не найдены");
        labelError1.setBounds(15, 50, 200, 30);
        labelError2.setBounds(30, 100, 200, 30);
        add(labelError1);
        add(labelError2);
        setSize(200, 200);
        Color backgroundColor = new Color(253, 188, 180);
        getContentPane().setBackground(backgroundColor);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int windowX = (screenWidth - windowWidth) / 2; // Горизонтальное расположение по центру
        int windowY = (screenHeight - windowHeight) / 2; // Вертикальное расположение по центру
        setLocation(windowX, windowY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void displayStudentData(String lastName, String faculty) {
        setTitle("Данные студентов");
        setLayout(new BorderLayout());
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        // Вызов функции выборки данных из базы данных и привязка результатов к таблице

        selectNotes(table, lastName, faculty);

        add(scrollPane, BorderLayout.CENTER);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Установка цвета фона окна
        Color backgroundColor = new Color(253, 188, 180);
        getContentPane().setBackground(backgroundColor);

        setVisible(true);
    }

    public void windowClosing(WindowEvent we) {this.dispose();}
    public void windowActivated(WindowEvent we) {}
    public void windowClosed(WindowEvent we) {}
    public void windowDeactivated(WindowEvent we) {}
    public void windowDeiconified(WindowEvent we) {}
    public void windowIconified(WindowEvent we) {}
    public void windowOpened(WindowEvent we) {}
    public void actionPerformed(ActionEvent e) {
        try {
            addParams = new ArrayList<>();
            addParams.add(textFieldLastName.getText());
            addParams.add(textFieldFirstName.getText());
            addParams.add(textFieldMiddleName.getText());
            if (radioButtonMale.isSelected()) addParams.add("Мужской");
            else  if (radioButtonFemale.isSelected()) addParams.add("Женский");
            addParams.add(textFieldPhone.getText());
            addParams.add(textFieldAddress.getText());
            addParams.add(textFieldBirthDate.getText());
            addParams.add(textFieldEducationPlace.getText());
            addParams.add((String) choiceFaculty.getSelectedItem());
            MySQLService.addNote(addParams);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
    ActionListener delete = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try{
                deleteParams = new ArrayList<>();
                deleteParams.add(textFieldLastName.getText());
                deleteParams.add(textFieldFirstName.getText());
                deleteParams.add(textFieldMiddleName.getText());
                MySQLService.deleteNote(deleteParams);
            }catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    };
    ActionListener check = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try{
                searchParams = new ArrayList<>();
                searchParams.add(textFieldLastName.getText());
                searchParams.add(textFieldFirstName.getText());
                searchParams.add(textFieldMiddleName.getText());
                boolean noteIsExist = false;
                if(!searchParams.isEmpty()) noteIsExist = MySQLService.checkNote(searchParams);
                if (noteIsExist) {
                    SystemWindows c = new SystemWindows();
                    boolean isChange = true;
                    c.addNoteForm(isChange);
                }
                if (!noteIsExist) {
                    SystemWindows c = new SystemWindows();
                    c.ErrorWindow();
                }
            }catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    };
    ActionListener change = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try{
                addParams = new ArrayList<>();
                addParams.add(textFieldLastName.getText());
                addParams.add(textFieldFirstName.getText());
                addParams.add(textFieldMiddleName.getText());
                if (radioButtonMale.isSelected()) addParams.add("Мужской");
                else  if (radioButtonFemale.isSelected()) addParams.add("Женский");
                addParams.add(textFieldPhone.getText());
                addParams.add(textFieldAddress.getText());
                addParams.add(textFieldBirthDate.getText());
                addParams.add(textFieldEducationPlace.getText());
                addParams.add((String) choiceFaculty.getSelectedItem());
                MySQLService.changeNote(addParams);
            }catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    };
    ActionListener searchName = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try{
                String param = textFieldSearch.getText();
                displayStudentData(param, null);
            }catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    };
    ActionListener searchFaculty = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try{
                String param = textFieldSearch.getText();
                displayStudentData(null, param);
            }catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
            }
        }
    };
}
