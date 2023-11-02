package mysqlapplication;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MySQLService {
    private static final String url = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String username = "root";
    private static final String password = "LeraMam";
    private static List<String>  searchParams = new ArrayList<>();;
    public static void selectNotes(JTable table, String lastName, String faculty) {
        String sql = "SELECT * FROM students";
        // Добавление условий выборки по фамилии и по факультету, если они указаны
        if (lastName != null) {
            sql += " WHERE last_name = ?";
        }
        if (faculty != null) {
            if (lastName != null) {
                sql += " AND";
            } else {
                sql += " WHERE";
            }
            sql += " faculty = ?";
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            // Установка значений параметров выборки
            if (lastName != null) {
                statement.setString(1, lastName);
            }
            if (faculty != null) {
                statement.setString(lastName != null ? 2 : 1, faculty);
            }
            ResultSet resultSet = statement.executeQuery();
            DefaultTableModel tableModel = new DefaultTableModel();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                tableModel.addColumn(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getString(i);
                }
                tableModel.addRow(rowData);
            }
            table.setModel(tableModel);
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void addNote(List<String> params) {
        String sql = "INSERT students(last_name, first_name, " +
                "middle_name, gender, phone, address, birth_date, education_place, faculty) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try(Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                String param = params.get(i);
                Object value = param;

                if (i == 6) {
                    value = dateFormat.parse(param);
                }

                statement.setObject(i + 1, value);
            }

            int rows = statement.executeUpdate();
            System.out.printf("Добавлено %d строк", rows);

        } catch (SQLException e) {
            System.err.println("Произошла ошибка при выполнении SQL-запроса");
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Ошибка при парсинге даты");
            e.printStackTrace();
        }
    }

    public static void deleteNote(List<String> params){
        String sql = "DELETE FROM students WHERE  last_name = ? AND first_name = ? " +
                "AND middle_name = ? " ;
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
            }
            int rows = statement.executeUpdate();
            System.out.printf("Удалено %d строк", rows);
            statement.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static boolean checkNote(List<String> params){
        boolean noteIsExist = false;
        String sql = "SELECT * FROM students WHERE  last_name = ? AND first_name = ? " +
                "AND middle_name = ?" ;
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                statement.setString(i + 1, params.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                noteIsExist = true;
                for(String element : params){
                    searchParams.add(element);
                }
            } else {
                noteIsExist = false;
            }
            statement.close();
            connection.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return noteIsExist;
    }
    public static void changeNote(List<String> params){
        int parameterCount = 0;
        String sql = "UPDATE students SET last_name = ?, first_name= ?, middle_name = ?, " +
                "gender = ?, phone = ?, address = ?, birth_date = ?, education_place = ?, faculty = ?" +
                "WHERE last_name = ? AND first_name = ? AND middle_name = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                String param = params.get(i);
                Object value = param;
                if(i == 6){
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    value = dateFormat.parse(param);
                }
                statement.setObject(i + 1, value);
                parameterCount++;
            }
            for (int i = 0; i < searchParams.size(); i++) {
                statement.setObject(parameterCount + 1, searchParams.get(i));
                parameterCount++;
            }
            int rows = statement.executeUpdate();
            System.out.printf("Изменено %d строк", rows);
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
