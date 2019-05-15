# MovieApp

Enter your api-key into the "api_key" string which is top of the "MainActivity.java" class.

## Description

  Initially custom array adapter used in this project to design the list. "singlerow.xml" is a refrence row for the list and this list take
the data from People Arraylist("mainpeople") to fill the table. When we come up to Strigns, there are 3 main string for the control the url
link, and these are "query" , "page" and "type". Query string is taken from searchbar and use for the fill the url. Secondly, "type" string is used in searchbar for determining type of the url which needs from app. Basically there are two types which are "main", "search". "Main" type is using for the main-screen that include most popular people, and with "search" type app can search the people that written in searchbar. Finally, "page" string is using to determine page. These three string use for the "url" method for the creating unique url and  execute asynctask in "JSON" class to fill the "mainpeople". Additionally, when you are in the searched list, you can type "main" in the searchbar or press previous button in search mode while page is more than 1 in order to return most popular human.

Data is taken from "The Movie DB(TMDb)".


