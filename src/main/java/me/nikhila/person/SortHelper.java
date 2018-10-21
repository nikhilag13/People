package me.nikhila.person;


import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;

public class SortHelper {


    /**
     *
     * @param dataType: DataType of object you are trying to compare Eg, String.class.
     * @param  method: method to invoked by comparator Eg, getSsn method in Person Class.
     *
     * @return Returns a comparator based on dataType and method.
     *
     *
     * */
    public Comparator findComparator(Class dataType, Method method) {

        Comparator comparator = null;

        if (dataType == String.class) {
            comparator = getStringComparator(method);
        } /*
        We can implement other data types, to cover all the data types.

       else if (dataType == Float.class || dataType == Float.TYPE) {
            comparator = getFloatCompartor(method);
        }
        */ else if (dataType == Double.class || dataType == Double.TYPE) {
            comparator = getDoubleComparator(method);
        } else if (dataType == Date.class) {
            comparator = getDateComparator(method);
        }
        else {
            throw new RuntimeException("Data Type is not supported yet !!!  " + dataType);
        }

        return comparator;
    }



    /**
     * @param method: Method that comparator needs to invoke.
     * @return Returns Boolean comparator which will invoke method on object to compare.
     * */
    private Comparator getDoubleComparator(Method method) {

        return new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                try{
                    return Double.compare((Double) method.invoke(o1) , (Double) method.invoke(o2));
                }
                catch (Exception e){
                    e.printStackTrace();
                    return -1;
                }
            }
        };

    }


    /**
     * @param method: Method that comparator needs to invoke.
     * @return Returns Date comparator which will invoke method on object to compare.
     * */
    private Comparator getDateComparator(Method method) {

        return new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                try{
                    return ((Date) method.invoke(o1)).compareTo((Date) method.invoke(o2));
                }
                catch (Exception e){
                    e.printStackTrace();
                    return -1;
                }
            }
        };

    }

    /**
     * @param method: Method that comparator needs to invoke.
     * @return Returns String comparator which will invoke method on object to compare.
     * */
    private Comparator getStringComparator(Method method) {

        return new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                try{
                    return ((String) method.invoke(o1)).compareTo((String) method.invoke(o2));
                }
                catch (Exception e){
                    e.printStackTrace();
                    return -1;
                }
            }
        };
    }


    public String getMethodName(String fieldName){

        return  "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

    }

}
