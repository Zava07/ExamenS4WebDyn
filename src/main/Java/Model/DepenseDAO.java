package main.Java.Model;

import main.Java.utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DepenseDAO implements DAO {
    

    
    public float getsommeDepense(BaseModel m) throws Exception{
        Depense depense = (Depense) m;
        float somme = 0;
        try(Connection connexion = DB.connect()){
            String request = "SELECT SUM(montantDepense) AS somme FROM depense WHERE idCredit = ? ";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(request)){
                preparedStatement.setInt(1,depense.getIdCredit());
                try(ResultSet rs = preparedStatement.executeQuery()){
                    if(rs.next()){
                        somme = rs.getFloat("somme");
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
        return somme;
    }


    @Override
    public void save(BaseModel m) throws Exception{
        Depense depense = (Depense) m;
        CreditDAO creditdao = new CreditDAO();
        float montantCredit = creditdao.getMontantCredit(depense.getIdCredit());
        float montantDepense = this.getsommeDepense(depense) + depense.getMontantDepense();

        if(montantCredit < montantDepense){
            throw new Exception("montant entrer superrieur a montantCredit , montantDepense :" +montantDepense +"montantCredit :"+montantCredit);
        }
        try(Connection connexion = DB.connect()){
            String request = "INSERT INTO depense (idCredit,montantDepense) VALUES (? , ?) ";
            try(PreparedStatement preparedStatement = connexion.prepareStatement(request)){
                preparedStatement.setInt(1,depense.getIdCredit());
                preparedStatement.setFloat(2,depense.getMontantDepense());

                preparedStatement.executeUpdate();
            }  
            catch (SQLException e) {
                throw new Exception("erreur sql" +e.getMessage());
            }
        } catch (Exception e) {
            throw new Exception("erreur sur la connexion" + e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws Exception{
        String request = "DELETE FROM depense WHERE idDepense = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                prstm.executeUpdate();
                System.out.println("depense deleted successfully");
            } catch (SQLException e) {
                throw new Exception("erreur sql" +e.getMessage());
            }
        } catch (Exception e) {
            throw new Exception("erreur sur la connexion" + e.getMessage());
        }
    }

    @Override 
    public void update(BaseModel m) throws Exception{
        Depense depense = (Depense) m;
        String request = "UPDATE depense SET idCredit = ? , montantDepense = ? WHERE idDepense = ? ";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1,depense.getIdCredit());
                prstm.setFloat(2,depense.getMontantDepense());
                prstm.setInt(3,depense.getId());

                prstm.executeUpdate();
                System.out.println("Depense update successfully");
            } catch (SQLException e) {
                throw new Exception("erreur sql" +e.getMessage());
            }
        }catch (Exception e) {
            throw new Exception("erreur sur la connexion" + e.getMessage());
        }
    }

    @Override
    public List<BaseModel> findAll() throws Exception{
        List<BaseModel> depenseList = new ArrayList<>();
        try(Connection connexion = DB.connect()){
            String request = "SELECT * FROM depense";
            try(Statement stm = connexion.createStatement()){
                try(ResultSet rs = stm.executeQuery(request)){
                    while(rs.next()){
                        depenseList.add(new Depense(
                            rs.getInt("idDepense"), 
                            rs.getFloat("montantDepense"),
                            rs.getInt("idCredit")));
                    }
                }
            }
        }
        return depenseList;
    }

    @Override
    public Depense findById(int id) throws Exception{
        String request = "SELECT * FROM depense WHERE idDepense = ?";
        try(Connection connexion = DB.connect()){
            try(PreparedStatement prstm = connexion.prepareStatement(request)){
                prstm.setInt(1, id);
                try(ResultSet rs = prstm.executeQuery()){
                    if(rs.next()){
                        return new Depense(rs.getInt("idDepense"), 
                            rs.getFloat("montantDepense"),
                            rs.getInt("idCredit"));
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
        System.err.println("Depense non trouve pour l'id"+id);
        return null;
    }

    @Override 
    public List<BaseModel> findAllWithPagination(int index , int count) throws Exception{
        List<BaseModel> depenseList = new ArrayList<>();
        String request = "SELECT * FROM depense ORDER BY id LIMIT ? OFFSET ?";

        try (Connection connection = DB.connect()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
                preparedStatement.setInt(2, index - 1);
                preparedStatement.setInt(1, count);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        depenseList.add(
                            new Depense(
                                resultSet.getInt("idDepense"),
                                resultSet.getFloat("montantDepense"),
                                resultSet.getInt("idCredit")
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
        return depenseList;

    }
}
