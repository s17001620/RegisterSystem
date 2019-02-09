# RegisterSystem
		A program created to emulate elements of the
        University’s student records system, allowing the creation of
        individual profiles incorporating name, course and attendance.
        Any data structures will follow object-orientated patterns, and
        the system should be accompanied by an appropriate class
        diagram.

## Application
This is a Spring Boot application wit JavaFX frontend.

### Prerequisites
It is built with: 
- OpenJDK 11  (https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
- OpenJFX 11  (https://gluonhq.com/products/javafx/)
- Maven 3.6.0 (https://maven.apache.org/download.cgi)
- NetBeans 10 (https://netbeans.apache.org/download/nb100/nb100.html)
- Scene Builder 9 (https://gluonhq.com/products/scene-builder/) (optional)

#### Netbeans IDE Setup
- set path to jdk in in netbeans.conf netbeans_jdkhome="<path_to_jdk>"
- set Scene Builder Home Tools -> Options -> JavaFX -> Scene Builder Home
- set up JavaFX 11 see https://openjfx.io/openjfx-docs/#IDE-NetBeans
- Project -> Properties -> Run add VM options:

	--module-path <path to javafx-sdk\lib>
	--add-modules=javafx.controls,javafx.fxml,javafx.graphics,javafx.base,javafx.media

#### Build
	mvn update
	mvn clean install 

#### Start application
	java -jar RegisterSystem-0.0.1-SNAPSHOT.jar

### Usage
The UI has 6 tabs to enter information.
The tabs must be filled from left to right, since the more left tabs create information for the right ones to use.

#### Register Student
Students base data is entered here by filling the textfields and clicking the "Add" button.
For demo purposes is this tab prefilled with 6 students
To delete a student record, mark the line and click "Delete Selected Rows"
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/studentregistration.PNG)

#### Register Course
the same logic like in Register Student applies here.
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/registercourse.PNG)

#### Register Course Members
Existing students can be added as course members to existing courses.
same Logic as above.
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/registercoursemember.PNG)

#### Register Course Dates
Courses must have course dates to track the attendence of cours members at course dates.
select a course and a date and add a date to the course date list.
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/registercoursedate.PNG)

#### Book Attendence
An attending course member can be added to a course date here.
Select course date and course Member and her/him to the attending members.

![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/bookAttendence.PNG)

#### Profile
the Profile built from the entered Data.
See the attendence for each student for each course she/he is member
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/profileview.PNG)

### Class Diagrams
#### Core Classes
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/mainClasses.png)
#### Complete 
![alt text](https://github.com/s17001620/RegisterSystem/blob/master/documentation/completeClassdiagram.png)