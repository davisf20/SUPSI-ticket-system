package ch.supsi.webapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/api/tickets/*")
public class TicketServlet extends HttpServlet {
    private final ArrayList<Ticket> tickets = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")){
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // get one specific ticket
        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (ticket != null) {
                res.setStatus(200);
                res.setHeader("Content-Type", "application/json");
                res.getWriter().println(mapper.writeValueAsString(ticket));
            } else {
                res.setStatus(404);
                res.getWriter().println("Ticket not found");
            }
        } else {
            // get all tickets
            res.setStatus(200);
            res.getWriter().println(mapper.writeValueAsString(tickets));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String type = req.getContentType();
        Ticket ticket;

        if (type != null) {
            if (type.equals("application/json")) {
                ticket = mapper.readValue(req.getInputStream(), Ticket.class);
                tickets.add(ticket);
                res.setStatus(201);
                res.setHeader("Content-Type", "application/json");
                res.getWriter().println(mapper.writeValueAsString(ticket));
            } else {
                res.setStatus(400);
                res.getWriter().println("Invalid content type");
            }
        } else {
            res.setStatus(400);
            res.getWriter().println("Invalid content type");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (req.getPathInfo() != null) {
            String type = req.getContentType();
            if (type != null) {
                if (type.equals("application/json")) {
                    int id = Integer.parseInt(req.getPathInfo().substring(1));
                    Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
                    if (ticket != null) {
                        Ticket newTicket = mapper.readValue(req.getInputStream(), Ticket.class);
                        ticket.setTitle(newTicket.getTitle());
                        ticket.setDescription(newTicket.getDescription());
                        ticket.setAuthor(newTicket.getAuthor());
                        res.setStatus(200);
                        res.setHeader("Content-Type", "application/json");
                        res.getWriter().println(mapper.writeValueAsString(ticket));
                    } else {
                        res.setStatus(404);
                        res.getWriter().println("Ticket not found");
                    }
                }
            }
        } else {
            res.setStatus(400);
            res.getWriter().println("Bad request");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (req.getPathInfo() != null) {
            int id = Integer.parseInt(req.getPathInfo().substring(1));
            Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            if (ticket != null) {
                tickets.remove(ticket);
                res.setStatus(204);
            } else {
                res.setStatus(404);
                res.getWriter().println("Ticket not found");
            }
        } else {
            res.setStatus(400);
            res.getWriter().println("Bad request");
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (req.getPathInfo() != null) {
            String type = req.getContentType();
            if (type != null) {
                if (type.equals("application/json")) {
                    int id = Integer.parseInt(req.getPathInfo().substring(1));
                    Ticket ticket = tickets.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
                    if (ticket != null) {
                        Ticket newTicket = mapper.readValue(req.getInputStream(), Ticket.class);
                        if (newTicket.getTitle() != null) {
                            ticket.setTitle(newTicket.getTitle());
                        }
                        if (newTicket.getDescription() != null) {
                            ticket.setDescription(newTicket.getDescription());
                        }
                        if (newTicket.getAuthor() != null) {
                            ticket.setAuthor(newTicket.getAuthor());
                        }
                        res.setStatus(200);
                        res.setHeader("Content-Type", "application/json");
                        res.getWriter().println(mapper.writeValueAsString(ticket));
                    } else {
                        res.setStatus(404);
                        res.getWriter().println("Ticket not found");
                    }
                }
            }
        } else {
            res.setStatus(400);
            res.getWriter().println("Bad request");
        }
    }
}
