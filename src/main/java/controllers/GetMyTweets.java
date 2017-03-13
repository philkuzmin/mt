package controllers;

import dao.DaoFactory;
import model.Instrument;
import model.Tweet;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Air on 11/03/2017.
 */
@WebServlet("/GetMyTweets")
public class GetMyTweets extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        Writer out = response.getWriter();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("User");
        String localeString = (String) session.getAttribute("locale");
        Locale locale = new Locale(localeString);
        ResourceBundle bundle = ResourceBundle.getBundle("main", locale);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withLocale(locale);
        DaoFactory daoFactory = (DaoFactory) getServletContext().getAttribute("daoFactory");

        if (daoFactory != null && user != null) {
            List<Instrument> instrumentList = daoFactory.getInstrumentDao().getUserInstruments(user.getUserId());
            StringBuilder instruments = new StringBuilder();
            for (Instrument instrument : instrumentList) {
                instruments.append(instrument.getInstrumentName()).append(", ");
            }
            instruments.delete(instruments.length() - 2, instruments.length());
            List<Tweet> tweets = daoFactory.getMessageDao().getUserMessages(user, instruments.toString());
            out.write(ProcessTweets.process(tweets, bundle, formatter));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}