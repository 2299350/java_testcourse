package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePassword() {
    String userName = "user13";
    app.session().loginAdminUI(); // Login as an admin
    app.session().chooseUser(userName);

    /*>> Логинимся под админом
      >> Заходим на http://localhost:8080/mantisbt-2.22.1/manage_user_page.php
      >> Выбрать пользователя по username и кликнуть на него
      >> На открывшейся странице нажать кнопку <input type="submit" class="btn btn-primary btn-white btn-round" value="Сбросить пароль">

      >> Получить письмо
      >> Извлечь из него ссылку для смены пароля
      >> Пройти по этой ссылке и изменить пароль

      >> Залогиниться под новыми учетными данными*/
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
