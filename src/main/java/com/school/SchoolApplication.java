package com.school;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Додаток для роботи з базою даних шкільного журналу.
 */
@SpringBootApplication
public class SchoolApplication implements CommandLineRunner {

    @Autowired
    private JournalRepository journalRepository;

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- МЕНЮ ШКІЛЬНОГО ЖУРНАЛУ ---");
            System.out.println("1. Завантажити журнал з CSV-файлу (school.csv)");
            System.out.println("2. Переглянути всі записи журналу");
            System.out.println("3. Очистити журнал (видалити всі записи)");
            System.out.println("4. Вихід");
            System.out.print("Введіть номер команди (1-4): ");
            
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера

                switch (choice) {
                    case 1:
                        addJournalFromCsv();
                        break;
                    case 2:
                        viewAllJournals();
                        break;
                    case 3:
                        dropAllJournals();
                        break;
                    case 4:
                        System.out.println("Робота завершена.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Номер команди некоректний. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Будь ласка, введіть число.");
                scanner.next(); // пропустити некоректний ввід
            }
        }
    }

    private void addJournalFromCsv() {
        // Зверніть увагу: файл має називатися school.csv і лежати в папці resources
        try (CSVReader reader = new CSVReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("school.csv"),
        StandardCharsets.UTF_8))) {
            List<String[]> records = reader.readAll();

            if (records.isEmpty()) {
                System.out.println("CSV файл порожній.");
                return;
            }

            records.remove(0); // Видалити перший рядок з заголовками (Student, Clazz, etc.)

            List<Journal> journalList = new ArrayList<>();
            for (String[] record : records) {
                // Переконайтеся, що порядок тут відповідає порядку колонок у вашому school.csv
                Journal journalEntry = new Journal(
                    record[0],  // Student (ПІБ учня)
                    record[1],  // Clazz (Клас)
                    record[2],  // Teacher (ПІБ вчителя)
                    record[3],  // Subject (Предмет)
                    record[4],  // Date (Дата)
                    record[5],  // Mark (Оцінка)
                    record[6],  // Teacher_level (Категорія)
                    record[7],  // School (Школа)
                    record[8],  // Adress (Адреса школи)
                    record[9],  // Phone (Телефон)
                    record[10]  // Director (Директор)
                );

                journalList.add(journalEntry);
            }

            journalRepository.saveAll(journalList);
            System.out.println("Успішно завантажено " + journalList.size() + " записів до журналу.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Помилка при завантаженні файлу school.csv. Перевірте, чи існує файл в папці resources.");
        }
    }

    private void viewAllJournals() {
        List<Journal> journals = journalRepository.findAll();
        if (journals.isEmpty()) {
            System.out.println("Журнал порожній. Спочатку завантажте дані (пункт 1).");
        } else {
            System.out.println("Знайдено " + journals.size() + " записів:");
            // Виводить дані, використовуючи метод toString() з класу journal
            journals.forEach(System.out::println);
        }
    }

    private void dropAllJournals() {
        long count = journalRepository.count();
        journalRepository.deleteAll();
        System.out.println("Видалено " + count + " записів. База даних очищена.");
    }
}