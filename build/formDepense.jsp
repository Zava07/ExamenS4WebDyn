<%@ page import="main.Java.Model.* ,java.util.List" %>

<%
    List<Credit> creditList = (List<Credit>) request.getAttribute("listeCredit");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion Depense</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #5f2c82, #49a09d);
            color: white;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }

        form {
            background-color: rgba(0, 0, 0, 0.6);
            padding: 30px;
            border-radius: 8px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
        }

        label {
            font-size: 18px;
            margin-bottom: 10px;
            display: block;
        }

        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #49a09d;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #5f2c82;
        }

        select {
            background-color: #fff;
            color: #333;
        }

        a {
            color: #49a09d;
            text-decoration: none;
            font-size: 16px;
            margin-top: 15px;
            display: inline-block;
            transition: color 0.3s ease;
        }

        a:hover {
            color: white;
        }
    </style>
</head>
<body>
    <form action="/ETU003777/depense" method="post">
        <label for="montantDepense">Montant Depense</label>
        <input type="text" name="montantDepense" id="montantDepense" placeholder="Montant ..." required>
        
        <label for="credit">Credit</label>
            <% if(creditList != null && !creditList.isEmpty()){ %>
                <select name="credit" id="credit" required>
                    <% for(Credit cr : creditList){ %>
                        <option value="<%= cr.getId() %>"><%= cr.getLibelle() %></option>
                    <% } %>
                </select>
            <% }else {%>
                <h1>Aucune donner</h1>
            <% } %>
        
        <input type="submit" value="Enregistrer">
    </form>
    <a href="/ETU003777/">Retour</a>
</body>
</html>
