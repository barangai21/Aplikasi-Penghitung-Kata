package penghitungkataapp;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;

public class PenghitungKataApp {

    private JFrame frame;
    private JTextArea textArea;
    private JLabel wordCountLabel;
    private JLabel charCountLabel;
    private JLabel sentenceCountLabel;
    private JLabel paragraphCountLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PenghitungKataApp window = new PenghitungKataApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public PenghitungKataApp() {
        frame = new JFrame("Aplikasi Penghitung Kata");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // Panel untuk menampung komponen input dan hasil
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // JTextArea untuk memasukkan teks
        textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        
        // Menambahkan JScrollPane untuk text area
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan panel ke frame
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        // Panel untuk menampilkan hasil perhitungan
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayout(4, 1));

        // Label untuk menampilkan hasil perhitungan
        wordCountLabel = new JLabel("Jumlah Kata: 0");
        charCountLabel = new JLabel("Jumlah Karakter: 0");
        sentenceCountLabel = new JLabel("Jumlah Kalimat: 0");
        paragraphCountLabel = new JLabel("Jumlah Paragraf: 0");

        resultPanel.add(wordCountLabel);
        resultPanel.add(charCountLabel);
        resultPanel.add(sentenceCountLabel);
        resultPanel.add(paragraphCountLabel);

        // Menambahkan panel hasil ke frame
        frame.getContentPane().add(resultPanel, BorderLayout.SOUTH);

        // Tombol untuk menghitung jumlah kata, kalimat, dll
        JButton hitungButton = new JButton("Hitung");
        hitungButton.addActionListener(e -> calculateTextStats());
        frame.getContentPane().add(hitungButton, BorderLayout.NORTH);

        // Menambahkan DocumentListener untuk menghitung secara real-time
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateStats();
            }
            public void removeUpdate(DocumentEvent e) {
                updateStats();
            }
            public void changedUpdate(DocumentEvent e) {
                updateStats();
            }
        });
    }

    // Fungsi untuk menghitung dan memperbarui statistik teks
    private void calculateTextStats() {
        String text = textArea.getText();

        // Menghitung jumlah kata
        int wordCount = countWords(text);

        // Menghitung jumlah karakter
        int charCount = countCharacters(text);

        // Menghitung jumlah kalimat
        int sentenceCount = countSentences(text);

        // Menghitung jumlah paragraf
        int paragraphCount = countParagraphs(text);

        // Memperbarui label dengan hasil perhitungan
        wordCountLabel.setText("Jumlah Kata: " + wordCount);
        charCountLabel.setText("Jumlah Karakter: " + charCount);
        sentenceCountLabel.setText("Jumlah Kalimat: " + sentenceCount);
        paragraphCountLabel.setText("Jumlah Paragraf: " + paragraphCount);
    }

    // Fungsi untuk menghitung jumlah kata dalam teks
    private int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    // Fungsi untuk menghitung jumlah karakter dalam teks
    private int countCharacters(String text) {
        return text.replaceAll("\\s", "").length();
    }

    // Fungsi untuk menghitung jumlah kalimat dalam teks
    private int countSentences(String text) {
        String[] sentences = text.split("[.!?]\\s*");
        return sentences.length;
    }

    // Fungsi untuk menghitung jumlah paragraf dalam teks
    private int countParagraphs(String text) {
        String[] paragraphs = text.split("\n+");
        return paragraphs.length;
    }

    // Fungsi untuk memperbarui statistik secara real-time
    private void updateStats() {
        String text = textArea.getText();

        // Menghitung jumlah kata
        int wordCount = countWords(text);

        // Menghitung jumlah karakter
        int charCount = countCharacters(text);

        // Menghitung jumlah kalimat
        int sentenceCount = countSentences(text);

        // Menghitung jumlah paragraf
        int paragraphCount = countParagraphs(text);

        // Memperbarui label dengan hasil perhitungan
        wordCountLabel.setText("Jumlah Kata: " + wordCount);
        charCountLabel.setText("Jumlah Karakter: " + charCount);
        sentenceCountLabel.setText("Jumlah Kalimat: " + sentenceCount);
        paragraphCountLabel.setText("Jumlah Paragraf: " + paragraphCount);
    }
}