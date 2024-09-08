# Тестовое задание
Этот проект представляет собой тестовое задание, в котором использованы следующие технологии и инструменты:

- **Kotlin** - язык программирования.
- **Gradle** - система сборки.
- **JUnit 5** - фреймворк для написания тестов.
- **RestAssured** - библиотека для тестирования REST API.
- **Allure** - система для создания отчетов о тестировании.
- **Gson** - библиотека для работы с JSON.

## Запуск тестов
1. Клонируйте репозиторий:
   ```
   git clone https://github.com/amkaznov/jsonplaceholder_test_task.git
   ```
2. Соберите проект и запустите тесты:
   ```
   gradle build
   ```
      ```
   gradle test
   ```
3. Создайте отчет Allure:
   ```
   gradle allureServe
   ```
   Отчет будет создан в директории build/reports/allure-report.

## Примечания
- Убедитесь, что у вас установлены все необходимые инструменты и их версии соответствуют указанным в `build.gradle.kts`.
- Для работы с Allure необходимо установить Allure Commandline, следуйте [официальной документации](https://docs.qameta.io/allure/) для установки и настройки.

## Задание 2 - Топ 10 слов 

В классе GetTopWords есть тест Get top words. Для того чтобы получить результат нужно запустииь тест через ide и в консоле отобразится список 
<img width="214" alt="cons" src="https://github.com/user-attachments/assets/5585544a-b8ca-4aa6-a6cd-019841e6efcc">
