package murach.db;

/*
* Date: November 10th, 2019
* @author Drake Greeott
* Description: add a notes field to the Product Manager application and display the first 30
characters of the note after the price column. Make sure the Product table has been create in the MySQL
database.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import murach.business.Product;

public class ProductDB {

    private static Product getProductFromRow(ResultSet rs) throws SQLException {

        int productID = rs.getInt(1);
        String code = rs.getString(2);
        String description = rs.getString(3);
        double price = rs.getDouble(4);
        String note = rs.getString(5);

        Product p = new Product();
        p.setId(productID);
        p.setCode(code);
        p.setDescription(description);
        p.setPrice(price);
        p.setNote(note);
        
        return p;
    }

    public static List<Product> getAll() throws DBException {
        String sql = "SELECT * FROM Product ORDER BY ProductID";
        List<Product> products = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = getProductFromRow(rs);
                products.add(p);
            }
            rs.close();
            return products;
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static Product get(String productCode) throws DBException {
        String sql = "SELECT * FROM Product WHERE Code = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, productCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = getProductFromRow(rs);
                rs.close();
                return p;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void add(Product product) throws DBException {
        String sql
                = "INSERT INTO Product (Code, Description, ListPrice, Note) "
                + "VALUES (?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getNote());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void update(Product product) throws DBException {
        String sql = "UPDATE Product SET "
                + "Code = ?, "
                + "Description = ?, "
                + "ListPrice = ?,"
                + "Note = ? "
                + "WHERE ProductID = ? ";
                
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());            
            ps.setString(4, product.getNote());
            ps.setLong(5, product.getId());            
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public static void delete(Product product)
            throws DBException {
        String sql = "DELETE FROM Product "
                + "WHERE ProductID = ?";
        Connection connection = DBUtil.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
    
}