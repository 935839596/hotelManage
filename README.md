# hotelManage
a simple hotelManage System

## students can apply for the room and the administrator deal with student' application through input studentID into bed.

## Files
 ### Jsp(below pages can only be seen by administrator except index.jsp)
  * index.jsp
    index page
    student can apply(don't need login) and admin login to following 
  * room.jsp
    Administrator can see every room's general situation and clicking room will show the detail.
  * nationality.jsp
    to see how many nationalities totaly and the number of each countries.
  * rent.jsp
    calculate income per year or month
  * application.jsp
    in this page ,administrator can see every applications students push and copy the studentID to the room.jsp to settle them down.
    
 ### DAO
  * DBcommonUtil
    connector to the database.
  * RoomUtil
    some method about room (can easily understand by methodName)
  * StuUtil
    some method about student
    
 ### VO
   Room、student
 
 ### servlets
   use in room.jsp
   * AddRooms (add a new room)
   * GetData (return rooms' situation)
   * GetRoomInfo (return a room's detail)
   * Search (admin can search a room with roomID)
   * SettleDown (admin arranges student)
   
   use in nationality.jsp
   * Nationality
   
   use in rent.jsp
   * Rent
   
   use in application.jsp
   * Application (it is also used by other three pages in order to show how many application)
   
   other
   * Application (get students' appllication)
   * UploadServlet (help to get the path of the upload files)
   * login (admin login and the password can be changed here.)
   
 ### function
   * Count
     rent calculate and the nationality are done here.
   * Function
     help get time.
     
## database
   mysql database is export as 'hotelManage.sql' 
    
