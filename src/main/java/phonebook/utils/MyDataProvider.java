package phonebook.utils;

import org.testng.annotations.DataProvider;
import phonebook.model.Contact;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider
    public Iterator<Object[]> addNewContact(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Oliver","Kan","1234567890","kan@gmail.com","Berlin","goalkeeper"});
        list.add(new Object[]{"Oliver1","Kan","12345678901","kan@gmail.com","Berlin","goalkeeper"});
        list.add(new Object[]{"Oliver2","Kan","123456789012345","kan@gmail.com","Berlin","goalkeeper"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]>addNewContactFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/сontact.csv")));

        String line = reader.readLine();
        while (line !=null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact().setName(split[0]).setLastName(split[1]).setPhone(split[2])
                    .setEmail(split[3]).setAddress(split[4]).setDesc(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}

// dataProvider - для того чтобы не плодить 1 метод в коде много раз, а для того, чтобы одним методом подтянуть
// весь массив из таблицы .csv и за один раз выполнить тест

