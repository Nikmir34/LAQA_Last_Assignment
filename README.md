# Liga_Aqa_Java_Course
**Инструкции по запуску**

1. Склонируйте репозиторий проекта с помощью команды git clone <URL репозитория>.
2. Установите необходимые зависимости, указанные в файле pom.xml.
3. Запустите автотесты с помощью команды mvn clean test.
4. Для формирования Allure-отчета выполните команду mvn allure:serve.

# Описание проекта
Автотесты написаны на стенда http://172.24.120.5:8081/

В проекте реализованы тесты для:
 * Регистрации:
   * UI - https://disk.yandex.ru/i/K9R2niMSeU0nAw
   * API - https://disk.yandex.ru/i/ZXUeGLFIUqnjlA
 * Авторизации:
   * UI - https://disk.yandex.ru/i/gwRveaGAm706-Q
   * API - https://disk.yandex.ru/i/mZW44xPR6HIi3Q
 * Работа с заметками:
   * UI - https://disk.yandex.ru/i/ML-HPutdsjqbxw
   * API - https://disk.yandex.ru/i/PhX1STg83z1Ngg

# Отчет

**Работа с отчётами**

    1. Выполните в терминале следующую команду: mvn allure:serve
        1.1 Или Откройте боковую панель Idea с названием Maven
        1.2 Раскройте список Plugins
        1.3 Раскройте список команд для плагина allure
        1.4 Двойным кликом запустите команду allure:serve

    2. Если отчет не открывается из-за VPN, то нужно найти в терминале его адрес И заменить IP на localhost
    