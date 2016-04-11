package pl.bookstore.robot;

/**
 * Created by damian on 29.03.16.
 */

import org.apache.log4j.Logger;


public class MainClass {

    final static Logger logger = Logger.getLogger(MainClass.class);

    public static void main(String[] args) {


//
//        if (args.length >= 1) {
//            for (int i = 0; i < args.length; i++) {
//                System.out.println(args[i]);
//                if (args[i].equals("gui")) {
//                    logger.info("Start GUI");
//                    System.out.println("GUI START");
//                } else {
//                    logger.error("Nieznane polecenie");
//                }
//            }
//        } else {
//            System.out.println("CLI START");
//            logger.info("Start CLI");
//            MySqlFunction mysql = null;
//            try {
//                mysql = new MySqlFunction();
//            } catch (ClassNotFoundException | SQLException e) {
//                logger.error(e);
//                e.printStackTrace();
//            }
//
//            LinkedList<BookStore> book = null;
//            try {
//                book = mysql.getListBookStore();
//            } catch (SQLException e) {
//                logger.error(e);
//                e.printStackTrace();
//            }
//
//            try {
//                mysql.saveListBookStore(book);
//            } catch (SQLException e) {
//                logger.error(e);
//                e.printStackTrace();
//            }
//            mysql.close();
//
//            for (BookStore bookStore : book) {
//                if (bookStore.isActive())
//                    try {
//                        new BookSearch(bookStore.getUrl(), bookStore.getSiteName());
//                    } catch (IOException | NotFound | ResponseException e) {
//                        logger.error(e);
//                    }
//            }

//        }
    }
}
