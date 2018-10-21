package me.nikhila.person;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortPerson {


    public static void main(String[] args) {

        List<Person> l = new ArrayList<Person>();
        try {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            l.add(new Person("1", sdf.parse("2018-01-01"), "adam",
                    "zen", 3.4, 192.9));
            l.add(new Person("1", sdf.parse("2018-01-01"), "brad",
                    "yen", 4.4, 192.9));
            l.add(new Person("1", sdf.parse("2018-01-01"), "collar",
                    "xero", 5.4, 192.9));
            l.add(new Person("22", sdf.parse("2015-01-01"), "axd",
                    "y", 6.1, 172.6));
            l.add(new Person("3", sdf.parse("2011-01-01"), "xs",
                    "z", 6.6, 122.1));
            l.add(new Person("3323", sdf.parse("1988-01-01"), "z",
                    "d", 5.0, 152.97));
            l.add(new Person("232", sdf.parse("2015-01-01"), "bdcs",
                    "a", 4.6, 102.4));

        }
        catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(sort(l, "ssn", "false"));
        System.out.println(sort(l, "ssn", "true"));
        System.out.println(sort(l, "dateOfBirth", "false"));
        System.out.println(sort(l, "firstName", "false"));
        System.out.println(sort(l, "heightIn", "false"));

    }


    /**
     * @param people: Iterable of person objects
     * @param  sortField: Field to sort by in person object
     * @param ascending: Order of sort
     *
     * @return Sorted List of person objects based on input parameters.
     * */
    public static List<Person> sort(Iterable<Person> people, String sortField, String ascending) {


        // If empty list
        if(sortField == null || sortField.length() < 1)
            return new ArrayList<Person>();

        try {
            List<Person> l = new ArrayList<>();

            for(Person e: people) {
                l.add(e);
            }

            if(l.size() <= 1) {
                // No need to sort if size <= 1
                return l;
            }

            SortHelper sortHelper = new SortHelper();
            Class aClass = Person.class;
            String methodName = sortHelper.getMethodName(sortField);
            Method getterMethod = aClass.getMethod(methodName);

            //Get comparator from helper class based on field that we need to sort on.
            Comparator<Person> comparator =  sortHelper.findComparator(getterMethod.getReturnType(), getterMethod);

            /*
            We can chain comparators if we want to sort by more than one field
            sortField = "firstName";
            String methodName2 = sortHelper.getMethodName(sortField);

            Method getterMethod2 = aClass.getMethod(methodName2);

            Comparator comparatorFirstName = sortHelper.findComparator(getterMethod2.getReturnType(), getterMethod2);

            Comparator comparatorFinal = comparator.thenComparing(comparatorFirstName);

            */


            //Handle ascending and descending sort functions
            if(ascending.equalsIgnoreCase("false"))

                l.sort(comparator);
            else
                l.sort(comparator.reversed());

            return l;
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
