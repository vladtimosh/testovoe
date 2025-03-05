package dao;

import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/your_database";
    private String jdbcUsername = "your_username";
    private String jdbcPassword = "your_password";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertMember(Member member) {
        String sql = "INSERT INTO Members (name, email) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getName());
            statement.setString(2, member.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member getMember(int id) {
        Member member = null;
        String sql = "SELECT * FROM Members WHERE member_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Members";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Member member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void updateMember(Member member) {
        String sql = "UPDATE Members SET name = ?, email = ? WHERE member_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getName());
            statement.setString(2, member.getEmail());
            statement.setInt(3, member.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM Members WHERE member_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}