# Bookstore-Java
Repository for Java Project:
Bookstore Counter Management

User class contains the Frame and components that are to be used by employees of the bookstore
1) Authorized to only add the customer name, contact number, product id's, quantity to calculate total amount create a bill.
2) Based on product id, book details are fetched and entered in respective fields.


LoginFrame contains Frame and components that are initially used during login and also after logout
1) It has to verify whether user credentials are correct
2) It also has to verify if individual has admin rights or not
3) Accomplished these with data from sql database.

Addframe & RemoveFrame contains Frame and components that are used for new entries in staff
1) Only admin has rights to make updation, deletion or addition of staff and their information.

Clock has the functionality where the date and time instance is supplied to various frames for display purpose

Admin class is the module that opens up where administrators can view the current inventory status and carry out operations like
1) Add employees record
2) Update Employee Record
3) Delete Employee record
