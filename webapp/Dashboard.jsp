<%@ page import="javax.servlet.http.*,javax.servlet.*,java.util.HashMap" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
if (session == null || session.getAttribute("logged") == null) {
    response.sendRedirect("Login.html"); // Redirect to login if not authenticated
    return;
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome - Event Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url("background.jpg");
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .content-box {
            background-color: rgba(255, 255, 255, 0.1); /* Glassmorphism */
            backdrop-filter: blur(15px); /* Applying blur effect */
            padding: 40px;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.2);
            border-radius: 15px;
            width: 60%;
            text-align: center;
            color: #fff;
        }

        .header {
            margin-bottom: 20px;
            font-size: 28px;
            font-weight: bold;
        }

        .events-list {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .event-item {
            background-color: rgba(0, 123, 255, 0.8);
            color: white;
            padding: 15px;
            border-radius: 8px;
            font-size: 20px;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
            position: relative;
        }

        .event-item:hover {
            transform: scale(1.05);
        }

        .event-item span {
            font-weight: bold;
        }

        .delete-button {
            position: absolute;
            right: 10px;
            top: 10px;
            background-color: #dc3545;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
        }

        .delete-user-button {
            display: inline-block;
            margin-top: 20px;
            background-color: #6c757d; /* Gray color specific to delete user */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .delete-user-button:hover {
            background-color: #495057; /* Darker gray */
            transform: scale(1.05);
        }

        .footer {
            margin-top: 20px;
            font-size: 14px;
            color: black;
        }

        .important-info {
            font-style: italic;
            color: #ff3b3b;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="content-box">
        <div class="header">
            Welcome, <%= session.getAttribute("name") %>!
        </div>

        <h3>Upcoming Event Dates & Times:</h3>

        <div class="events-list">
            <% 
                Boolean dance = (Boolean) session.getAttribute("Dance");
                Boolean singing = (Boolean) session.getAttribute("Singing");
                Boolean drama = (Boolean) session.getAttribute("Drama");
                Boolean painting = (Boolean) session.getAttribute("Painting");
            %>

            <% if (dance != null && dance) { %>
            <div class="event-item">
                <span>Dance</span>: 2025-02-10 10:00 AM
                <form method="post" action="./DeleteServlet" onsubmit="return ask('Dance');">
                    <input type="hidden" name="event" value="dance" />
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </div>
            <% } %>
            
            <% if (singing != null && singing) { %>
            <div class="event-item">
                <span>Singing</span>: 2025-02-10 1:00 PM
                <form method="post" action="./DeleteServlet" onsubmit="return ask('Singing');">
                    <input type="hidden" name="event" value="singing" />
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </div>
            <% } %>
            
            <% if (drama != null && drama) { %>
            <div class="event-item">
                <span>Drama</span>: 2025-02-11 10:00 AM
                <form method="post" action="./DeleteServlet" onsubmit="return ask('Drama');">
                    <input type="hidden" name="event" value="drama" />
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </div>
            <% } %>
            
            <% if (painting != null && painting) { %>
            <div class="event-item">
                <span>Painting</span>: 2025-02-11 2:00 PM
                <form method="post" action="./DeleteServlet" onsubmit="return ask('Painting');">
                    <input type="hidden" name="event" value="painting" />
                    <button type="submit" class="delete-button">Delete</button>
                </form>
            </div>
            <% } %>
            
            <% if (dance == null && singing == null && drama == null && painting == null) { %>
            <div class="event-item">No events selected.</div>
            <% } %>
        </div>
        
        <!-- Delete User Button -->
        <form method="post" action="./DeleteServlet" onsubmit="return confirm('Are you sure you want to delete your user account? This action is irreversible.');">
            <button name="user" type="submit" class="delete-user-button">Delete User Account</button>
        </form>

        <div class="footer">
            * Please ensure you attend the events on the mentioned dates.
        </div>

        <!-- Important Note -->
        <div class="important-info">
            Note: If any event is deleted, it will be permanently removed and cannot be recovered.
        </div>
    </div>
    
    <script>
        function ask(name) {
            return confirm("Are you sure you want to delete the event " + name + "? This action is irreversible.");
        }
    </script>
</body>
</html>
