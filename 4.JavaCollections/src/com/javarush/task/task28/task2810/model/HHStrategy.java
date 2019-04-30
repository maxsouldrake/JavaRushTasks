package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        String vacancyQuery = "[data-qa='vacancy-serp__vacancy']";
        String titleQuery = "[data-qa='vacancy-serp__vacancy-title']";
        String compensationQuery = "[data-qa='vacancy-serp__vacancy-compensation']";
        String addressQuery = "[data-qa='vacancy-serp__vacancy-address']";
        String employerQuery = "[data-qa='vacancy-serp__vacancy-employer']";

        List<Vacancy> list = new ArrayList<>();
        int i = 0;
        Vacancy vacancy = new Vacancy();
        try{
            while (true) {
                Document document = getDocument(searchString, i);
                Elements vacanciesElements = document.select(vacancyQuery);
                if (!vacanciesElements.isEmpty()) {
                    for (Element element: vacanciesElements) {
                        vacancy.setTitle(element.select(titleQuery).text());
                        vacancy.setCity(element.select(addressQuery).text());
                        vacancy.setCompanyName(element.select(employerQuery).text());
                        vacancy.setUrl(element.select(titleQuery).attr("href"));
                        vacancy.setSiteName("https://hh.ua");
                        if (!element.select(compensationQuery).isEmpty()){
                            vacancy.setSalary(element.select(compensationQuery).text());
                        } else {
                            vacancy.setSalary("");
                        }
                        list.add(vacancy);
                    }
                } else {
                    break;
                }
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);

        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.117 Safari/537.36")
                .referrer("https://kiev.hh.ua/search/vacancy?text=java+%D0%BA%D0%B8%D0%B5%D0%B2")
                .get();
    }
}
