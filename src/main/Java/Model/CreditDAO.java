package main.Java.Model;

import main.Java.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class CreditDAO implements DAO {
    
    @Override
    public void save(BaseModel m) throws Exception{
        Credit credit = (Credit) m;
        try(Connection connexion = DB.connect()){
            String request = "INSERT INTO credit (libelle,montant) VALUES (? , ?) ";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(request)){
                preparedStatement.setString(1,credit.getLibelle());
                preparedStatement.setFloat(2,credit.getMontant());

                preparedStatement.executeUpdate();
            }  
            catch (SQLException e) {
                throw new Exception("erreur sql" +e.getMessage());
            }
        } catch (Exception e) {
            throw new Exception("erreur sur la connexion" + e.getMessage());
        }
    }


    public float getMontantCredit(int id) throws Exception{
        float montant = 0;
        try(Connection connexion = DB.connect()){
            String request = "SELECT montant FROM credit WHERE idCredit = ? ";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(request)){
                    preparedStatement.setInt(1,id);
                    try(ResultSet rs = preparedStatement.executeQuery()){
                        if(rs.next()){
                            montant = rs.getFloat("montant");
                        }
                    }catch (SQLException e) {
                        throw new Exception("erreur sql"+e.getMessage());
                    }
                }catch(SQLException e){
                    throw new Exception("Erreur sql "+e.getMessage());
                }
        }catch(Exception e){
                throw new Exception("Exception"+e.getMessage());
        }
        return montant;
    }
    @Override
    public void delete(int id) throws Exception{
        String request = "DELETE FROM credit WHERE idCredit = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                prstm.executeUpdate();
                System.out.println("credit deleted successfully");
            } catch (SQLException e) {
                throw new Exception("erreur sql" +e.getMessage());
            }
        } catch (Exception e) {
            throw new Exception("erreur sur la connexion" + e.getMessage());
        }
    }

    @Override 
    public void update(BaseModel m) throws Exception{
        Credit credit = (Credit) m;
        String request = "UPDATE credit SET libelle = ? , montant = ? WHERE idCredit = ? ";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setString(1,credit.getLibelle());
                prstm.setFloat(2,credit.getMontant());
                prstm.setInt(3,credit.getId());

                prstm.executeUpdate();
                System.out.println("Employer update successfully");
            } catch (SQLException e) {
                throw new Exception("erreur sql" +e.getMessage());
            }
        }catch (Exception e) {
            throw new Exception("erreur sur la connexion" + e.getMessage());
        }
    }

    @Override
    public List<BaseModel> findAll() throws Exception{
        List<BaseModel> creditList = new ArrayList<>();
        try(Connection connexion = DB.connect()){
            String request = "SELECT * FROM credit";
            try(Statement stm = connexion.createStatement()){
                try(ResultSet rs = stm.executeQuery(request)){
                    while(rs.next()){
                        creditList.add(new Credit(
                            rs.getInt("idCredit"), 
                            rs.getString("libelle"), 
                            rs.getFloat("montant")));
                    }
                }catch (SQLException e) {
                    throw new Exception("erreur sql"+e.getMessage());
                }
            }catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        }catch(Exception e){
            throw new Exception("Exception"+e.getMessage());
        }
        return creditList;
    }

    @Override
    public Credit findById(int id) throws Exception{
        String request = "SELECT * FROM credit WHERE idCredit = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                try(ResultSet rs = prstm.executeQuery()){
                    if(rs.next()){
                        return new Credit(rs.getInt("idCredit"), 
                            rs.getString("libelle"),
                            rs.getFloat("montant"));
                    }
                }catch (SQLException e) {
                    throw new Exception("erreur sql"+e.getMessage());
                }
            }catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        }catch(Exception e){
            throw new Exception("Exception"+e.getMessage());
        }
        System.err.println("Credit non trouve pour l'id"+id);
        return null;
    }

    @Override 
    public List<BaseModel> findAllWithPagination(int index , int count) throws Exception{
        List<BaseModel> creditList = new ArrayList<>();
        String request = "SELECT * FROM credit ORDER BY id LIMIT ? OFFSET ?";

        try (Connection connection = DB.connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
                preparedStatement.setInt(2, index - 1);
                preparedStatement.setInt(1, count);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        creditList.add(
                            new Credit(
                                resultSet.getInt("idCredit"),
                                resultSet.getString("libelle"),
                                resultSet.getInt("montant")
                            )
                        );
                    }
                }catch (SQLException e) {
                    throw new Exception("erreur sql"+e.getMessage());
                }
            }
            catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        }
        catch(Exception e){
            throw new Exception("Exception"+e.getMessage());
        }
        return creditList;

    }

    public List<Dashboard> getDashBord() throws Exception{
        List<Dashboard> list = new ArrayList<>();
        DepenseDAO depenseDAO = new DepenseDAO();
        try(Connection connexion = DB.connect()){
            String request = "SELECT * FROM credit";
            try(Statement stm = connexion.createStatement()){
                try(ResultSet rs = stm.executeQuery(request)){
                    while(rs.next()){
                        int idCredit = (int) rs.getInt("idCredit");
                        String libelle =(String) rs.getString("libelle");
                        float montantCredit = rs.getInt("montant");
                        Depense d =  new Depense(0, 0, idCredit);
                        float montantDepense = depenseDAO.getsommeDepense(d);
                        float reste = montantCredit - montantDepense;
                        Dashboard da = new Dashboard(idCredit, libelle, montantCredit, montantDepense,reste);

                        list.add(da);

                    }
                }catch (SQLException e) {
                    throw new Exception("erreur sql"+e.getMessage());
                }
            }
            catch(SQLException e){
                throw new Exception("Erreur sql "+e.getMessage());
            }
        }
        catch(Exception e){
            throw new Exception("Exception"+e.getMessage());
        }
        return list;
    }
    
}
