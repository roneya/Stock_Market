﻿# Stock_Market
 
 Assignment 2 :		https://github.com/roneya/Stock_Market/

1st API: ("/adduser")


Here the password is encoded and then stored for a unique email as id.
{
  "first_name": "Rohan",
  "last_name": "Vidhate",
  "email": "iamrohanvidhate@gmail.com",
  "phone_number": "12345",
  "password": "12345"
}




2nd API: ("/login")
{
   "email": "iamrohanvidhate@gmail.com",
   "password": "12345"
}









3rd API: ("/subscribe")
(stored in mongodb with session id)
{
  "stock_symbol": ["IBM", "BA", "MSFT"],
  "notificationFrequency": ["MONTHLY", "WEEKLY"],
  "notify_time": "10:00 AM",
  "email": "iamrohanvidhate@gmail.com",
  "password": "12345"
}
Email notification and same time all session id and related data is stored in db:
Note: date and stock symbol and then open, high, low, close, volume these values are in the same sequence store in db.




4th API: ("/infoinrange")
(only print the info)
{
  
 "stock_symbol": "IBM",
  "start_date": "2023-01-01",
  "end_date" : "2023-03-01"


}

