package by.bsuir.mycoolsite.dao.impl;

import by.bsuir.mycoolsite.bean.Category;
import by.bsuir.mycoolsite.connection.DBConnection;
import by.bsuir.mycoolsite.dao.CategoryDAO;
import by.bsuir.mycoolsite.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCategoryDAO implements CategoryDAO {
    private static final Logger logger = LogManager.getLogger(SQLCategoryDAO.class);
    private static final String QUERY_GET_CATEGORIES =
            "SELECT cat_id, cat_name " +
                    "FROM category";

    @Override
    public List<Category> getCategories() throws DAOException {
        List<Category> categories = new ArrayList<>();

        DBConnection dbConnection = DBConnection.getInstance();
        Connection con = dbConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            ps = con.prepareStatement(QUERY_GET_CATEGORIES);

            rs = ps.executeQuery();

            while(rs.next()){
                long catId = rs.getLong(1);
                String catName = rs.getString(2);
                Category category = new Category(catId, catName);
                categories.add(category);
            }

        } catch (SQLException e) {
            logger.error("Selection from categories error", e);
            throw new DAOException("Selection from categories error", e);
        } finally {
            dbConnection.close(ps, rs);
        }

        return categories;
    }
}
