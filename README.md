# robot

It is a program to search books on internet bookstores.

First to use program you have to clone git repository from git@github.com:damianbajno/robot.git

To start program specify the database properties: database server url, database name, password
in file /robot/src/main/resources/hibernate.cfg.xml which is configuration file for hibernate.

Next to search books run the main method in pl.bookstore.robot.view.GuiMainClass file.
After it you see Gui interface where you have to specify BookStores to search books and path to title and category
in page. If you want to search for books after specifying library you have to run main method in class
pl.bookstore.robot.booksearch.BookSearcherAndSaverMain.



