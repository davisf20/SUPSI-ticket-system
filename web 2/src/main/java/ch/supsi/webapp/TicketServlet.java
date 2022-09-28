package ch.supsi.webapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/tickets")
public class TicketServlet extends HttpServlet {
    private ArrayList<Ticket> tickets = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // res.getWriter().println("Hello world!!");
        // Ticket ticket = mapper.readValue(new File("data.json"), Ticket.class);
        tickets = mapper.readValue(new File("data/data.json"), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Ticket.class));
        res.getWriter().println(tickets);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Ticket ticket = mapper.readValue(req.getInputStream(), Ticket.class);
        tickets.add(ticket);
        mapper.writeValue(new File("data/data.json"), tickets);
    }
}
