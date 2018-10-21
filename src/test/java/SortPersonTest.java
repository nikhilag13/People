
import me.nikhila.person.Person;
import me.nikhila.person.SortPerson;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SortPersonTest{

    private List<Person> listPerson = new ArrayList<>();

    @Before
    public void setUp() {

        try {

            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            listPerson.add(new Person("1", sdf.parse("2018-01-01"), "adam",
                    "zen", 3.4, 192.9));
            listPerson.add(new Person("2", sdf.parse("1998-01-01"), "brad",
                    "yen", 4.4, 12.9));
            listPerson.add(new Person("3", sdf.parse("2017-01-01"), "collar",
                    "san", 5.4, 192.9));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @Test
    public void sortTest(){

        List<Person> sortedList = SortPerson.sort(listPerson, "ssn", "true");

        assertTrue("Size should be same before and after", listPerson.size()==sortedList.size());

        boolean isInOrder = checkOrder(sortedList,true, "ssn");
        assertTrue("Should be in ascending order by ssn ", isInOrder);

        sortedList = SortPerson.sort(listPerson, "dateOfBirth", "false");
        isInOrder = checkOrder(sortedList,false, "dateOfBirth");
        assertTrue("Should be descending order by date of birth ", isInOrder);

        sortedList = SortPerson.sort(listPerson, "firstName", "false");
        isInOrder = checkOrder(sortedList,false, "firstName");
        assertTrue("Should be descending order for first name", isInOrder);


        sortedList = SortPerson.sort(listPerson, "heightIn", "false");
        isInOrder = checkOrder(sortedList,false, "heightIn");
        assertTrue("Should be descending order by height", isInOrder);

    }

    private boolean checkOrder(List<Person> sortedList, boolean asc, String fieldName){
        try{
        Class aClass = Person.class;
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method getterMethod = aClass.getMethod(methodName);
            for(int i =0; i < sortedList.size()-1; i++){

                if(getterMethod.getReturnType() == String.class) {
                    int res = ((String)getterMethod.invoke(sortedList.get(i))).compareTo( ((String) getterMethod.invoke(sortedList.get(i+1))));
                    if ( asc && res < 0)
                        return false;
                    if( !asc && res > 0 )
                        return false;
                }

                else if(getterMethod.getReturnType() == Double.class) {
                    int res = ((Double)getterMethod.invoke(sortedList.get(i))).compareTo(((Double) getterMethod.invoke(sortedList.get(i+1))));
                    if ( asc && res < 0)
                        return false;
                    if( !asc && res > 0 )
                        return false;
                }
                else if(getterMethod.getReturnType() == Date.class) {
                    int res = ((Date)getterMethod.invoke(sortedList.get(i))).compareTo(((Date) getterMethod.invoke(sortedList.get(i+1))));
                    if ( asc && res < 0)
                        return false;
                    if( !asc && res > 0 )
                        return false;
                }
            }

            return true;

    }
    catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }



}
