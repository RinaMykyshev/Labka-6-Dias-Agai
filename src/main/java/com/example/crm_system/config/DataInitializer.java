package com.example.crm_system.config;

import com.example.crm_system.entity.Courses;
import com.example.crm_system.entity.Operator;
import com.example.crm_system.repository.CoursesRepository;
import com.example.crm_system.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final CoursesRepository coursesRepository;
    private final OperatorRepository operatorRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("🔄 Начало инициализации данных...");

        if (coursesRepository.count() == 0) {
            Courses course1 = new Courses();
            course1.setName("Java Standard Edition");
            course1.setDescription("Основы программирования на Java");
            course1.setPrice(200);

            Courses course2 = new Courses();
            course2.setName("Java Enterprise Edition");
            course2.setDescription("Продвинутое Java программирование");
            course2.setPrice(300);

            Courses course3 = new Courses();
            course3.setName("Spring Framework");
            course3.setDescription("Фреймворк Spring для enterprise приложений");
            course3.setPrice(250);

            Courses course4 = new Courses();
            course4.setName("Web Development");
            course4.setDescription("HTML, CSS, JavaScript");
            course4.setPrice(150);

            coursesRepository.saveAll(Arrays.asList(course1, course2, course3, course4));
            log.info("✅ Тестовые курсы добавлены в базу данных");
        }


        if (operatorRepository.count() == 0) {
            Operator op1 = new Operator();
            op1.setName("Ренат");
            op1.setSurname("Красавчик");
            op1.setDepartment("Продажа");

            Operator op2 = new Operator();
            op2.setName("Ренат");
            op2.setSurname("Лучший");
            op2.setDepartment("Рекрутинг");

            Operator op3 = new Operator();
            op3.setName("Ренат");
            op3.setSurname("Молодец");
            op3.setDepartment("Маркетинг");

            Operator op4 = new Operator();
            op4.setName("Ренат");
            op4.setSurname("Гений");
            op4.setDepartment("ИТ");

            Operator op5 = new Operator();
            op5.setName("Ренат");
            op5.setSurname("Супер");
            op5.setDepartment("Администрация");

            operatorRepository.saveAll(Arrays.asList(op1, op2, op3, op4, op5));
            log.info("✅ Тестовые операторы добавлены в базу данных");
        }

        log.info("🎉 Инициализация данных завершена!");
    }
}