package com.school;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class MainApp {

    private static final Logger log = LoggerFactory.getLogger(MainApp.class);

    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        try {
            mainApp.setup();
            mainApp.insertSampleData();
            mainApp.queryAndLogData();
        } catch (Exception e) {
            log.error("Ошибка в MainApp: ", e);
        } finally {
            mainApp.shutdown();
        }
    }

    protected void setup() {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
        log.info("SessionFactory успешно создан.");
    }

    protected void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
            log.info("SessionFactory успешно закрыт.");
        }
    }

    protected void insertSampleData() {
        log.info("--- НАЧАЛО ВСТАВКИ ДАННЫХ ---");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            School school1 = new School();
            school1.setName("Ліцей №1");
            school1.setCity("Київ");
            session.save(school1);

            SchoolClass classA = new SchoolClass();
            classA.setName("10-А");
            classA.setSchool(school1);
            session.save(classA);

            Teacher teacher1 = new Teacher();
            teacher1.setName("Петренко Галина Іванівна");
            teacher1.setLvl("Вищий");
            teacher1.setSchool(school1);
            session.save(teacher1);

            Student student1 = new Student();
            student1.setName("Іваненко Іван");
            student1.setSchoolClass(classA);
            session.save(student1);

            Journal journalEntry1 = new Journal();
            journalEntry1.setSubject("Математика");
            journalEntry1.setRating("5");
            journalEntry1.setDate(LocalDate.now());
            journalEntry1.setStudent(student1);
            journalEntry1.setTeacher(teacher1);
            session.save(journalEntry1);

            Journal journalEntry2 = new Journal();
            journalEntry2.setSubject("Фізика");
            journalEntry2.setRating("4");
            journalEntry2.setDate(LocalDate.now().minusDays(1));
            journalEntry2.setStudent(student1);
            journalEntry2.setTeacher(teacher1);
            session.save(journalEntry2);

            tx.commit();
            log.info("--- ДАННЫЕ УСПЕШНО ВСТАВЛЕНЫ ---");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.error("Ошибка при вставке данных: ", e);
        } finally {
            session.close();
        }
    }

    protected void queryAndLogData() {
        log.info("--- РЕЗУЛЬТАТЫ ВЫБОРКИ (ДЕНОРМАЛИЗОВАННАЯ ТАБЛИЦА) ---");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            String hql = "SELECT new com.school.ScheduleInfoDTO(" +
                         "s.name, " +
                         "sc.name, " +
                         "array_to_string(j.subject, ', '), " +
                         "j.rating, " +
                         "t.name) " +
                         "FROM Journal j " +
                         "JOIN j.student s " +
                         "JOIN s.schoolClass sc " +
                         "JOIN j.teacher t";

            Query<ScheduleInfoDTO> query = session.createQuery(hql, ScheduleInfoDTO.class);
            List<ScheduleInfoDTO> results = query.getResultList();

            tx.commit();

            if (results.isEmpty()) {
                log.warn("Выборка не дала результатов. (Это нормально, если в БД нет данных)");
            } else {
                for (ScheduleInfoDTO dto : results) {
                    log.info(dto.toString()); 
                }
            }
            log.info("--- КОНЕЦ ВЫБОРКИ (Найдено записей: " + results.size() + ") ---");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            log.error("Ошибка при выполнении запроса: ", e);
        } finally {
            session.close();
        }
    }
}