<%@ page import="main.Java.Model.* ,java.util.List" %>

<%
    List<Dashboard> dash = (List<Dashboard>) request.getAttribute("listeDashBoard");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #0f0c29, #302b63, #24243e);
            color: white;
            
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            box-shadow: 0 0 20px rgba(255, 255, 255, 0.2);
            background: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            overflow: hidden;
      
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background: rgba(255, 255, 255, 0.3);
        }
        tr:nth-child(even) {
            background: rgba(255, 255, 255, 0.2);
        }
        tr:hover {
            background: rgba(255, 255, 255, 0.4);
            transition: 0.3s;
        }
        a{
            text-decoration: none;
            color: white;
        }
    </style>
</head>
<body>
    <div class="titre">
        <h1>Dashboard</h1>
    </div>
    <table>
        <tr>
            <th>idCredit</th>
            <th>Libelle</th>
            <th>Montant Credit</th>
            <th>Montant Depense</th>
            <th>Reste</th>
        </tr>
        <% if (dash != null && !dash.isEmpty()) { %>
            <% for (Dashboard da : dash) { %>
                <tr>
                    <td><%= da.getIdCredit() %></a></td>
                    <td><%= da.getLibelle() %></a></td>
                    <td><%= da.getMontantCredit() %></a></td>
                    <td><%= da.getMontantDepense() %></a></td>
                    <td><%= da.getReste() %></a></td>
                    
                </tr>

            <% } %>
        <% } else { %>
            <tr>
                <td colspan="2" style="text-align: center;">Aucun Credit et depense inserer</td>
            </tr>
        <% } %>
        <a href="/ETU003777/">Retour</a>
    </table>
</body>
</html>
