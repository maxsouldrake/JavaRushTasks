package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);


            //  ПИШЕМ ДАННЫЕ О ПОЛЬЗОВАТЕЛЯХ В ФАЙЛ
//---------------------------------------------------------------------------------------------------------------
            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            //  Первый пользователь
            User user = new User();

            user.setFirstName("Sergey");
            user.setLastName("Gey");
            user.setCountry(User.Country.OTHER);
            user.setMale(true);
            Date date = new Date(1998,5,21);
            user.setBirthDate(date);

            //  Второй пользователь
            User user1 = new User();

            user1.setFirstName("Roman");
            user1.setLastName("Bubnov");
            user1.setCountry(User.Country.OTHER);
            user1.setMale(false);
            Date date1 = new Date(1998,7,17);
            user1.setBirthDate(date1);

            //  Добавляем пользователей в список
            javaRush.users.add(user);
            javaRush.users.add(user1);


            //  Сохраняем информацию о пользователях в файл
            javaRush.save(outputStream);
            outputStream.flush();


            //    ЧИТАЕМ ИНФОРМАЦИЮ О ПОЛЬЗОВАТЕЛЯХ ИЗ ФАЙЛА
//--------------------------------------------------------------------------------------------------------------
            //  Объект, в поле users которого будет хранится информация,
            //  считанная из файла.
            JavaRush loadedObject = new JavaRush();

            //  Читаем инфомрацию о сохраненных пользователях из файла,
            //  записываем её в объект loadedObject
            loadedObject.load(inputStream);

            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();
//--------------------------------------------------------------------------------------------------------------

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);

            //  Если список пользователей не пустой, - пишем информацию о них в файл
            //  если список пустой - ничего не пишем в файл.
            if(users.size() != 0){
                for(User user : users) {
                    printWriter.println(user.getFirstName());
                    printWriter.println(user.getLastName());
                    printWriter.println(user.getCountry());
                    printWriter.println(user.isMale());
                    printWriter.println(user.getBirthDate().getTime());
                }
            }

            printWriter.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //  Если список пользователей пуст - читаем информацию о пользователях
            //  из файла и добавляем этих пользователей в список.
            if(users.size() == 0) {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    //  Имя
                    String firstName = line;
                    //  Фамилия
                    String lastName = reader.readLine();
                    //  Страна
                    String country = reader.readLine();
                    User.Country enumCountry = null;
                    switch (country) {
                        case "UKRAINE":
                            enumCountry = User.Country.UKRAINE;
                            break;
                        case "RUSSIA":
                            enumCountry = User.Country.RUSSIA;
                            break;
                        case "OTHER":
                            enumCountry = User.Country.OTHER;
                            break;
                    }
                    //  Пол
                    String sex = reader.readLine();
                    boolean gender = true;
                    switch (sex) {
                        case "true":
                            gender = true;
                            break;
                        case "false":
                            gender = false;
                            break;
                    }
                    //  Дата рождения
                    String date = reader.readLine();
                    long longDate = Long.parseLong(date);
                    Date birthDate = new Date(longDate);


                    //  Создаем пользователя и задаем значения его переменным,
                    //  информацию о которых мы считали из файла.
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setCountry(enumCountry);
                    user.setMale(gender);
                    user.setBirthDate(birthDate);

                    //  Добавляем пользователя в список
                    users.add(user);
                }
            }

            reader.close();
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
