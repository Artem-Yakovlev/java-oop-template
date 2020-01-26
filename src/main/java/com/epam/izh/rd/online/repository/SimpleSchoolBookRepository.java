package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = ArrayUtils.add(schoolBooks, book);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] suitableSchoolBooks = new SchoolBook[0];
        for (SchoolBook schoolBook : schoolBooks) {
            if (schoolBook.getName().equalsIgnoreCase(name)) {
                suitableSchoolBooks = ArrayUtils.add(suitableSchoolBooks, schoolBook);
            }
        }
        return suitableSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int oldSize = schoolBooks.length;
        schoolBooks = Arrays.stream(schoolBooks)
                .filter(book -> !book.getName().equalsIgnoreCase(name))
                .toArray(SchoolBook[]::new);
        return oldSize > schoolBooks.length;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
