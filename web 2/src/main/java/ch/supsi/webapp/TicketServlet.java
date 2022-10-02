package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/tickets")
public class TicketServlet extends HttpServlet {
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.getWriter().println(mapper.writeValueAsString(tickets));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String type = req.getContentType();
        Ticket ticket = null;

        if (type != null) {
            if (type.equals("application/json")) {
                ticket = mapper.readValue(req.getInputStream(), Ticket.class);
                tickets.add(ticket);
            } else if (type.equals("application/x-www-form-urlencoded")) {
                String title = req.getParameter("title");
                String description = req.getParameter("description");
                String author = req.getParameter("author");
                ticket = new Ticket(title, description, author);
                tickets.add(ticket);
            }
        }

        res.getWriter().println(mapper.writeValueAsString(ticket));
    }
}
