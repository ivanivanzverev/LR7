import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void run() {
        //Создадим окно и установим заголовок
        final JFrame window = new JFrame("Matrix_1 + Matrix_2 = Matrix_3");

        //Событие "закрыть" при нажатии по крестику окна
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //На весь экран
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Создадим панель
        JPanel panel = new JPanel();

        //Создадим кнопки
        JButton add_col_Button = new JButton("Добавить столбец");
        JButton add_row_Button = new JButton("Добавить строку");
        JButton plus_Button = new JButton("Сложить");

        //Матрицы
        String[] col_1 = {""};
        int numRow_1 = 1;
        DefaultTableModel model_1 = new DefaultTableModel(numRow_1, col_1.length) ;
        model_1.setColumnIdentifiers(col_1);

        JTable table1 = new JTable(model_1);
        table1.setBackground(Color.WHITE);

        String[] col_2 = {""};
        int numRow_2 = 1;
        DefaultTableModel model_2 = new DefaultTableModel(numRow_2, col_2.length) ;
        model_2.setColumnIdentifiers(col_2);

        JTable table2 = new JTable(model_2);
        table2.setBackground(Color.WHITE);

        String[] col_3 = {""};
        int numRow_3 = 1;
        DefaultTableModel model_3 = new DefaultTableModel(numRow_3, col_3.length) ;
        model_3.setColumnIdentifiers(col_3);

        JTable table3 = new JTable(model_3);
        table3.setBackground(Color.WHITE);

        //Событие для кнопки "Добавить столбец"
        add_col_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model_1.addColumn(col_1);
                model_2.addColumn(col_2);
                model_3.addColumn(col_3);
            }
        });

        //Событие для кнопки "Добавить строку"
        add_row_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model_1.addRow(col_1);
                model_2.addRow(col_2);
                model_3.addRow(col_3);
            }
        });

        //Событие для кнопки "Сложить"
        plus_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Map<Integer, Double> matrix1 = new HashMap<Integer, Double>();//матрица 1
                int count1 = 0;
                for(int j = 0; j < model_1.getColumnCount(); j++)
                    for (int i = 0; i < model_1.getRowCount(); i++) {
                        matrix1.put(count1, Double.parseDouble(model_1.getValueAt(i, j).toString()));
                        count1 = count1 + 1;
                    }

                Map<Integer, Double> matrix2 = new HashMap<Integer, Double>();//матрица 1
                int count2 = 0;
                for(int j = 0; j < model_2.getColumnCount(); j++) {
                    for (int i = 0; i < model_2.getRowCount(); i++) {
                        matrix2.put(count2, Double.parseDouble(model_2.getValueAt(i, j).toString()));
                        count2 = count2 + 1;
                    }
                }

                Matrix mx = new Matrix();
                Map<Integer, String> matrix3 = mx.education(matrix1, matrix2);
                int count3 = 0;
                for(int j = 0; j < model_3.getColumnCount(); j++) {
                    for (int i = 0; i < model_3.getRowCount(); i++) {
                        model_3.setValueAt(matrix3.get(count3),i, j);
                        count3 = count3 + 1;
                    }
                }


            }
        });

        //Добавим кнопки и поля на панель
        panel.add(add_col_Button);
        panel.add(add_row_Button);
        panel.add(plus_Button);
        panel.add(table1);
        panel.add(table2);
        panel.add(table3);

        //Добавим панель в окно
        window.getContentPane().add(panel);
        window.pack();

        //Разместим программу по центру
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    //Запускаем
    public static void main(String[] args) {
        run();
    }
}