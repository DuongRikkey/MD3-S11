package test;

import rikkey.academy.model.Category;

import java.sql.*;

public class Main {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_mysql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "duong123";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            // Đăng ký Driver
            Class.forName(DRIVER);
            // Tạo connection
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println(conn);
            // THỰC THI CÂU LỆNH
//            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("select * from category where id =?");
            // truyền đổi số
//            CallableStatement call = conn.prepareCall("{call insert_cate(?,?)}");
//            call.setString(1,"Duong");
//            call.setBoolean(2,true);

//           int conut= call.executeUpdate();

            // truyền đối số vào
            ps.setInt(1, 100);
            // execute , executeQuery(SELECT) ,executeUpdate(Update/Insert/Delete)
            ResultSet rs = ps.executeQuery();
//            ResultSet rs = stmt.executeQuery("select * from category ");

            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                System.out.println(category);

            }




//            while (rs.next()) {
//                Category category = new Category();
//                category.setId(rs.getInt("id"));
//                category.setName(rs.getString("name"));
//                category.setStatus(rs.getBoolean("status"));
//                System.out.println(category);
//
//            }
//            int count=  stmt.executeUpdate("insert into category (name,status) values ('Duong',1)");
//            System.out.println(count);
            // Đóng kết nối , tránh rò rỉ bộ nhớ
//            System.out.println(conut);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
