package dao;

import model.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private String jdbcURL = "jdbc:postgresql://localhost:5432/Library";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "234500239";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertMember(Member member) {
        String sql = "INSERT INTO members (full_name, membership_date) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getFullName());
            statement.setDate(2, Date.valueOf(member.getMembershipDate())); // Убедитесь, что это поле существует
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Member getMember(int id) {
        Member member = null;
        String sql = "SELECT * FROM members WHERE member_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("membership_date").toLocalDate() // Преобразование даты
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Member member = new Member(
                        resultSet.getInt("member_id"),
                        resultSet.getString("full_name"),
                        resultSet.getDate("membership_date").toLocalDate() // Преобразование даты
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void updateMember(Member member) {
        String sql = "UPDATE members SET full_name = ?, membership_date = ? WHERE member_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getFullName());
            statement.setDate(2, Date.valueOf(member.getMembershipDate())); // Убедитесь, что это поле существует
            statement.setInt(3, member.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int id) {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}