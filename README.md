# Person Sort

Solution for exercise is implemented in SortPerson class.
static List<Person> sort(Iterable<Person> people, String sortField, String ascending)

## Tested solution using simple JUNIT test cases.
    Verified using basic test cases and sorting sample list of person objects

    To execute test cases
        mvn test

    To build and install
        mvn clean install

## Desing:
    Used reflection api to decide type of comparator (Integer,Double, Date, String) for sorting objects.
    By using reflection api, even if there is any new fields added to Person object, its taken care by SortHelper.
    Reason for using reflection api is ability to extend easily to any new objects or addition of new attributes.

    Using this design, its easy to chain multiple comparators to sort by multiple fields.
    Eg Sort by firstName Ascending order and then by DateOfBirth Descending.

