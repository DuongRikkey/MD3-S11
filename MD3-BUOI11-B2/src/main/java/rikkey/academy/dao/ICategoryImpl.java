package rikkey.academy.dao;

import rikkey.academy.model.Category;
import rikkey.academy.until.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ICategoryImpl implements ICategoryDao<Category, Integer> {

    @Override
    public void addAndUpdate(Category category) {
        Connection conn = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            if (category.getId() == null) {
                // Thêm mới
                ps = conn.prepareStatement("INSERT INTO category(name, status) VALUES (?, ?)");
                ps.setString(1, category.getName());
                ps.setBoolean(2, category.getStatus());
            } else {
                // Cập nhật thông tin nếu tồn tại ID
                ps = conn.prepareStatement("UPDATE category SET name = ?, status = ? WHERE id = ?");
                ps.setString(1, category.getName());
                ps.setBoolean(2, category.getStatus());
                ps.setInt(3, category.getId());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void remove(Integer id) {
        Connection conn = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM category WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public int findIndexByID(Integer id) {
        Connection conn = ConnectDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) AS count FROM category WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("count") > 0 ? 1 : -1;  // Trả về 1 nếu tìm thấy, ngược lại trả về -1
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return -1;  // Không tìm thấy ID
    }

    @Override
    public Integer getNewID() {
        Connection conn = ConnectDB.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT MAX(id) AS max_id FROM category");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("max_id") + 1;  // Lấy giá trị id lớn nhất và tăng thêm 1
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return 1;  // Nếu bảng trống, bắt đầu từ id = 1
    }

    @Override
    public List<Category> findAll() {
        Connection conn = ConnectDB.getConnection();
        List<Category> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM category");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }
}
